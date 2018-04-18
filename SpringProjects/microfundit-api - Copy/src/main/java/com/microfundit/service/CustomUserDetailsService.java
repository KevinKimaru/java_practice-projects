//package com.microfundit.service;
//
//import com.microfundit.dao.UserRepository;
//import com.microfundit.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
///**
// * Created by Kevin Kimaru Chege on 3/27/2018.
// */
//@Component
//public class CustomUserDetailsService implements UserDetailsService {
//    @Autowired
//    UserRepository users;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = users.findByUsername(username);
//        if(user == null) {
//            throw new UsernameNotFoundException(username + " was not found.");
//        }
//        return new CustomUserDetails(user);
//    }
//}
