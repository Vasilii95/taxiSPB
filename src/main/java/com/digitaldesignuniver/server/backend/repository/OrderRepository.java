package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Customer;
import com.digitaldesignuniver.server.backend.model.Dispatcher;
import com.digitaldesignuniver.server.backend.model.Driver;
import com.digitaldesignuniver.server.backend.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAllByDispatcher(Dispatcher dispatcher);
    List<Order> findAllByCustomer(Customer customer);
    List<Order> findAllByDriver(Driver driver);
    List<Order> findAllByStatus(Boolean status);
    List<Order> findAll();
}
