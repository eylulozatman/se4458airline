package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.CustomerResponse;
import airline.airlinemidterm4458.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @DeleteMapping("/delete-customer")
    public ResponseEntity<?> deleteCustomerById(@RequestParam(value = "id") Long id)
    {
        return customerService.deleteCustomer(id);
    }

    @GetMapping("/all-customers")
    public List<CustomerResponse> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

}
