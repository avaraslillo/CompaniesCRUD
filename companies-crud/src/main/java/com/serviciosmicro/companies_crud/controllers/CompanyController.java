package com.serviciosmicro.companies_crud.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serviciosmicro.companies_crud.entities.Company;
import com.serviciosmicro.companies_crud.services.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@AllArgsConstructor
@RequestMapping(path="/company")
@Slf4j
@Tag(name = "Companies Resource")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping(path="/{name}")
    @Operation(summary = "Get company by name")
    public ResponseEntity<Company> get(@PathVariable String name) {
        log.info("Get company by name {}", name);
        var company = this.companyService.readByName(name);
        return ResponseEntity.ok(company);
    }

    @PostMapping
    @Operation(summary = "Create a new company")
    public ResponseEntity<Company> post(@RequestBody Company company) {
        log.info("Create company {}", company.getName());
        var companyCreated = this.companyService.create(company);
        return ResponseEntity.created(URI.create(companyCreated.getName())).build();
    }

    @PutMapping(path="/{name}")
    @Operation(summary = "Update a company by name")
    public ResponseEntity<Company> put(@RequestBody Company company,@PathVariable String name) {
        log.info("Update company {}", name);
        var companyUpdated = this.companyService.update(company, name);
        return ResponseEntity.ok(companyUpdated);
    }

    @DeleteMapping(path="/{name}")
    @Operation(summary = "Delete a company by name")
    public ResponseEntity<?> delete(@PathVariable String name) {
        log.info("Delete company {}", name);
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }
    
}
