package airline.airlinemidterm4458.service;
import airline.airlinemidterm4458.DTO.CustomerResponse;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public List<CustomerResponse> getAllCustomers() {

        if(customerRepository != null)
        {
            return customerRepository.findAll()
                    .stream()
                    .map(CustomerResponse::new)
                    .collect(Collectors.toList());
        }

        return null;

    }
}
