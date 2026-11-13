package com.holberton.realestate.service;

import com.holberton.realestate.dto.LeaseMapper;
import com.holberton.realestate.dto.LeaseRequest;
import com.holberton.realestate.dto.LeaseResponse;
import com.holberton.realestate.exception.InsufficientIncomeException;
import com.holberton.realestate.exception.LeaseNotFoundException;
import com.holberton.realestate.exception.PropertyNotFoundException;
import com.holberton.realestate.exception.PropertyUnavailableException;
import com.holberton.realestate.exception.TenantNotFoundException;
import com.holberton.realestate.model.Lease;
import com.holberton.realestate.model.Property;
import com.holberton.realestate.model.Tenant;
import com.holberton.realestate.repository.LeaseRepository;
import com.holberton.realestate.repository.PropertyRepository;
import com.holberton.realestate.repository.TenantRepository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaseService {
    public static final int INCOME_RENT_RATIO = 3;
    public static final int MIN_DURATION_MONTHS = 12;
    public static final int MAX_DURATION_MONTHS = 36;
    public static final int DEPOSIT_MONTHS = LeaseMapper.DEPOSIT_MONTHS;

    private final LeaseRepository repository;
    private final PropertyRepository propertyRepository;
    private final TenantRepository tenantRepository;

    public LeaseService(LeaseRepository repository, PropertyRepository propertyRepository,
            TenantRepository tenantRepository) {
        this.repository = repository;
        this.propertyRepository = propertyRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional(readOnly = true)
    public List<LeaseResponse> findAll() {
        return repository.findAll(Sort.by("id")).stream()
            .map(LeaseMapper::toResponse)
            .toList();
    }

    @Transactional(readOnly = true)
    public LeaseResponse findById(Long id) {
        return repository.findById(id)
            .map(LeaseMapper::toResponse)
            .orElseThrow(() -> new LeaseNotFoundException(id));
    }

    @Transactional
    public LeaseResponse create(LeaseRequest request) {
        Property property = propertyRepository.findByReference(request.propertyReference())
            .orElseThrow(() -> new PropertyNotFoundException(request.propertyReference()));

        Tenant tenant = tenantRepository.findById(request.tenantId())
            .orElseThrow(() -> new TenantNotFoundException(String.valueOf(request.tenantId())));

        if (repository.existsByPropertyIdAndActiveTrue(property.getId())) {
            throw new PropertyUnavailableException(property.getReference());
        }

        int requiredIncome = INCOME_RENT_RATIO * property.getMonthlyRent();
        if (tenant.getMonthlyIncome() < requiredIncome) {
            throw new InsufficientIncomeException(tenant.getId(), tenant.getMonthlyIncome(), requiredIncome);
        }

        Lease lease = new Lease(property, tenant, request.durationMonths(), property.getMonthlyRent());
        return LeaseMapper.toResponse(repository.save(lease));
    }
}
