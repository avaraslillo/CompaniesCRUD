package com.serviciosmicro.companies_crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serviciosmicro.companies_crud.entities.WebSite;

public interface WebSiteRepository extends JpaRepository<WebSite, Long> {

}
