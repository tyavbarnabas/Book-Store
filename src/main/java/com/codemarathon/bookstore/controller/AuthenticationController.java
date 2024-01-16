package com.codemarathon.bookstore.controller;


import com.codemarathon.bookstore.dto.AuthRequest;
import com.codemarathon.bookstore.dto.AuthenticationResponse;
import com.codemarathon.bookstore.dto.RegisterRequest;
import com.codemarathon.bookstore.model.Role;
import com.codemarathon.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class AuthenticationController {
    private  final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest, final HttpServletRequest request) {
        log.info("from register user controller");
        return ResponseEntity.ok(userService.registerUser(registerRequest, Role.USER,request));

    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthRequest request){
        log.info("entering the authenticate controller{}",request);
        return ResponseEntity.ok(userService.authenticate(request));

    }
}
