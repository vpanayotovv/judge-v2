package com.workshop.judgev2.service.Impl;

import com.workshop.judgev2.model.entity.Role;
import com.workshop.judgev2.model.entity.RoleName;
import com.workshop.judgev2.repository.RoleRepository;
import com.workshop.judgev2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void importRoles() {

        if (this.roleRepository.count() == 0){
            for (RoleName value : RoleName.values()) {
                Role role = new Role(value);
                this.roleRepository.saveAndFlush(role);
            }
        }

    }

    @Override
    public Role getRole(RoleName roleName) {
       return this.roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Invalid Role"));
    }
}
