package airline.airlinemidterm4458.DTO;

import airline.airlinemidterm4458.model.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FlightResponse {

    String message;
    Long flightNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date flightDate;
    String location;
    String price;
    int numOfSeats;


    public FlightResponse(Flight flight)
    {
        this.message = "Flight Detail:";
        this.flightDate =flight.getFlightDate();
        this.flightNumber = flight.getId();
        this.location = "from " + flight.getFromCity() + " to " + flight.getToCity();
        this.price = flight.getPrice() + "$"  ;
        this.numOfSeats = flight.getNumOfSeats();
    }

    public FlightResponse(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }
}
