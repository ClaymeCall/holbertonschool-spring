package com.holberton.realestate.service;

import com.holberton.realestate.dto.OwnerMapper;
import com.holberton.realestate.dto.OwnerRequest;
import com.holberton.realestate.dto.OwnerResponse;
import com.holberton.realestate.dto.PropertyMapper;
import com.holberton.realestate.dto.PropertyResponse;
import com.holberton.realestate.exception.DuplicateEmailException;
import com.holberton.realestate.exception.OwnerHasPropertiesException;
import com.holberton.realestate.exception.OwnerNotFoundException;
import com.holberton.realestate.model.Owner;
import com.holberton.realestate.repository.OwnerRepository;
import com.holberton.realestate.repository.PropertyRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OwnerService {
    private final OwnerRepository repository;
    private final PropertyRepository propertyRepository;

    public OwnerService(OwnerRepository repository, PropertyRepository propertyRepository) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
    }

    @Transactional(readOnly = true)
    public List<OwnerResponse> findAll() {
        return repository.findAll(Sort.by("name")).stream()
            .map(OwnerMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public OwnerResponse findById(Long id) {
        return repository.findById(id)
            .map(OwnerMapper::toResponse)
            .orElseThrow(() -> new OwnerNotFoundException(String.valueOf(id)));
    }

    @Transactional(readOnly = true)
    public List<PropertyResponse> findProperties(Long id) {
        if (!repository.existsById(id)) {
            throw new OwnerNotFoundException(String.valueOf(id));
        }
        return propertyRepository.findByOwnerIdOrderByReference(id).stream()
            .map(PropertyMapper::toResponse)
            .toList();
    }

    @Transactional
    public void delete(Long id) {
        Owner owner = repository.findById(id)
            .orElseThrow(() -> new OwnerNotFoundException(String.valueOf(id)));
        if (propertyRepository.existsByOwnerId(id)) {
            throw new OwnerHasPropertiesException(id);
        }
        repository.delete(owner);
    }

    @Transactional
    public OwnerResponse create(OwnerRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }
        Owner saved = repository.save(OwnerMapper.toEntity(request));
        return OwnerMapper.toResponse(saved);
    }
}
