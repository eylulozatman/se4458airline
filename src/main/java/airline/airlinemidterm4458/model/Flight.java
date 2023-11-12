package airline.airlinemidterm4458.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity(name = "flight")
@Table(name="flight")
public class Flight {

    String fromCity;
    String toCity;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date flightDate;
    int price;
    int numOfSeats;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
