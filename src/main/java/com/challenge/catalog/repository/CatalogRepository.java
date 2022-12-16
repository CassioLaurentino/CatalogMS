package com.challenge.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.catalog.entity.Product;

public interface CatalogRepository extends JpaRepository<Product, Long>{
    
}
