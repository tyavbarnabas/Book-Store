package com.codemarathon.bookstore.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String responseCode;
    private String message;
    private Object details;
}
