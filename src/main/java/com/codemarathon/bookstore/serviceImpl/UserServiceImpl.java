package com.codemarathon.bookstore.serviceImpl;

import com.codemarathon.bookstore.config.JwtService;
import com.codemarathon.bookstore.constants.GeneralResponseEnum;
import com.codemarathon.bookstore.dto.AuthRequest;
import com.codemarathon.bookstore.dto.AuthenticationResponse;
import com.codemarathon.bookstore.dto.RegisterRequest;
import com.codemarathon.bookstore.exception.UsersNotFoundException;
import com.codemarathon.bookstore.model.Role;
import com.codemarathon.bookstore.model.User;
import com.codemarathon.bookstore.repository.UserRepository;
import com.codemarathon.bookstore.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private  final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse registerUser(RegisterRequest registerRequest, Role role, final HttpServletRequest request) {

        Optional<User> existingUser = userRepository.findByEmail(registerRequest.getEmail());
        log.info("existing user in database: {}",existingUser);

        if(existingUser.isPresent()){
            throw  new UsersNotFoundException("user with " + registerRequest.getEmail() + " already exists");
        }

        User registeredUser = new User();

        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword());


        registeredUser.setFirstname(registerRequest.getFirstname());
        registeredUser.setLastName(registerRequest.getLastName());
        registeredUser.setPhoneNumber(registerRequest.getPhoneNumber());
        registeredUser.setAddress(registerRequest.getAddress());
        registeredUser.setEmail(registerRequest.getEmail());
        registeredUser.setPassword(encryptedPassword);
        registeredUser.setRole(role);

        User savedUser = userRepository.save(registeredUser);
        log.info("User successfully saved to database: {} ",savedUser);


        var jwtToken = jwtService.generateToken(registeredUser);
        log.info("generated jwt token for user: {}", jwtToken);


        return AuthenticationResponse.builder()
                .responseCode(GeneralResponseEnum.REGISTRATION.getCode())
                .message(GeneralResponseEnum.SUCCESS.getMessage())
                .token(jwtToken)
                .build();
    }

    @Override
    public  AuthenticationResponse authenticate(AuthRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),request.getPassword()
        ));

        var user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(()-> new UsersNotFoundException("User not found"));


        var jwtToken = jwtService.generateToken(user);
        log.info("generated jwt token for user: {}", jwtToken);

        return AuthenticationResponse.builder()
                .responseCode(GeneralResponseEnum.LOGIN.getCode())
                .message(GeneralResponseEnum.LOGIN.getMessage())
                .token(jwtToken)
                .build();

    }

}
