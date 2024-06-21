package com.serviciosmicro.companies_crud.services;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.serviciosmicro.companies_crud.entities.Category;
import com.serviciosmicro.companies_crud.entities.Company;
import com.serviciosmicro.companies_crud.repositories.CompanyRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    @Override
    public Company create(Company company) {
        company.getWebSites().forEach(siteWeb -> {
            siteWeb.setCategory(siteWeb.getCategory() == null ? Category.NONE : siteWeb.getCategory());
        });
        return this.companyRepository.save(company);
    }

    @Override
    public Company readByName(String name) {
        return this.companyRepository.findByName(name)
            .orElseThrow(() -> new NoSuchElementException("Company not found"));
    }

    @Override
    public Company update(Company company, String name) {
        var companyToUpdate=this.companyRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));
        companyToUpdate.setLogo(company.getLogo());
        companyToUpdate.setFounder(company.getFounder());
        companyToUpdate.setFoundationDate(company.getFoundationDate());
        return this.companyRepository.save(companyToUpdate);

    }

    @Override
    public void delete(String name) {
        var companyToDelete=this.companyRepository.findByName(name)
        .orElseThrow(() -> new NoSuchElementException("Company not found"));
        this.companyRepository.delete(companyToDelete);
    }
}
