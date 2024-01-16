package com.codemarathon.bookstore.dto;

import com.codemarathon.bookstore.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String title;
    private String author;
    private Category category;
    private Double amount;
    private String status;
    private LocalDateTime dateCreated;
}
