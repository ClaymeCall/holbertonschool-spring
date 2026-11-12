package com.holberton.realestate.service;

import com.holberton.realestate.dto.PropertyRequest;
import com.holberton.realestate.dto.PropertyResponse;
import com.holberton.realestate.dto.PropertyMapper;
import com.holberton.realestate.repository.OwnerRepository;
import com.holberton.realestate.repository.PropertyRepository;
import com.holberton.realestate.model.Owner;
import com.holberton.realestate.model.Property;
import com.holberton.realestate.exception.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
public class PropertyService {
    private final PropertyRepository repository;
    private final OwnerRepository ownerRepository;

    public PropertyService(PropertyRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
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

    public PropertyResponse findByReference(String ref) {
        return repository.findByReference(ref)
            .map(PropertyMapper::toResponse)
            .orElseThrow(() -> new PropertyNotFoundException(ref));
    }

    public PropertyResponse create(PropertyRequest req) {
        if (repository.existsByReference(req.reference())){
            throw new DuplicateReferenceException(req.reference());
        }

        Property entity = PropertyMapper.toEntity(req);

        return PropertyMapper.toResponse(repository.save(entity));
    }

    public PropertyResponse update(String reference, PropertyRequest request) {
        return repository.findByReference(reference).map(
            p -> {
                p.setAddress(request.address());
                p.setSurface(request.surface());
                p.setMonthlyRent(request.monthlyRent());
                p.setEnergyClass(request.energyClass());
                return PropertyMapper.toResponse(repository.save(p));
            }
        )
        .orElseThrow(() -> (new PropertyNotFoundException(reference)));
    }

    public PropertyResponse assignOwner(String reference, Long ownerId) {
        Property property = repository.findByReference(reference)
            .orElseThrow(() -> new PropertyNotFoundException(reference));
        Owner owner = ownerRepository.findById(ownerId)
            .orElseThrow(() -> new OwnerNotFoundException(String.valueOf(ownerId)));

        property.setOwner(owner);
        return PropertyMapper.toResponse(repository.save(property));
    }

    public void delete(String ref) {
        Property p = repository.findByReference(ref)
        .orElseThrow(() -> (new PropertyNotFoundException(ref)));

        repository.delete(p);
    }
}
