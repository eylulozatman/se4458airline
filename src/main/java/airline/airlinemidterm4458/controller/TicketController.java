package airline.airlinemidterm4458.controller;

import airline.airlinemidterm4458.DTO.BuyTicketRequest;
import airline.airlinemidterm4458.DTO.TicketResponse;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.repository.CustomerRepository;
import airline.airlinemidterm4458.service.AuthService;
import airline.airlinemidterm4458.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Api(value = "Ticket controller ")
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Autowired
    CustomerRepository customerRepository;


    @ApiOperation(value = "buy-ticket")
    @PostMapping("/buy-ticket")
    public ResponseEntity<TicketResponse> buyTicket(@RequestBody BuyTicketRequest buyTicketRequest,Authentication authentication)
    {
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Customer currentCustomer = customerRepository.findByUsername(username);
        return ticketService.buyOneTicket(buyTicketRequest, currentCustomer);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    }
    @ApiOperation(value = "delete-ticket")
    @DeleteMapping("/delete-ticket")
    public  ResponseEntity<String> deleteTicket(@RequestParam (value = "id") Long id)
    {
        return ticketService.deleteTicket(id);
    }


}
