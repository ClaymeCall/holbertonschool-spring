package com.holberton.realestate.controller;

import com.holberton.realestate.dto.OwnerRequest;
import com.holberton.realestate.dto.OwnerResponse;
import com.holberton.realestate.service.OwnerService;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerService service;

    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @GetMapping
    public List<OwnerResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OwnerResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> create(@Valid @RequestBody OwnerRequest request) {
        OwnerResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/owners/" + created.id())).body(created);
    }
}
