package com.challenge.catalog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<ProductDTO> listAll() {
        List<Product> products = catalogRepository.findAll();
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(product -> productsDTO.add(productMapper.toDTO(product)));
        return productsDTO;
    }

    public List<ProductDTO> searchByPrice() {
        List<Product> products = catalogRepository.findAll(Sort.by(Sort.Direction.ASC, "price"));
        List<ProductDTO> productsDTO = new ArrayList<>();
        products.forEach(product -> productsDTO.add(productMapper.toDTO(product)));
        return productsDTO;
    }

    public ProductDTO findById(Long id) throws ProductNotFoundException {
        Product product = catalogRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toDTO(product);
    }

    public MessageResponseDTO updateById(Long id, ProductDTO productDTO) throws ProductNotFoundException {
        Product productToUpdate = catalogRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productToUpdate = productMapper.toModel(productDTO);
        productToUpdate.setId(id);
        Product savedProduct = catalogRepository.save(productToUpdate);
        return MessageResponseDTO.builder()
        .message("Product updated with ID " + savedProduct.getId())
        .build();
    }

    public MessageResponseDTO deleteById(Long id) throws ProductNotFoundException {
        catalogRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        catalogRepository.deleteById(id);
        return MessageResponseDTO.builder()
        .message("Product deleted with ID " + id)
        .build();
    }
}
