package com.example.spring.security.service;

import com.example.spring.security.exception.UserServiceException;
import com.example.spring.security.model.AppUser;
import com.example.spring.security.model.AppUserRole;
import com.example.spring.security.repository.AppUserRepository;
import com.example.spring.security.repository.AppUserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class AppUserServiceImplementation implements AppUserService, UserDetailsService {
    private final AppUserRepository userRepository;
    private final AppUserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);

        if (appUser == null) {
            log.error("User with username {} not found", username);
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User with username {} found", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser getUser(String userId) throws UserServiceException {
        Optional<AppUser> currentUser = userRepository.findById(userId);

        if(currentUser.isEmpty()) {
            throw new UserServiceException(UserServiceException.NotFoundException(userId));
        }

        log.info("Get user information...");
        return currentUser.get();
    }

    @Override
    public List<AppUser> getAllUsers() {
        List<AppUser> allUsers = userRepository.findAll();

        if(allUsers.size() <= 0) {
            return new ArrayList<>();
        }

        log.info("Get all users...");
        return allUsers;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving user...");
        return userRepository.save(user);
    }

    @Override
    public AppUserRole saveUserRole(AppUserRole userRole) {
        log.info("Saving user role...");
        return userRoleRepository.save(userRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        AppUserRole role = userRoleRepository.findByName(roleName);

        log.info("Adding role to user...");
        user.getRoles().add(role);
    }

}
