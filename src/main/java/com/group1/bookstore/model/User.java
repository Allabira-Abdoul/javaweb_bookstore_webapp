package com.group1.bookstore.model;

import com.group1.bookstore.Dto.UserDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    
    @Column
    private String password;
    
    @Column(unique = true)
    private String email;
    
    @Column
    private String firstName;
    
    @Column
    private String lastName;

    @Column
    private String role;

    public static User toUser(UserDto dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setRole("USER");
        return user;
    }
}
