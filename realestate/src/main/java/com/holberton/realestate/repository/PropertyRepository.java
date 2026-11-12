package com.holberton.realestate.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.holberton.realestate.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByReference(String reference);
    boolean existsByReference(String reference);
    List<Property> findByAddressCityIgnoreCaseOrderByReference(String city);
    List<Property> findByMonthlyRentLessThanEqualOrderByReference(int maxRent);
    List<Property> findByAddressCityIgnoreCaseAndMonthlyRentLessThanEqualOrderByReference(String city, int maxRent);
    List<Property> findByOwnerIdOrderByReference(Long ownerId);
    boolean existsByOwnerId(Long ownerId);
}

