package com.workshop.judgev2.security;

import com.workshop.judgev2.model.entity.RoleName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@Getter
@Setter
@NoArgsConstructor
public class CurrentUser {

    private long id;

    private String username;

    private RoleName role;

    public boolean isAnonymous(){
        return this.username == null;
    }

    public boolean isAdmin(){
        return this.role == RoleName.ADMIN;
    }

}
