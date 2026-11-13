package com.holberton.realestate.service;

import com.holberton.realestate.dto.TenantMapper;
import com.holberton.realestate.dto.TenantRequest;
import com.holberton.realestate.dto.TenantResponse;
import com.holberton.realestate.exception.DuplicateEmailException;
import com.holberton.realestate.exception.TenantNotFoundException;
import com.holberton.realestate.model.Tenant;
import com.holberton.realestate.repository.TenantRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantService {
    private final TenantRepository repository;

    public TenantService(TenantRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TenantResponse> findAll() {
        return repository.findAll(Sort.by("name")).stream()
            .map(TenantMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public TenantResponse findById(Long id) {
        return repository.findById(id)
            .map(TenantMapper::toResponse)
            .orElseThrow(() -> new TenantNotFoundException(String.valueOf(id)));
    }

    @Transactional
    public TenantResponse create(TenantRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new DuplicateEmailException(request.email());
        }
        Tenant saved = repository.save(TenantMapper.toEntity(request));
        return TenantMapper.toResponse(saved);
    }
}
