package com.example.spring.security.service;

import com.example.spring.security.exception.UserServiceException;
import com.example.spring.security.model.AppUser;
import com.example.spring.security.model.AppUserRole;

import java.util.List;

public interface AppUserService {
    AppUser getUser(String userId) throws UserServiceException;

    List<AppUser> getAllUsers();

    AppUser saveUser(AppUser user);

    AppUserRole saveUserRole(AppUserRole userRole);

    void addRoleToUser(String userId, String roleName);
}
