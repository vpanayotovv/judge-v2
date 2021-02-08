package com.workshop.judgev2.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    private String confirmPassword;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "(?:git@|https://)github.com[:/](.*)" , message = "Github address is not valid")
    private String git;
}
