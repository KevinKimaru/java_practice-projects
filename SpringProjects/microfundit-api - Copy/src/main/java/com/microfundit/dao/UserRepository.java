package com.microfundit.dao;

import com.microfundit.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@CrossOrigin(origins = "http://localhost:4200")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
