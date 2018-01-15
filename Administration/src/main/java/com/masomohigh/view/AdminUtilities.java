package com.masomohigh.view;

import com.masomohigh.controller.AdminController;
import com.masomohigh.model.Administrator;
import com.masomohigh.model.Staff;
import com.masomohigh.model.Teacher;

import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 12/23/2017.
 */
public class AdminUtilities {
    public static final String SCHOOL_NAME = "MASOMO HIGH SCHOOL";
    public static final String ADDRESS = "P.O. BOX 245-0100\n" +
            "Nairobi.";

    public static boolean ADMINISTRATOR_LOGGED_IN = false;
    public static Administrator LOGGED_IN_ADMIN = null;
    public static int SELECTED_ADMINISTRATOR_ID = 0;
    public static Administrator SELECTED_ADMINISTRATOR = null;

    public static void setSelectedAdministrator() {
        SELECTED_ADMINISTRATOR = AdminController.getAdministratorDetails(SELECTED_ADMINISTRATOR_ID);
        if (SELECTED_ADMINISTRATOR != null) {
            SELECTED_ADMINISTRATOR_ID = SELECTED_ADMINISTRATOR.getIdNumber();
        }
    }

    public static List<Administrator> getAllAdministrators() {
        return AdminController.fetchAllAdministrators();
    }
}
