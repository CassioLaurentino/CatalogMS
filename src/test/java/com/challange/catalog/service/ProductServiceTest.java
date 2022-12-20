package com.challange.catalog.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challange.catalog.utils.ProductUtils;
import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.entity.Product;
import com.challenge.catalog.exception.ProductNotFoundException;
import com.challenge.catalog.repository.CatalogRepository;
import com.challenge.catalog.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    
    @Mock
    private CatalogRepository catalogRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void whenGivenExistingIdThenReturnThisProduct() throws ProductNotFoundException {
        Product expectedFoundProduct = ProductUtils.createFakeProduct();

        Mockito.when(catalogRepository.findById(expectedFoundProduct.getId())).thenReturn(Optional.of(expectedFoundProduct));

        ProductDTO productDTO = productService.findById(expectedFoundProduct.getId());

        Assertions.assertEquals(expectedFoundProduct.getName(), productDTO.getName());
        Assertions.assertEquals(expectedFoundProduct.getDescription(), productDTO.getDescription());
        Assertions.assertEquals(expectedFoundProduct.getPrice(), productDTO.getPrice());
    }

    @Test
    void whenGivenUnexistingIdThenNotFindThrowAnException() {
        var invalidId = 10L;

        Mockito.when(catalogRepository.findById(invalidId))
                .thenReturn(Optional.ofNullable(any(Product.class)));

        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findById(invalidId));
    }
    
}
