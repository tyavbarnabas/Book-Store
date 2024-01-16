package com.codemarathon.bookstore.dto;

import lombok.*;

@Getter
@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {

    private String responseCode;
    private String message;
    private String token;
}
