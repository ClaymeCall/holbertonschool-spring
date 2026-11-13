package com.holberton.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.holberton.realestate.model.Lease;

public interface LeaseRepository extends JpaRepository<Lease, Long> {
    boolean existsByPropertyIdAndActiveTrue(Long propertyId);
}
