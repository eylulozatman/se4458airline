package airline.airlinemidterm4458.controller;

import airline.airlinemidterm4458.DTO.BuyTicketRequest;
import airline.airlinemidterm4458.DTO.TicketResponse;
import airline.airlinemidterm4458.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/buy-ticket")
    public ResponseEntity<TicketResponse> buyTicket(@RequestBody BuyTicketRequest buyTicketRequest)
    {
          return ticketService.buyOneTicket(buyTicketRequest);
    }

    @DeleteMapping("/delete-ticket")
    public  ResponseEntity<String> deleteTicket(@RequestParam (value = "id") Long id)
    {
        return ticketService.deleteTicket(id);
    }
}
