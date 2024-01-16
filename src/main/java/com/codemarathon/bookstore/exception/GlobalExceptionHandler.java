package com.codemarathon.bookstore.exception;

import com.codemarathon.bookstore.constants.GeneralResponseEnum;
import com.codemarathon.bookstore.dto.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handlePayloadException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ApiResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getAllErrors().get(0).getDefaultMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiResponse apiResponse = new ApiResponse(
                GeneralResponseEnum.FAILED.getCode(),
                GeneralResponseEnum.FAILED.getMessage(),
                errors);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiResponse> handleBookNotFoundException(BookNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BooKAlreadyExistException.class)
    public ResponseEntity<ApiResponse> handleBooKAlreadyExistException(BooKAlreadyExistException ex) {
        return new ResponseEntity<>(new ApiResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<ApiResponse> handleCustomerNotFoundException(UsersNotFoundException ex) {
        return new ResponseEntity<>(new ApiResponse(GeneralResponseEnum.FAILED.getCode(),
                ex.getMessage(),
                GeneralResponseEnum.FAILED.getMessage()), HttpStatus.NOT_FOUND);
    }


}
