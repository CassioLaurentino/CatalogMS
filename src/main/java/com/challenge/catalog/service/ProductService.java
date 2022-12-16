package com.challenge.catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.dto.MessageResponseDTO;
import com.challenge.catalog.entity.Product;
import com.challenge.catalog.exception.ProductNotFoundException;
import com.challenge.catalog.mapper.ProductMapper;
import com.challenge.catalog.repository.CatalogRepository;

@Service
public class ProductService {
    
    private CatalogRepository catalogRepository;

    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Autowired
    public ProductService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }
    
    public MessageResponseDTO create(ProductDTO productDTO) {
        Product productToSave = productMapper.toModel(productDTO);
        Product savedProduct = catalogRepository.save(productToSave);
        return MessageResponseDTO.builder()
        .message("Product created with ID " + savedProduct.getId())
        .build();
    }

    public ProductDTO findById(Long id) throws ProductNotFoundException {
        Product product = catalogRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDTO(product);
    }
}
