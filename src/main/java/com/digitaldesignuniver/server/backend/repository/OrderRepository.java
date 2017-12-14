package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Driver;
import com.digitaldesignuniver.server.backend.model.Order;
import com.digitaldesignuniver.server.backend.model.Request;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByRequest(Request request);
    List<Order> findAllByDriver(Driver driver);
    List<Order> findAllByStatus(Boolean status);
    List<Order> findAll();

}
