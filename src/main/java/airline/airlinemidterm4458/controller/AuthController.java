package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.AuthenticationResponse;
import airline.airlinemidterm4458.DTO.CustomerResponse;
import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.DTO.RegisterRequest;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.service.AuthService;
import airline.airlinemidterm4458.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        return authService.login(loginRequest);

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        return  authService.register(registerRequest);

    }
}
