package airline.airlinemidterm4458.repository;


import airline.airlinemidterm4458.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Override
    List<Flight> findAll();

    @Override
    Optional<Flight> findById(Long aLong);

    List<Flight> findByFlightDate(Date flightDate);

    @Query(value = "SELECT f FROM flight f WHERE :condition", nativeQuery = true)
    List<Flight> findFlightsByCondition(@Param("condition") String condition);

    Flight findByFlightDateAndFromCityAndToCity(Date flightDate,String fromCity,String toCity);

}
