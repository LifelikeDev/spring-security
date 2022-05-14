package com.example.spring.security.controller;

import com.example.spring.security.exception.UserServiceException;
import com.example.spring.security.model.AppUser;
import com.example.spring.security.model.AppUserRole;
import com.example.spring.security.service.AppUserServiceImplementation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userServiceImplementation.saveUser(user));
    }

    @PostMapping("/roles")
    public ResponseEntity<AppUserRole> addRole(@RequestBody AppUserRole userRole) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        return ResponseEntity.created(uri).body(userServiceImplementation.saveUserRole(userRole));
    }

    @PostMapping("/roles/user")
    public ResponseEntity<Integer> addRoleToUser(@RequestBody RoleToUserForm form) {
        userServiceImplementation.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok(201);
    }

}

@Getter
@Setter
class RoleToUserForm {
    private String username;
    private String roleName;
}
