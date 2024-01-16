package com.codemarathon.bookstore.service;

import com.codemarathon.bookstore.dto.ApiResponse;
import com.codemarathon.bookstore.dto.BookRequest;


public interface BookService {

    ApiResponse createBook(BookRequest bookRequest);

    ApiResponse getAllBooks();

    void deleteProduct(Long bookId);

    ApiResponse updateBook(BookRequest bookRequest,Long bookId);


}
