package airline.airlinemidterm4458.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class QueryTicketRequest {

    @JsonFormat(pattern="yyyy-MM-dd")
    Date date;
    String fromCity;
    String toCity;
    int numberOfPeople;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
