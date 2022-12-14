package com.cassiolaurentino.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cassiolaurentino.bookmanager.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
