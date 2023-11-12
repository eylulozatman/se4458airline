package airline.airlinemidterm4458.DTO;

import java.util.Date;
import java.util.Optional;

public class TicketResponse {

    String CustomerName;
    Date flightDate;
    String message;
    Long flightNumber;

    public TicketResponse() {
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getMessage() {
        return message;
    }


}
