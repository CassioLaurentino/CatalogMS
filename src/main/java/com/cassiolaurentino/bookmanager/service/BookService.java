package com.cassiolaurentino.bookmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cassiolaurentino.bookmanager.dto.MessageResponseDTO;
import com.cassiolaurentino.bookmanager.entity.Book;
import com.cassiolaurentino.bookmanager.repository.BookRepository;

@Service
public class BookService {
    
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public MessageResponseDTO create(Book book) {
        Book savedBook = bookRepository.save(book);
        return MessageResponseDTO.builder()
        .message("Book created withi ID " + savedBook.getId())
        .build();
    }
}
