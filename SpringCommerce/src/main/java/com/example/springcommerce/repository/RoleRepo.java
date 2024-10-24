package com.example.springcommerce.repository;

import com.example.springcommerce.entity.roleEntity;
import com.example.springcommerce.utils.Enums.AppRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<roleEntity, Long> {
    Optional<roleEntity> findByRoleName(AppRoles role);

}
