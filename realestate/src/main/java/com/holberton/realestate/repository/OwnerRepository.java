package com.holberton.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.holberton.realestate.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    boolean existsByEmail(String email);
}
