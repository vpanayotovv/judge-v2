package com.workshop.judgev2.repository;

import com.workshop.judgev2.model.entity.Role;
import com.workshop.judgev2.model.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
