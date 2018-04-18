package com.microfundit.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Entity
public class PointsCompany extends BaseEntity {
    private String name;
    @OneToMany(mappedBy = "pointsCompany")
    private List<Donation> donations;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    protected PointsCompany() {
        super();
        donations = new ArrayList<>();
        dateAdded = new Date();
    }

    public PointsCompany(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void addDonation(Donation donation) {
        donation.setPointsCompany(this);
        donations.add(donation);
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
