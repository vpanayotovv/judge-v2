package com.workshop.judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotNull
    @Length(min = 2,message = "Username must be minimum 2 characters")
    private String username;

    @NotNull
    @Length(min = 2,message = "Password must be minimum 2 characters")
    private String password;

    @NotNull
    @Length(min = 2,message = "Password must be minimum 2 characters")
    private String confirmPassword;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String git;
}
