package com.codemarathon.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    @JsonIgnore
    private Long id;
    private String categoryName;
    private String description;
    @JsonIgnore
    @OneToOne(mappedBy = "category")
    private Book book;

}
