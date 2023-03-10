package com.challenge.catalog.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.challenge.catalog.dto.ProductDTO;
import com.challenge.catalog.entity.Product;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    
    Product toModel(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
