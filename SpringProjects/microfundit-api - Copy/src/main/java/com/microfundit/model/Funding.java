package com.microfundit.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Entity
public class Funding extends BaseEntity {
    @ManyToOne
    private Brand brand;
    private int placedAmount;
    private int currentAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    //Can either be CLOSED = 0 or OPENED = 1
    private int status;
    private int ratio;


    protected Funding() {
        super();
        dateAdded = new Date();
        status = 1;
    }

    public Funding(Brand brand, int placedAmount, int ratio) {
        this();
        this.brand = brand;
        this.placedAmount = placedAmount;
        this.ratio = ratio;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public int getPlacedAmount() {
        return placedAmount;
    }

    public void setPlacedAmount(int placedAmount) {
        this.placedAmount = placedAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
