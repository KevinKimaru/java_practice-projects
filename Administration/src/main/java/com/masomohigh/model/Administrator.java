package com.masomohigh.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Kevin Kimaru Chege on 12/10/2017.
 */
@Entity
public class Administrator extends Staff implements Serializable {
    private static final long serialVersionUID = 8942582153559426625L;

    public Administrator() {
    }

    /**Missing: obligations, obligationsBackup, achievements*/
    public Administrator(int idNumber, String firstName, String middleName, String lastName, String password, Date dateOfBirth, String phoneNumber,
                         String email, Address address, Date dateOfStart, String status) {
        super(idNumber, firstName, middleName, lastName, password, dateOfBirth, phoneNumber, email, address, dateOfStart, status);
    }
}
