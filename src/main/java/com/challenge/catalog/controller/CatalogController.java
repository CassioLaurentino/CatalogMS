package com.challenge.catalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.dto.MessageResponseDTO;
import com.challenge.catalog.exception.ProductNotFoundException;
import com.challenge.catalog.service.ProductService;

@RestController
@RequestMapping("productInventory/producatManagement/v1/products")
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

    @GetMapping()
    public List<ProductDTO> listAll() {
        return productService.listAll();
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws ProductNotFoundException {
        return productService.updateById(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deleteById(@PathVariable Long id) throws ProductNotFoundException {
        return productService.deleteById(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchByPrice() throws ProductNotFoundException {
        return productService.searchByPrice();
    }
}
