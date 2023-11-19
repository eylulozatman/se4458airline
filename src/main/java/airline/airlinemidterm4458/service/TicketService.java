package airline.airlinemidterm4458.service;


import airline.airlinemidterm4458.DTO.BuyTicketRequest;
import airline.airlinemidterm4458.DTO.TicketResponse;
import airline.airlinemidterm4458.model.Customer;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.model.Ticket;
import airline.airlinemidterm4458.repository.CustomerRepository;
import airline.airlinemidterm4458.repository.FlightRepository;
import airline.airlinemidterm4458.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    CustomerRepository customerRepository;

    public ResponseEntity<?> buyOneTicket(BuyTicketRequest buyTicketRequest, Customer currentCustomer)
    {
        Flight flight =
        flightRepository.findByFlightDateAndFromCityAndToCity(buyTicketRequest.getFlightDate(),
                                       buyTicketRequest.getFromCity(), buyTicketRequest.getToCity());
        if (flight == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            flight.setNumOfSeats(flight.getNumOfSeats() -1 );
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            ticket.setCustomer(currentCustomer);
            System.out.println(currentCustomer.getName());
            ticketRepository.save(ticket);
            TicketResponse ticketResponse = new TicketResponse(ticket, Optional.ofNullable(currentCustomer.getName()));
            return new ResponseEntity<>(ticketResponse, HttpStatus.CREATED);
        }

    }


    public ResponseEntity<String> deleteTicket(Long id)
    {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent())
        {
            Flight flight = ticket.get().getFlight();
            flight.setNumOfSeats(flight.getNumOfSeats() + 1);
            System.out.println(flight.getNumOfSeats());
            ticketRepository.deleteById(id);

            return new ResponseEntity<>("ticket deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }

    public List<TicketResponse> getAllTickets()
    {
        if(ticketRepository != null) {
            return ticketRepository.findAll()
                    .stream()
                    .map(t -> new TicketResponse(t,null))
                    .collect(Collectors.toList());
        }

        return null;
    }

    public List<TicketResponse> getTicketsByCustomerID(Long id)
    {
        if (customerRepository.findById(id).isPresent())
        {
            if(ticketRepository != null) {
                return ticketRepository.findAllByCustomer_Id(id)
                        .stream()
                        .map(t -> new TicketResponse(t,null))
                        .collect(Collectors.toList());
            }
        }
        else {
            return null;
        }

        return null;
    }
}
