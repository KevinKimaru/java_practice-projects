package com.microfundit;

//import com.microfundit.service.CustomUserDetails;
//import com.microfundit.service.CustomUserDetailsService;
import com.microfundit.dao.UserRepository;
import com.microfundit.model.Role;
import com.microfundit.model.User;
import com.microfundit.service.StorageService;
import com.microfundit.service.StoryCloseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by Kevin Kimaru Chege on 3/21/2018.
 */
@SpringBootApplication
//@EnableResourceServer
@EnableScheduling
public class Application{
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;

    @Resource
    StorageService storageService;

    @Autowired
    StoryCloseService subscriptionService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public TaskScheduler scheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Autowired
//    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository users) throws Exception {
////        User admin = new User("henry", "henry", "henry", "henry", "henry@gmail.com",
////                "074534456", 1);
////        admin.setRoles(Arrays.asList(new Role("ROLE_ADMIN"), new Role("ROLE_USER")));
////        users.save(admin);
//        builder.userDetailsService(customUserDetailsService ).passwordEncoder(User.PASSWORD_ENCODER);
//    }
}
