package com.codemarathon.bookstore.controller;


import com.codemarathon.bookstore.dto.ApiResponse;
import com.codemarathon.bookstore.dto.BookRequest;
import com.codemarathon.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/create-book")
    public ApiResponse createBook(@Valid  @RequestBody BookRequest bookRequest) {
        return bookService.createBook(bookRequest);
    }


    @GetMapping("/get-all-books")
    public ApiResponse getAllBooks(){
        log.info("entering the get all books controller...");
        return bookService.getAllBooks();
    }


    @DeleteMapping("/delete-book/{book_id}")
    public void deleteProduct(@PathVariable("book_id")Long bookId) {
        bookService.deleteProduct(bookId);
    }

    @PutMapping("/update-book/{book_id}")
    public ApiResponse updateBook(@Valid @RequestBody BookRequest bookRequest,@PathVariable("book_id") Long bookId){
        return bookService.updateBook(bookRequest,bookId);
    }
}
