package com.example.spring.security;

import com.example.spring.security.model.AppUser;
import com.example.spring.security.model.AppUserRole;
import com.example.spring.security.service.AppUserServiceImplementation;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppUserServiceImplementation userService) {
        return args -> {
            AppUserRole roleOne = new AppUserRole(null, "Secretary");
            AppUserRole roleTwo = new AppUserRole(null, "Chief Operating Officer");
            AppUserRole roleThree = new AppUserRole(null, "Administrator");
            AppUserRole roleFour = new AppUserRole(null, "Resource Manager");
            AppUserRole roleFive = new AppUserRole(null, "Executive");

            // add user role
            userService.saveUserRole(roleOne);
            userService.saveUserRole(roleTwo);
            userService.saveUserRole(roleThree);
            userService.saveUserRole(roleFour);

            AppUser userOne = new AppUser(null, "Julian Anderson", "j_anders", "jsanduers", new ArrayList<>());
            AppUser userTwo = new AppUser(null, "Andrews Leigh", "leigh-andy", "supers9274", new ArrayList<>());
            AppUser userThree = new AppUser(null, "Patricia Nuamah", "pat-the-lady", "pat23451", new ArrayList<>());
            AppUser userFour = new AppUser(null, "Kyle Prinsloo", "webdevsimplified", "webdsimp2022", new ArrayList<>());

            // save user
            userService.saveUser(userOne);
            userService.saveUser(userTwo);
            userService.saveUser(userThree);
            userService.saveUser(userFour);

            // add role to user
            userService.addRoleToUser(userOne.getUsername(), roleOne.getName());
            userService.addRoleToUser(userOne.getUsername(), roleFive.getName());
            userService.addRoleToUser(userTwo.getUsername(), roleTwo.getName());
            userService.addRoleToUser(userTwo.getUsername(), roleFive.getName());
            userService.addRoleToUser(userThree.getUsername(), roleThree.getName());
            userService.addRoleToUser(userThree.getUsername(), roleFour.getName());
            userService.addRoleToUser(userThree.getUsername(), roleFive.getName());
        };
    }

}
