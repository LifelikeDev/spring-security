package com.example.spring.security.controller;

import com.example.spring.security.exception.UserServiceException;
import com.example.spring.security.model.AppUser;
import com.example.spring.security.model.AppUserRole;
import com.example.spring.security.service.AppUserServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {
    private final AppUserServiceImplementation userServiceImplementation;

    @GetMapping("/users")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok().body(userServiceImplementation.getAllUsers());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<AppUser> getUser(@PathVariable("userId") String userId) throws UserServiceException {
        return ResponseEntity.ok().body(userServiceImplementation.getUser(userId));
    }

    @PostMapping("/users")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser user) {
        return ResponseEntity.ok().body(userServiceImplementation.saveUser(user));
    }

    @PostMapping("/roles")
    public ResponseEntity<AppUserRole> addRole(@RequestBody AppUserRole userRole) {
        return ResponseEntity.ok().body(userServiceImplementation.saveUserRole(userRole));
    }

    @PostMapping("/roles/{username}")
    public ResponseEntity<Integer> addRoleToUser(@PathVariable("username") String username, @RequestBody String userRole) {
        userServiceImplementation.addRoleToUser(username, userRole);
        return ResponseEntity.ok(201);
    }

}
