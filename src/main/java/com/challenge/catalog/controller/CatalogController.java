package com.challenge.catalog.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.dto.MessageResponseDTO;
import com.challenge.catalog.exception.ProductNotFoundException;
import com.challenge.catalog.service.ProductService;

@RestController
@RequestMapping("productInventory/producatManagement/v1")
public class CatalogController {
    
    private ProductService productService;

    @Autowired
    public CatalogController(ProductService productService) {
        this.productService = productService;
    }
    
    @PostMapping
    public MessageResponseDTO create(@RequestBody @Valid ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.findById(id);
    }
}
