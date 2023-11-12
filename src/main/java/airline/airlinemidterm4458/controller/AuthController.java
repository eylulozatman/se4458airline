package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.DTO.RegisterRequest;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

        return customerService.login(loginRequest);

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {

        if(customerService.getOneUserByUsername(registerRequest.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            Customer customer = new Customer();
            customer.setUsername(registerRequest.getUsername());
            customer.setName(registerRequest.getName());
            customer.setLastname(registerRequest.getLastname());
            customer.setPassword(registerRequest.getPassword());
            return customerService.saveNewUser(customer);

        }

    }
}
