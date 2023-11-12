package airline.airlinemidterm4458.controller;


import airline.airlinemidterm4458.DTO.NewFlightRequest;
import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Autowired
    FlightService flightService;
    @PostMapping("create-flight")
    public ResponseEntity<String> createNewFlight(@RequestBody NewFlightRequest newFlightRequest)
    {
           return flightService.createFlight(newFlightRequest);
    }

    @GetMapping("get-all-flights")
    public List<Flight> getFlights()
    {
        return flightService.getflights();
    }
}
