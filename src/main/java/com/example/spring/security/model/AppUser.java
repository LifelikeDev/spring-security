package com.example.spring.security.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class AppUser {
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    @DBRef
    private Collection<AppUserRole> roles = new ArrayList<>();

}
