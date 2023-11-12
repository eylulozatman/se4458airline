package airline.airlinemidterm4458.service;


import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;
    public ResponseEntity<String> createFlight(NewFlightRequest newFlightRequest)
    {
        Flight flight = new Flight();
        flight.setFlightDate(newFlightRequest.getFlightDate());
        flight.setPrice(newFlightRequest.getPrice());
        flight.setFromCity(newFlightRequest.getFromCity());
        flight.setToCity(newFlightRequest.getToCity());
        flight.setNumOfSeats(newFlightRequest.getNumOfSeats());

        flightRepository.save(flight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public List<Flight> getflights() {
        return flightRepository.findAll();
    }
}
