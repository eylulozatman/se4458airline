package airline.airlinemidterm4458.repository;


import airline.airlinemidterm4458.model.Flight;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    Page<Flight> findAll(Pageable pageable);

    @Override
    Optional<Flight> findById(Long aLong);

    List<Flight> findByFlightDate(Date flightDate);

    Flight findByFlightDateAndFromCityAndToCity(Date flightDate,String fromCity,String toCity);


}
