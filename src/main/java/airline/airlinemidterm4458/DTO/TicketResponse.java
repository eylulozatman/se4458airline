package airline.airlinemidterm4458.DTO;

import airline.airlinemidterm4458.model.Flight;
import airline.airlinemidterm4458.model.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;

import java.util.Date;
import java.util.Optional;

public class TicketResponse {


    String message;
    Long ticketNumber;
    Long flightNumber;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date flightDate;
    String customerName;
    String location;
    String numberOfSeat;


    public TicketResponse(Ticket ticket, Optional<String> customerName)
    {

        this.message = "Ticket details:";
        this.ticketNumber = ticket.getId();
        this.flightDate = ticket.getFlight().getFlightDate();
        this.flightNumber = ticket.getFlight().getId();
        if (customerName == null)
        {     //for get all tickets
            this.customerName = ticket.getCustomer().getName() + " " + ticket.getCustomer().getLastname();
        }
        else {  //for buy ticket
            this.customerName = String.valueOf(customerName);
        }
        this.location = "from " + ticket.getFlight().getFromCity() + " to " + ticket.getFlight().getToCity();
        this.numberOfSeat = "Number of seats remaining for this flight " + ticket.getFlight().getNumOfSeats();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Long ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNumberOfSeat() {
        return numberOfSeat;
    }

    public void setNumberOfSeat(String numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }
}
