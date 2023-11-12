package airline.airlinemidterm4458.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BuyTicketRequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    Date flightDate;
    String fromCity;
    String toCity;
    String CustomerName;

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }
}
