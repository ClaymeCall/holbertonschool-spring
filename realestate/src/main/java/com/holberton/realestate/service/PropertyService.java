package com.holberton.realestate.service;

import com.holberton.realestate.dto.PropertyRequest;
import com.holberton.realestate.repository.PropertyRepository;
import com.holberton.realestate.model.Property;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class PropertyService {
    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> findAll() {
        return repository.findAll(Sort.by("reference"));
    }

    public Optional<Property> findByReference(String ref) {
        return repository.findByReference(ref);
    }

    public Optional<Property> create(PropertyRequest req) {
        if (repository.existsByReference(req.reference())) return Optional.empty();

        Property property = new Property(
            req.reference(),
            req.address(),
            req.surface(),
            req.monthlyRent(),
            req.energyClass()
        );
        return Optional.of(repository.save(property));
    }

    public Optional<Property> update(String reference, PropertyRequest request) {
        Optional<Property> found = findByReference(reference);
        found.ifPresent(p -> {
            p.setAddress(request.address());
            p.setSurface(request.surface());
            p.setMonthlyRent(request.monthlyRent());
            p.setEnergyClass(request.energyClass());

            repository.save(p);
        });
        return found;
    }

    public boolean delete(String ref) {
        return repository.findByReference(ref).map(p -> { repository.delete(p); return true; }).orElse(false);
    }
}
