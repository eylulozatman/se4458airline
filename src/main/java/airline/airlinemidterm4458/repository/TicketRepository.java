package airline.airlinemidterm4458.repository;

import airline.airlinemidterm4458.model.Ticket;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Override
    List<Ticket> findAll();

    @Override
    Optional<Ticket> findById(Long aLong);

    List<Ticket> findAllByCustomer_Id(Long CustomerID);

}
