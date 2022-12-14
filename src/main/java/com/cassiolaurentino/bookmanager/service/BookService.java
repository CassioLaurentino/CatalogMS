package com.cassiolaurentino.bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassiolaurentino.bookmanager.dto.BookDTO;
import com.cassiolaurentino.bookmanager.dto.MessageResponseDTO;
import com.cassiolaurentino.bookmanager.entity.Author;
import com.cassiolaurentino.bookmanager.entity.Book;
import com.cassiolaurentino.bookmanager.mapper.BookMapper;
import com.cassiolaurentino.bookmanager.repository.BookRepository;

@Service
public class BookService {
    
    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public MessageResponseDTO create(BookDTO bookDTO) {
        Book BookToSave = bookMapper.toModel(bookDTO);
        Book savedBook = bookRepository.save(BookToSave);
        return MessageResponseDTO.builder()
        .message("Book created withi ID " + savedBook.getId())
        .build();
    }
}
