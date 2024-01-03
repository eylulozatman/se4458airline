package airline.airlinemidterm4458.controller;

import airline.airlinemidterm4458.DTO.FlightResponse;
import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.DTO.QueryTicketRequest;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/get-all-flights")
    public List<FlightResponse> getFlights(@RequestParam(defaultValue = "0") int pageNumber ){

        Page<Flight> flightsPage = flightService.getFlights(pageNumber);
        return flightsPage.getContent().stream()
                .map(FlightResponse::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/create-flight")
    public ResponseEntity<String> createNewFlight(@RequestBody NewFlightRequest newFlightRequest) {
        return flightService.createFlight(newFlightRequest);
    }

    @PostMapping("/query-flight")
    public  ResponseEntity<?> queryFlights(@RequestBody QueryTicketRequest queryTicketRequest) {
        if (queryTicketRequest != null) {
            return flightService.queryFlights(queryTicketRequest);
        }
        else
            return new ResponseEntity<>("please fill in the required fields: " +
                    "date, from city, to city, number of people", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-flight")
    public ResponseEntity<String> deleteOneFlight(@RequestParam(value = "id") Long id)
    {
             return flightService.deleteOneFlight(id);
    }
}
