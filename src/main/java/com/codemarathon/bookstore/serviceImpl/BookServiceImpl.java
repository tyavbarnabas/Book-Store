package com.codemarathon.bookstore.serviceImpl;

import com.codemarathon.bookstore.constants.GeneralResponseEnum;
import com.codemarathon.bookstore.dto.ApiResponse;
import com.codemarathon.bookstore.dto.BookRequest;
import com.codemarathon.bookstore.exception.BooKAlreadyExistException;
import com.codemarathon.bookstore.exception.BookNotFoundException;
import com.codemarathon.bookstore.model.Book;
import com.codemarathon.bookstore.repository.BookRepository;
import com.codemarathon.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public ApiResponse createBook(BookRequest bookRequest) {

        Optional<Book> existingBook = bookRepository.findByTitle(bookRequest.getTitle());
        log.info("existing book : {} ", existingBook);

        if(existingBook.isPresent()){
            throw new BooKAlreadyExistException("book with title already exist");
        }

        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setAmount(bookRequest.getAmount());
        book.setCategory(bookRequest.getCategory());
        book.setStatus(bookRequest.getStatus());
        book.setCreatedAt(LocalDateTime.now());
        Book save = bookRepository.save(book);
        log.info("save book: {}", save);

        return ApiResponse.builder()
                .responseCode(GeneralResponseEnum.SUCCESS.getCode())
                .message(GeneralResponseEnum.SUCCESS.getMessage())
                .details(book)
                .build();
    }

    @Override
    public ApiResponse getAllBooks(){
        
        List<Book>bookList = bookRepository.findAll();
        
        if(bookList.isEmpty()){
            throw new BookNotFoundException("book not found");
        }
        
        return ApiResponse.builder()
                .responseCode("000")
                .message("success")
                .details(bookList)
                .build();
        
    }

    @Override
    public void deleteProduct(Long bookId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with with id " + bookId + "was not found"));
        bookRepository.delete(book);

    }

    @Override
    public ApiResponse updateBook(BookRequest bookRequest,Long bookId) {

        Optional<Book> optionalBook = bookRepository.findById(bookId);


        if(optionalBook.isEmpty()){
            throw new BookNotFoundException("Book with with id " + bookId + "was not found");
        }

        Book existingBook = optionalBook.get();

        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setAmount(bookRequest.getAmount());
        existingBook.setCategory(bookRequest.getCategory());
        existingBook.setStatus(bookRequest.getStatus());
        existingBook.setUpdatedAt(LocalDateTime.now());

        Book updatedBook = bookRepository.save(existingBook);
        log.info("save book: {}", updatedBook);

        return ApiResponse.builder()
                .responseCode(GeneralResponseEnum.UPDATED.getCode())
                .message(GeneralResponseEnum.UPDATED.getMessage())
                .details(updatedBook)
                .build();

    }
}
