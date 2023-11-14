package airline.airlinemidterm4458.service;


import airline.airlinemidterm4458.DTO.CustomerResponse;
import airline.airlinemidterm4458.DTO.FlightResponse;
import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.DTO.QueryTicketRequest;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;
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

    public List<FlightResponse> getflights() {

        if(flightRepository != null)
        {
            return flightRepository.findAll()
                    .stream()
                    .map(FlightResponse::new)
                    .collect(Collectors.toList());
        }

        return null;
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
        if (flight.isPresent())
        {
            flightRepository.deleteById(id);
            return new ResponseEntity<>("flight deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("failed",HttpStatus.BAD_REQUEST);
    }
}
