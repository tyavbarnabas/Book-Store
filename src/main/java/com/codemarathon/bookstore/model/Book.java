package com.codemarathon.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@ToString
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="book_id")
    private Long id;
    @NotEmpty(message = "title must not be empty")
    private String title;
    @NotEmpty(message = "Author must not be empty")
    private String author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;
    @NotNull(message = "Amount cannot be null")
    private Double amount;
    @NotEmpty(message = "status must not be empty")
    private String status;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="user_id")
    private User user;



}
