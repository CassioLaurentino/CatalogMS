package com.cassiolaurentino.bookmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cassiolaurentino.bookmanager.dto.BookDTO;
import com.cassiolaurentino.bookmanager.entity.Book;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);
    
    Book toModel(BookDTO bookDTO);

    BookDTO toDTO(Book book);
}
