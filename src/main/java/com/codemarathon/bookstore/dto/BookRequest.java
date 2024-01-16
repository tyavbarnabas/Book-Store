package com.codemarathon.bookstore.dto;

import com.codemarathon.bookstore.model.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
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
