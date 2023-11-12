package airline.airlinemidterm4458.DTO;

import airline.airlinemidterm4458.model.Flight;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FlightResponse {


    String location;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date flightDate;
    int price;
    int numOfSeats;
    String message;
    Long flightNumber;

    public FlightResponse(Flight flight)
    {
        this.flightDate =flight.getFlightDate();
        this.flightNumber = flight.getId();
        this.location = "from " + flight.getFromCity() + " to " + flight.getToCity();
        this.price = flight.getPrice();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
