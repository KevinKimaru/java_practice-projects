package com.microfundit.model;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Entity
public class Donation extends BaseEntity {
    @ManyToOne
    private Donor donor;
    @ManyToMany
    private List<Brand> matchedBrands;
    @ManyToOne
    private Story story;
    //can either be from CASH or POINTS
    //$1 = 500 points
    private int amount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    //can either be CASH = 1 or POINTS = 0
    private int type;
    @ManyToOne
    private PointsCompany pointsCompany;
    @OneToMany(mappedBy = "donation")
    private List<Transaction> transactions;
    //In some special cases due to conflicting transactions some amount might already have been sent to the bank yet
    //and later does not find a match
    private int matchedAmount;

    protected Donation() {
        super();
        dateAdded = new Date();
        matchedBrands = new ArrayList<Brand>();
        transactions = new ArrayList<>();
    }

    public Donation(Donor donor, Story story, int amount, int type, PointsCompany pointsCompany) {
        this();
        this.donor = donor;
        this.story = story;
        this.amount = amount;
        this.type = type;
    }

    public int getMatchedAmount() {
        return matchedAmount;
    }

    public void setMatchedAmount(int matchedAmount) {
        this.matchedAmount = matchedAmount;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public List getMatchedBrands() {
        return matchedBrands;
    }

    public void setMatchedBrands(List<Brand> matchedBrands) {
        this.matchedBrands = matchedBrands;
    }


    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PointsCompany getPointsCompany() {
        return pointsCompany;
    }

    public void setPointsCompany(PointsCompany pointsCompany) {
        this.pointsCompany = pointsCompany;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction) {
        transaction.setDonation(this);
        transactions.add(transaction);
    }
}
