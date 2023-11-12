package airline.airlinemidterm4458.service;
import airline.airlinemidterm4458.DTO.LoginRequest;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public ResponseEntity<String> login(LoginRequest loginRequest)
    {
        Customer customer =
       customerRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if (customer != null)
        {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return null;
    }

    public Customer getOneUserByUsername(String username)
    {
        return customerRepository.findByUsername(username);
    }

    public ResponseEntity<String> saveNewUser(Customer customer)
    {
         customerRepository.save(customer);
         return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
