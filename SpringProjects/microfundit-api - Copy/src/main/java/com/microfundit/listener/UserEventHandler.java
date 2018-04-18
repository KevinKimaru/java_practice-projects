package com.microfundit.listener;

import com.microfundit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Kevin Kimaru Chege on 4/4/2018.
 */
@Component
@RepositoryEventHandler(User.class)
public class UserEventHandler {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEventHandler(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @HandleBeforeCreate
    public void encodePassword(User user) {
        String password = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(password);
    }
}
