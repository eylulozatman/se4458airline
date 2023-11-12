package airline.airlinemidterm4458.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity(name = "ticket")
@Table(name="ticket")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id",nullable = false)
    Flight flight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable = false)
    Customer customer;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
