package airline.airlinemidterm4458.service;


import airline.airlinemidterm4458.DTO.FlightResponse;
import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.DTO.QueryTicketRequest;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.model.Ticket;
import airline.airlinemidterm4458.repository.FlightRepository;
import airline.airlinemidterm4458.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FlightService {

    public static final int PAGE_SIZE = 3;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    TicketRepository ticketRepository;
    public ResponseEntity<String> createFlight(NewFlightRequest newFlightRequest)
    {
        Flight existingFlight = flightRepository.findByFlightDateAndFromCityAndToCity(
                newFlightRequest.getFlightDate(),
                newFlightRequest.getFromCity(),
                newFlightRequest.getToCity()
        );

        if (existingFlight != null) {
            return new ResponseEntity<>("This flight is already available", HttpStatus.OK);
        } else {
            Flight flight = new Flight();
            flight.setFlightDate(newFlightRequest.getFlightDate());
            flight.setPrice(newFlightRequest.getPrice());
            flight.setFromCity(newFlightRequest.getFromCity());
            flight.setToCity(newFlightRequest.getToCity());
            flight.setNumOfSeats(newFlightRequest.getNumOfSeats());

            flightRepository.save(flight);

            return new ResponseEntity<>("Flight created", HttpStatus.CREATED);
        }

    }

    public Page<Flight> getFlights(int pageNumber)
    {
        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        return flightRepository.findAll(pageable);
    }

    public ResponseEntity<?> queryFlights(QueryTicketRequest queryTicketRequest)
    {

        Flight flight = flightRepository.findByFlightDateAndFromCityAndToCity(queryTicketRequest.getDate(), queryTicketRequest.getFromCity(), queryTicketRequest.getToCity());
       if(flight != null && flight.getNumOfSeats() > queryTicketRequest.getNumberOfPeople())
       {
           FlightResponse flightResponse = new FlightResponse(flight);
           return new ResponseEntity<>(flightResponse,HttpStatus.OK);
       }
       return  new ResponseEntity<>("no available flight",HttpStatus.NOT_FOUND);

    }

    public ResponseEntity<String> deleteOneFlight(Long id) {

        Optional<Flight> flight = flightRepository.findById(id);
        List<Ticket> tickets = ticketRepository.findByFlight_Id(id);
        if (flight.isPresent() &&  tickets != null)
        {
            ticketRepository.deleteAll(tickets);
            flightRepository.deleteById(id);
            return new ResponseEntity<>("flight deleted",HttpStatus.OK);
        } else if (flight.isPresent())
        {
            flightRepository.deleteById(id);
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }
}
