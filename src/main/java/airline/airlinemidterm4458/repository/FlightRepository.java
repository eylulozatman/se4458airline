package airline.airlinemidterm4458.repository;


import airline.airlinemidterm4458.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Override
    List<Flight> findAll();

    @Override
    List<Flight> findAllById(Iterable<Long> longs);

    List<Flight> findByFlightDate(Date flightDate);
}
