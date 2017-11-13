package com.digitaldesignuniver.server.repository;

import com.digitaldesignuniver.server.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findCustomerByName(String name);
}
