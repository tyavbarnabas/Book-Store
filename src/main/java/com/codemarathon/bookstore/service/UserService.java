package com.codemarathon.bookstore.service;

import com.codemarathon.bookstore.dto.AuthRequest;
import com.codemarathon.bookstore.dto.AuthenticationResponse;
import com.codemarathon.bookstore.dto.RegisterRequest;
import com.codemarathon.bookstore.model.Role;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    AuthenticationResponse registerUser(RegisterRequest registerRequest, Role role, final HttpServletRequest request);

    AuthenticationResponse authenticate(AuthRequest request);
}
