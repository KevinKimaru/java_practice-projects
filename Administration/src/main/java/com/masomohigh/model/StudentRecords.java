package com.masomohigh.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class StudentRecords implements Serializable {
    private static final long serialVersionUID = 4685631589912848921L;

    @Id
    @GeneratedValue
    protected int studentRecordId;
    @ManyToOne
    protected Student student;
    @OneToMany(mappedBy = "studentRecords")
    protected List<Record> records;
    @ManyToOne
    @JoinColumn(name = "examId")
    protected Exam exam;

    public StudentRecords() {
    }

    /**Missing: recrds*/
    public StudentRecords(Student student, Exam exam) {
        this.student = student;
        this.exam = exam;
    }

    public int getStudentRecordId() {
        return studentRecordId;
    }

    public void setStudentRecordId(int studentRecordId) {
        this.studentRecordId = studentRecordId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
