package com.examly.springapp.service;

import com.examly.springapp.entity.User;
import com.examly.springapp.model.LoginRequest;
import com.examly.springapp.model.LoginResponse;
import com.examly.springapp.model.SignUpRequest;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public String register(SignUpRequest signUpRequest){
        boolean emailExist = userRepository.findByEmail(signUpRequest.getEmail()).isPresent();
        if (emailExist) throw new IllegalStateException("this email is already registered");
        if(!validUserRole(signUpRequest.getUserRole())) throw new IllegalStateException("user role not valid");
        User user = new User(signUpRequest);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return capitalize(signUpRequest.getUserRole())+" added";
    }

    public ResponseEntity<LoginResponse> authentication(LoginRequest loginRequest) throws BadCredentialsException {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            UserDetails userDetails = (UserDetails) authenticate.getPrincipal();
            User userObject = (User) userDetails;
            LoginResponse loginResponse = new LoginResponse(userObject);
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, "Bearer "+token)
                    .body(loginResponse);

        } catch (BadCredentialsException ex) {
            throw  new BadCredentialsException("Invalid username or password");
        }
    }

    private String capitalize(String string){
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }

    private boolean validUserRole(String userRole){
        if (userRole == null) return false;
        return userRole.equalsIgnoreCase("USER") ||  userRole.equalsIgnoreCase("ADMIN");
    }
}
