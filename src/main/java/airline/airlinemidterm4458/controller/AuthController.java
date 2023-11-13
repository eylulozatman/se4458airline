package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.AuthenticationResponse;
import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.DTO.RegisterRequest;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.service.AuthService;
import airline.airlinemidterm4458.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Authentication controller ")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @ApiOperation(value = "login")
    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {

        System.out.println("aaaaa");
        return authService.login(loginRequest);

    }

    @ApiOperation(value = "register")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

        return  authService.register(registerRequest);

    }
}
