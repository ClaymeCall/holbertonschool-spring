package com.holberton.realestate.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.holberton.realestate.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByReference(String reference);
    boolean existsByReference(String reference);
}

