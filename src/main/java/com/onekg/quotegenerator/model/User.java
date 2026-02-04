package com.onekg.quotegenerator.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString(exclude = "quotes")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @NotBlank(message = "Username can't be blank")
    @Size(min = 3, max = 50, message = "Size of username should be form 3 to 50 characters")
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @NotBlank(message = "Username can't be blank")
    @Email(message = "Format of email must be correct")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, message = "Password must have minimum 6 symbols")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Quote> quotes = new HashSet<>();

}
