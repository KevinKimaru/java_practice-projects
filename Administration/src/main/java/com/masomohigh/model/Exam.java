package com.masomohigh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Exam implements Serializable {
    private static final long serialVersionUID = 8942582153559426625L;

    @Id
    @GeneratedValue
    protected int examId;
    protected String name;
    @Temporal(TemporalType.DATE)
    protected Date examDate;
    protected int form;
    protected int mYear;
    protected int term;
    @OneToMany(mappedBy = "exam")
    protected List<StudentRecords> studentRecords;

    public Exam() {}

    /**Missing: studentRecords*/
    public Exam(String name, Date examDate, int form, int mYear, int term) {
        this.name = name;
        this.examDate = examDate;
        this.form = form;
        this.mYear = mYear;
        this.term = term;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getForm() {
        return form;
    }

    public void setForm(int form) {
        this.form = form;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public List<StudentRecords> getStudentRecords() {
        return studentRecords;
    }

    public void setStudentRecords(List<StudentRecords> studentRecords) {
        this.studentRecords = studentRecords;
    }
}
