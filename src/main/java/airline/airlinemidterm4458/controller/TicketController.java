package airline.airlinemidterm4458.controller;

import airline.airlinemidterm4458.DTO.BuyTicketRequest;
import airline.airlinemidterm4458.DTO.TicketResponse;
import airline.airlinemidterm4458.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    TicketService ticketService;

    @PostMapping("/butTicket")
    public ResponseEntity<TicketResponse> buyTicket(@RequestBody BuyTicketRequest buyTicketRequest)
    {
          return ticketService.buyOneTicket(buyTicketRequest);
    }
}
