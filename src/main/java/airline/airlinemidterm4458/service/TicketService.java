package airline.airlinemidterm4458.service;


import airline.airlinemidterm4458.DTO.BuyTicketRequest;
import airline.airlinemidterm4458.DTO.TicketResponse;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.model.Ticket;
import airline.airlinemidterm4458.repository.FlightRepository;
import airline.airlinemidterm4458.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    TicketRepository ticketRepository;

    public ResponseEntity<TicketResponse> buyOneTicket(BuyTicketRequest buyTicketRequest, Customer currentCustomer)
    {
        Flight flight =
        flightRepository.findByFlightDateAndFromCityAndToCity(buyTicketRequest.getFlightDate(),
                                       buyTicketRequest.getFromCity(), buyTicketRequest.getToCity());

        TicketResponse ticketResponse = new TicketResponse();
        if (flight == null)
        {
            ticketResponse.setMessage("No suitable flight found");
        }
        else {
            flight.setNumOfSeats(flight.getNumOfSeats() -1 );
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            ticket.setCustomer(currentCustomer);
            ticketRepository.save(ticket);
            ticketResponse.setMessage("ticket details: your ticket number is " + ticket.getId());
            ticketResponse.setFlightNumber(flight.getId());
            ticketResponse.setCustomerName(buyTicketRequest.getCustomerName());
            ticketResponse.setFlightDate(buyTicketRequest.getFlightDate());

        }

       return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
    }


    public ResponseEntity<String> deleteTicket(Long id)
    {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent())
        {
            ticketRepository.deleteById(id);
            return new ResponseEntity<>("ticket deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }
}
