package com.masomohigh.view.admin.tableDataModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Kevin Kimaru Chege on 1/10/2018.
 */
public class ClassDataModel {
    private SimpleStringProperty name;
    private SimpleStringProperty form;
    private SimpleStringProperty stream;
    private SimpleStringProperty year;
    private SimpleStringProperty classTeacher;

    public ClassDataModel(String name, String form, String stream, String year, String classTeacher) {
        this.name = new SimpleStringProperty(name);
        this.form = new SimpleStringProperty(form);
        this.stream = new SimpleStringProperty(stream);
        this.year = new SimpleStringProperty(year);
        this.classTeacher = new SimpleStringProperty(classTeacher);
    }

    public String getName() {
        return name.get();
    }

    public String getForm() {
        return form.get();
    }

    public String getStream() {
        return stream.get();
    }

    public String getYear() {
        return year.get();
    }

    public String getClassTeacher() {
        return classTeacher.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setForm(String form) {
        this.form.set(form);
    }

    public void setStream(String stream) {
        this.stream.set(stream);
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher.set(classTeacher);
    }
}
