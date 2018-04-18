package com.kevin.user;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kevin Kimaru Chege on 3/6/2018.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
