package airline.airlinemidterm4458.repository;

import airline.airlinemidterm4458.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long aLong);

    Customer findByUsername(String username);

    Customer findByUsernameAndPassword(String username, String password);
}
