package com.holberton.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.holberton.realestate.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    boolean existsByEmail(String email);
}
