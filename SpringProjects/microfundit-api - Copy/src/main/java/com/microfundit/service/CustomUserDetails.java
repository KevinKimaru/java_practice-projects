//package com.microfundit.service;
//
//import com.microfundit.model.Role;
//import com.microfundit.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by Kevin Kimaru Chege on 3/26/2018.
// */
//public class CustomUserDetails implements UserDetails {
//    private String username;
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public CustomUserDetails(User user) {
//        this.username = user.getUsername();
//        this.password = user.getPassword();
//        List<GrantedAuthority> auths = new ArrayList<>();
//        for (Role role : user.getRoles()) {
//            String name = role.getName().toUpperCase();
//            //Make sure that all roles start with "ROLE_"
//            if (!name.startsWith("ROLE_"))
//                name = "ROLE_" + name;
//            auths.add(new SimpleGrantedAuthority(name));
//        }
//        this.authorities = auths;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
