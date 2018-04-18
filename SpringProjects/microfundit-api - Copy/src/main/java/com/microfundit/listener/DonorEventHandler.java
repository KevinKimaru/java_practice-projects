package com.microfundit.listener;

import com.microfundit.model.Donor;
import com.microfundit.model.User;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@Component
@RepositoryEventHandler(Donor.class)
public class DonorEventHandler {
    @HandleBeforeCreate
    private void setDefaultsBeforeAddingUser(Donor donor) {
        donor.setDateAdded(new Date());
    }
}
