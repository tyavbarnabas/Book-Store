package com.codemarathon.bookstore;

import com.codemarathon.bookstore.dto.ApiResponse;
import com.codemarathon.bookstore.dto.BookRequest;
import com.codemarathon.bookstore.exception.BooKAlreadyExistException;
import com.codemarathon.bookstore.exception.BookNotFoundException;
import com.codemarathon.bookstore.model.Book;
import com.codemarathon.bookstore.repository.BookRepository;
import com.codemarathon.bookstore.serviceImpl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookStoreApplicationTests {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks

    private BookServiceImpl bookService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void createBook_Success() {
        // Arrange
        BookRequest bookRequest = new BookRequest("Sample Book", "John Doe", null, 29.99, "Available", LocalDateTime.now());


        when(bookRepository.findByTitle(any())).thenReturn(Optional.empty());

        when(bookRepository.save(any())).thenReturn(new Book());


        ApiResponse response = bookService.createBook(bookRequest);


        assertEquals("000", response.getResponseCode());
        assertEquals("success", response.getMessage());
        assertNotNull(response.getDetails());


        Mockito.verify(bookRepository, Mockito.times(1)).findByTitle(any());
        Mockito.verify(bookRepository, Mockito.times(1)).save(any());
    }


    @Test
    void createBook_BookAlreadyExists() {

        BookRequest bookRequest = new BookRequest("Sample Book", "John Doe", null, 29.99, "Available", LocalDateTime.now());


        when(bookRepository.findByTitle(any())).thenReturn(Optional.of(new Book()));


        assertThrows(BooKAlreadyExistException.class, () -> bookService.createBook(bookRequest));


        Mockito.verify(bookRepository, Mockito.times(1)).findByTitle(any());

        Mockito.verify(bookRepository, Mockito.never()).save(any());
    }



}
