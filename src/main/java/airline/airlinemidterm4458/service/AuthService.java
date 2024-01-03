package airline.airlinemidterm4458.service;

import airline.airlinemidterm4458.DTO.AuthenticationResponse;
import airline.airlinemidterm4458.DTO.CustomerResponse;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    public AuthenticationResponse login(LoginRequest loginRequest)
    {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            Customer user = customerRepository.findByUsername(loginRequest.getUsername());
            if (user != null) {
                var jwt = jwtService.generateToken(user);
                return AuthenticationResponse.builder().token("Bearer " + jwt).build();
            } else {

                return AuthenticationResponse.builder().token("invalid username or password").build();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return AuthenticationResponse.builder().token("An error occurred").build();
        }
    }
    public ResponseEntity<?> register(RegisterRequest registerRequest) {

        if(registerRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

       else {
            Customer customer = new Customer();
            String password = passwordEncoder.encode(registerRequest.getPassword());
            customer.setDecodedPassword(registerRequest.getPassword());
            customer.setUsername(registerRequest.getUsername());
            customer.setName(registerRequest.getName());
            customer.setLastname(registerRequest.getLastname());
            customer.setPassword(password);

            if (customerRepository.findByUsername(customer.getUsername()) != null) {
               return  new ResponseEntity<>("this username already used",HttpStatus.BAD_REQUEST);
           }
            customerRepository.save(customer);
            CustomerResponse customerResponse = new CustomerResponse(customer);
            return new ResponseEntity<>(customerResponse,HttpStatus.CREATED);

        }
    }

}
