package com.example.spring.security.repository;

import com.example.spring.security.model.AppUserRole;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRoleRepository extends MongoRepository<AppUserRole, String> {
    AppUserRole findByName(String name);
}
