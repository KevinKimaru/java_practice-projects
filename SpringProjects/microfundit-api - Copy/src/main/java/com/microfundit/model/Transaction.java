package com.microfundit.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Entity
public class Transaction extends BaseEntity {
    @ManyToOne
    private Donation donation;
    @Digits(integer = 10, fraction = 2)
    private double brandAmount;
    @Digits(integer = 10, fraction = 2)
    private double microfunditAmount;
    @Digits(integer = 10, fraction = 2)
    private double storyAmount;

    protected Transaction() {
        super();
    }

    public Transaction(double brandAmount, double microfunditAmount, double storyAmount) {
        this();
        this.brandAmount = brandAmount;
        this.microfunditAmount = microfunditAmount;
        this.storyAmount = storyAmount;
    }

    public Donation getDonation() {
        return donation;
    }

    public void setDonation(Donation donation) {
        this.donation = donation;
    }

    public double getBrandAmount() {
        return brandAmount;
    }

    public void setBrandAmount(double brandAmount) {
        this.brandAmount = brandAmount;
    }

    public double getMicrofunditAmount() {
        return microfunditAmount;
    }

    public void setMicrofunditAmount(double microfunditAmount) {
        this.microfunditAmount = microfunditAmount;
    }

    public double getStoryAmount() {
        return storyAmount;
    }

    public void setStoryAmount(double storyAmount) {
        this.storyAmount = storyAmount;
    }
}
