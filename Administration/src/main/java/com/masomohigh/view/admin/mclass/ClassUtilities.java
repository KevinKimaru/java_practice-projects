package com.masomohigh.view.admin.mclass;

import com.masomohigh.controller.ClassController;
import com.masomohigh.model.Class;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin Kimaru Chege on 1/10/2018.
 */
public class ClassUtilities {

    public static Set<String> AVAILABLE_CLASSES = new HashSet<>();

    public static void setAvailableClasses() {
        List<Class> classes = ClassController.fetchAllClasses();
        for (Class c: classes) {
            AVAILABLE_CLASSES.add(c.getName());
        }
    }

}
