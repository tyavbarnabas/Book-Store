package com.codemarathon.bookstore.dto;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstname;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
}
