package airline.airlinemidterm4458.service;
import airline.airlinemidterm4458.DTO.AuthenticationResponse;
import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.config.JwtUtil;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);

        if (customer == null) {
            throw new UsernameNotFoundException("customer not found: " + username);
        }

        return customer;
    }

    public ResponseEntity<?> deleteCustomer(Long id)
    {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
        {
            customerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public List<Customer> getAllCustomers() {

        return customerRepository.findAll();
    }
}
