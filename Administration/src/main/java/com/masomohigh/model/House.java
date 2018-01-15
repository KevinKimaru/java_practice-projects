package com.masomohigh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@IdClass(HouseKey.class)
@Entity
public class House implements Serializable {

    private static final long serialVersionUID = 4693636589912848921L;

    @Id
    @GeneratedValue
    protected int id;
    @Id
    @Column(unique = true)
    protected String name;
    @OneToMany
    protected Map<Integer, List<Teacher>> houseMasters;
    @OneToMany(mappedBy = "house")
    protected List<Student> students;
    @Temporal(TemporalType.DATE)
    protected Date openingDate;
    @ElementCollection
    @OneToMany
    protected Map<Integer, List<Student>> captains;

    public House() {
    }
    /**
     * Missing: id, housemaster, students, captains
     */
    public House(String name, Date openingDate) {
        this.name = name;
        this.openingDate = openingDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public Map<Integer, List<Teacher>> getHouseMasters() {
        return houseMasters;
    }

    public void setHouseMasters(Map<Integer, List<Teacher>> houseMasters) {
        this.houseMasters = houseMasters;
    }

    public Map<Integer, List<Student>> getCaptains() {
        return captains;
    }

    public void setCaptains(Map<Integer, List<Student>> captains) {
        this.captains = captains;
    }
}
