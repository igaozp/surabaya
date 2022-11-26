package xyz.andornot.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.andornot.domain.Customer;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}
