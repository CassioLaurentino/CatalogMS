package com.challange.catalog.utils;

import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.entity.Product;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;

public class ProductUtils {
    
    private static final Faker faker = Faker.instance();

    public static ProductDTO createFakeProductDTO() {
        return ProductDTO.builder()
                .id(faker.number().randomNumber())
                .name(faker.name().fullName())
                .description(faker.random().toString())
                .price(((float)faker.number().randomDouble(2, 0, 1000)))
                .build();
    }

    public static Product createFakeProduct() {
        return Product.builder()
                .id(faker.number().randomNumber())
                .name(faker.name().fullName())
                .description(faker.random().toString())
                .price(((float)faker.number().randomDouble(2, 0, 1000)))
                .build();
    }

    public static Product createFakeProductFrom(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }

    public static String asJsonString(ProductDTO productDTO) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModules(new JavaTimeModule());

            return objectMapper.writeValueAsString(productDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

