//package com.microfundit.config;
//
//import com.microfundit.model.User;
//import com.microfundit.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Created by Kevin Kimaru Chege on 3/27/2018.
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Autowired
//    CustomUserDetailsService userDetailsService;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(User.PASSWORD_ENCODER);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.anonymous().disable()
//                .requestMatcher(request -> {
//                    String auth = request.getHeader(HttpHeaders.AUTHORIZATION);
//                    return (auth != null && auth.startsWith("Basic"));
//                })
//                .antMatcher("/**")
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
////                .and()
////                .csrf().disable();
//    }
//}
