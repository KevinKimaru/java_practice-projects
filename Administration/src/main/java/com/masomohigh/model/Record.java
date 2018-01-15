package com.masomohigh.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Record implements Serializable {
    private static final long serialVersionUID = 4685631589912848921L;

    @Id
    @GeneratedValue
    protected int recordId;
    protected String subject;
    protected String marks;
    @ManyToOne
    protected Teacher teacher;
    protected String remark;
    protected char grade;
    @ManyToOne
    @JoinColumn(name = "studentRecordId")
    protected StudentRecords studentRecords;
    @ManyToOne
    protected Exam exam;

    public Record() {}

    /**Missing: grade will be created*/
    public Record(String subject, String marks, Teacher teacher, String remark, StudentRecords studentRecords, Exam exam) {
        this.subject = subject;
        this.marks = marks;
        this.teacher = teacher;
        this.remark = remark;
        this.studentRecords = studentRecords;
        this.exam = exam;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public StudentRecords getStudentRecords() {
        return studentRecords;
    }

    public void setStudentRecords(StudentRecords studentRecords) {
        this.studentRecords = studentRecords;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
