package com.microfundit.listener;

import com.microfundit.dao.FundingRepository;
import com.microfundit.dao.StoryRepository;
import com.microfundit.dao.TransactionRepository;
import com.microfundit.model.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Component
@RepositoryEventHandler(Donation.class)
public class DonationEventHandler {
    private final StoryRepository stories;
    private final FundingRepository fundings;
    private final TransactionRepository transactions;

    Logger logger = Logger.getLogger(DonationEventHandler.class);
    private List<Transaction> donationTransactions = new ArrayList<>();

    @Autowired
    public DonationEventHandler(StoryRepository stories, FundingRepository fundings, TransactionRepository transactions) {
        this.stories = stories;
        this.fundings = fundings;
        this.transactions = transactions;
    }

    @HandleBeforeCreate
    public void addDonationTransaction(Donation donation) throws Exception {
        donation.setDateAdded(new Date());

        //Amount donated be it points or cash converted into cash in dollars
        int simpleDonatedAmount;

        //Donation is only of 2 types.  1 == CASH or 0 == POINTS
        if(donation.getType() == 1) {
            //Cash Donation amount maximum is 5
            donation.setPointsCompany(null);
            if(donation.getAmount() > 5) {
                throw new RuntimeException("You cannot donate more than 5 dollars");
            } else {
                simpleDonatedAmount = donation.getAmount();
            }
        } else if(donation.getType() == 0) {
            //Points Donation maximum is 2500 and must be a multiple of 500
            if((donation.getAmount() > 2500)) {
                throw new RuntimeException("You cannot donate more than 2500 points");
            } else if(donation.getAmount() % 500 != 0) {
                throw new RuntimeException("Donation in points must be a multiple of 500");
            } else {
                simpleDonatedAmount = donation.getAmount()/500;
            }
        } else {
            throw new RuntimeException("Invalid transaction type: Types can only be 1 = CASH or 0 = POINTS");
        }

        Story story = donation.getStory();
        if(story.getStatus() == 0) {
            throw new UnsupportedOperationException("This story has been closed.");
        }
        if (isDateExpired(story.getDateAdded(), story.getTimeAllocated())) {
            story.setStatus(0);
            stories.save(story);
            throw new UnsupportedOperationException("This story has been closed. Time allocated to it has elapsed.");
        }
        if (story.getCurrentAmount() >= story.getTargetAmount()) {
            story.setStatus(0);
            stories.save(story);
            throw new UnsupportedOperationException("This story has already been closed. The target amount has been hit");
        }
        int availableFunds = 0;
        for (Funding funding : fundings.findAll()) {
            availableFunds += funding.getCurrentAmount();
        }
        if (availableFunds <= 0) {
            throw new UnsupportedOperationException("There are currently no funds from brands for matching.");
        }

        //TODO: BANK TRANSACTIONS OR POINTS TRANSACTIONS

        Random random = new Random(System.currentTimeMillis());
        Funding selectedFunding;
        //fundings with current amount greater than or equal to amount donated
        List<Funding> fundingsGtEDAmount = fundings.findBycurrentAmountGreaterThan(simpleDonatedAmount - 1);
        logger.info(fundingsGtEDAmount.size());
        logger.info(fundingsGtEDAmount);
        //Fundings with current amounnt greater than or equal to amount donated * ratio of funding
        List<Funding> fundingsGtEDAmountTimesRatio = new ArrayList<>();
        for(Funding funding: fundingsGtEDAmount) {
            logger.info("Adding fundings...." + simpleDonatedAmount);
            logger.info("Adding fundings...." + (funding.getRatio() * simpleDonatedAmount));
            logger.info("Adding fundings...." + funding.getCurrentAmount());
            System.out.println("Adding fundings.....");
            if((funding.getRatio() * simpleDonatedAmount) <= funding.getCurrentAmount()) {
                logger.info("Added to fundings");
                fundingsGtEDAmountTimesRatio.add(funding);
            }
        }
        logger.info("fundingsGtEDAmountTimesRatio " + fundingsGtEDAmountTimesRatio.size());
        logger.info("****1" + simpleDonatedAmount);
        if(fundingsGtEDAmountTimesRatio.size() > 0) {
            logger.info("There exists at least one funding to serve this donation wholly");
            System.out.println("There exists at least one funding to serve this donation wholly");
            //randomise the funding to select
            logger.info("****2" + simpleDonatedAmount);
            selectedFunding = fundingsGtEDAmountTimesRatio.get(random.nextInt(fundingsGtEDAmountTimesRatio.size()));
            logger.info("****3" + simpleDonatedAmount);
            //Decrement the funding's current amount
            selectedFunding.setCurrentAmount(selectedFunding.getCurrentAmount() - (simpleDonatedAmount * selectedFunding.getRatio()));
            logger.info("****4" + simpleDonatedAmount);
            //check if the funding,s current amount is zero. If so close it
            if(selectedFunding.getCurrentAmount() <= 0) {
                selectedFunding.setStatus(0);
            }
            logger.info("****5" + simpleDonatedAmount);
            //save the updated brand
            fundings.save(selectedFunding);
            //set the matched brand of the donation to this fundings brand
            donation.setMatchedBrands(Arrays.asList(selectedFunding.getBrand()));
            donation.setMatchedAmount(simpleDonatedAmount);
            logger.info("****6" + simpleDonatedAmount);


            //TODO: TRANSACTION
            //The brand amount to be used in this transaction
            double brandAmount = simpleDonatedAmount * selectedFunding.getRatio();
            double microfunditAmount = (brandAmount / 100.00) * 10.00;
            //Story amount to display to people
            double storyAmount = simpleDonatedAmount + brandAmount;
            //The real story amount after microfundit gets its share
            double realStoryAmount = storyAmount - microfunditAmount;
            Transaction transaction = new Transaction(brandAmount, microfunditAmount, realStoryAmount);
            transactions.save(transaction);
            donationTransactions.add(transaction);
            donation.setTransactions(Arrays.asList(transaction));

            //Update story's current amount
            story.setCurrentAmount(story.getCurrentAmount() + storyAmount);
            stories.save(story);
        } else {
            logger.info("There not exists any funding to serve this donation wholly");
            System.out.println("There not exists any funding to serve this donation wholly");
            Map<Brand, Integer> selectedBrands = new HashMap<>();
            //fundings with status open
            List<Funding> openFundings = fundings.findByStatus(1);
            //You had already confirmed that there is enough cash in the fundings. So no need of rechecking.
            //However other donors might have caused the data to be updated and thus invalid. Here is the big
            //question. todo; can be modified
            Funding selectedOpenFunding;
            int requiredAmount = simpleDonatedAmount;
            int counter = 0;
            //Break out of the loop only when all the donated amount has been matched OR all the open fundings have
            //been tested for matching. Due to modification by other donors this it is possible that all the donated
            // amount is not matched
            while(requiredAmount > 0 && counter < (openFundings.size() - 1)) {
                logger.info("Matching........");
                System.out.println("Matching.........");
                selectedOpenFunding = openFundings.get(counter);
                int matchesCount = 0;
                //Break the the required amount into 1s
                int fundingCurrAmount = selectedOpenFunding.getCurrentAmount();
                int fundingRatio = selectedOpenFunding.getRatio();
//                while(selectedOpenFunding.getCurrentAmount() > 0 || requiredAmount > 0) {
                while(fundingCurrAmount >= fundingRatio) {
                    logger.info("Looping.....");
                    requiredAmount -= 1;
                    logger.info("Looping2.....");
                    selectedOpenFunding.setCurrentAmount(selectedOpenFunding.getCurrentAmount()
                            - selectedOpenFunding.getRatio());
                    logger.info("Looping3.....");
                    fundings.save(selectedOpenFunding);
                    logger.info("Looping4.....");
                    matchesCount++;
                    logger.info("Looping5.....");
                    fundingCurrAmount--;
                }
                logger.info("Out of the loop");
                if(selectedOpenFunding.getCurrentAmount() == 0) {
                    selectedOpenFunding.setStatus(0);
                    fundings.save(selectedOpenFunding);
                }
                selectedBrands.put(selectedOpenFunding.getBrand(), matchesCount);
                counter++;
            }
            List<Brand> selectedBrandssList = new ArrayList<>();
            selectedBrandssList.addAll(selectedBrands.keySet());
            donation.setMatchedBrands(selectedBrandssList);
            donation.setMatchedAmount(simpleDonatedAmount - requiredAmount);

            //TODO: TRANSACTIONS
            for (Map.Entry<Brand, Integer> entry: selectedBrands.entrySet()) {
                logger.info("Making transactions.....");
                System.out.println("Making transactions......");
                float microfunditAmount = entry.getValue() / 10;
                float brandAmount = entry.getValue() - microfunditAmount;
                float storyAmount = entry.getValue() + brandAmount;
                Transaction transaction = new Transaction(brandAmount, microfunditAmount, storyAmount);
                transactions.save(transaction);
                donation.getTransactions().add(transaction);
            }
            story.setCurrentAmount(story.getCurrentAmount() + simpleDonatedAmount);
            stories.save(story);
        }

    }

    private boolean isDateExpired(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        System.out.println(c);
        System.out.println(Calendar.getInstance());
        return Calendar.getInstance().after(c);
    }

    @HandleAfterCreate
    public void addTransactionsToSavedDonations(Donation donation) {
        for (Transaction t: donationTransactions) {
            t.setDonation(donation);
            transactions.save(t);
        }
        donationTransactions = new ArrayList<>();
    }
}

