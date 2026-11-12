package com.holberton.realestate.service;

import com.holberton.realestate.dto.OwnerMapper;
import com.holberton.realestate.dto.OwnerRequest;
import com.holberton.realestate.dto.OwnerResponse;
import com.holberton.realestate.exception.DuplicateEmailException;
import com.holberton.realestate.exception.OwnerNotFoundException;
import com.holberton.realestate.model.Owner;
import com.holberton.realestate.repository.OwnerRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public List<OwnerResponse> findAll() {
        return repository.findAll(Sort.by("name")).stream()
            .map(OwnerMapper::toResponse)
            .toList();
    }

    public OwnerResponse findById(Long id) {
        return repository.findById(id)
            .map(OwnerMapper::toResponse)
            .orElseThrow(() -> new OwnerNotFoundException(String.valueOf(id)));
    }

    public OwnerResponse create(OwnerRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }
        Owner saved = repository.save(OwnerMapper.toEntity(request));
        return OwnerMapper.toResponse(saved);
    }
}
