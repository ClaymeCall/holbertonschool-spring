package com.holberton.realestate.service;

import com.holberton.realestate.dto.PropertyRequest;
import com.holberton.realestate.dto.PropertyResponse;
import com.holberton.realestate.dto.PropertyMapper;
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

    public List<PropertyResponse> findAll() {
        return repository.findAll(Sort.by("reference")).stream().map(PropertyMapper::toResponse).toList();
    }

    public List<PropertyResponse> search (String city, Integer maxRent) {
        List<Property> result = null;

        if (city != null && maxRent != null) {
            result = repository.findByAddressCityIgnoreCaseAndMonthlyRentLessThanEqualOrderByReference(city, maxRent);
        }
        else if (city != null) {
            result = repository.findByAddressCityIgnoreCaseOrderByReference(city);
        }
        else if (maxRent != null) {
            result = repository.findByMonthlyRentLessThanEqualOrderByReference(maxRent);
        }
        else return findAll();
        
        return result
        .stream()
        .map(PropertyMapper::toResponse)
        .toList();
    }

    public Optional<PropertyResponse> findByReference(String ref) {
        return repository.findByReference(ref)
            .map(PropertyMapper::toResponse);
    }

    public Optional<PropertyResponse> create(PropertyRequest req) {
        if (repository.existsByReference(req.reference())) return Optional.empty();

        Property entity = PropertyMapper.toEntity(req);

        return Optional.of(PropertyMapper.toResponse(repository.save(entity)));
    }

    public Optional<PropertyResponse> update(String reference, PropertyRequest request) {
        return repository.findByReference(reference).map(
            p -> {
                p.setAddress(request.address());
                p.setSurface(request.surface());
                p.setMonthlyRent(request.monthlyRent());
                p.setEnergyClass(request.energyClass());
                return PropertyMapper.toResponse(repository.save(p));
            }
        );
    }

    public boolean delete(String ref) {
        return repository.findByReference(ref).map(p -> { repository.delete(p); return true; }).orElse(false);
    }
}
