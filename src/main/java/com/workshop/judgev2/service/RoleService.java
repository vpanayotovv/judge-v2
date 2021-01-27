package com.workshop.judgev2.service;


import com.workshop.judgev2.model.entity.Role;
import com.workshop.judgev2.model.entity.RoleName;

public interface RoleService {
    void importRoles();

    Role getRole(RoleName roleName);
}
