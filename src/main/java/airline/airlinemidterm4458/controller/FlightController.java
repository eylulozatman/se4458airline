package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.FlightResponse;
import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.DTO.QueryTicketRequest;
import airline.airlinemidterm4458.service.FlightService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("/get-all-flights")
    public List<FlightResponse> getFlights() {
        return flightService.getflights();
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
            return new ResponseEntity<>("please fill in the required fields", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete-flight")
    public ResponseEntity<String> deleteOneFlight(@RequestParam(value = "id") Long id)
    {
             return flightService.deleteOneFlight(id);
    }
}
