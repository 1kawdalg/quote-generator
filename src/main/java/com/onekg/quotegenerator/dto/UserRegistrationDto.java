package com.onekg.quotegenerator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotBlank(message = "Username can't be blank")
    @Size(min = 3, max = 50, message = "Size of username should be form 3 to 50 characters")
    private String username;

    @NotBlank(message = "Username can't be blank")
    @Email(message = "Format of email must be correct")
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 6, message = "Password must have minimum 6 symbols")
    private String password;

    @NotBlank(message = "Confirm of password can't be blank")
    private String confirmPassword;

}
