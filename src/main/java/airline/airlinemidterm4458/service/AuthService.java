package airline.airlinemidterm4458.service;

import airline.airlinemidterm4458.DTO.AuthenticationResponse;
import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.DTO.RegisterRequest;
import airline.airlinemidterm4458.config.JwtUtil;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    public AuthenticationResponse login(LoginRequest loginRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginRequest.getUsername(), loginRequest.getPassword()));
         Customer user = customerRepository.findByUsername(loginRequest.getUsername());
        if (user.getId() > 0)
        {
            System.out.println("xxxxxxxx");
            var jwt = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwt).build();
        }
        else
        {
            return AuthenticationResponse.builder().token("invalid username or password").build();
        }
    }
    public ResponseEntity<String> register(RegisterRequest registerRequest) {

        if(customerRepository.findByUsername(registerRequest.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else if(Objects.equals(registerRequest.getUsername(), customerRepository.findByUsername(registerRequest.getUsername()).getUsername())){
               return  new ResponseEntity<>("this username is taken",HttpStatus.BAD_REQUEST);
        }


        else {
            Customer customer = new Customer();
            customer.setUsername(registerRequest.getUsername());
            customer.setName(registerRequest.getName());
            customer.setLastname(registerRequest.getLastname());
            customer.setPassword(registerRequest.getPassword());
            customerRepository.save(customer);

            return new ResponseEntity<>("Customer saved",HttpStatus.CREATED);

        }
    }

}
