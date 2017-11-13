package com.digitaldesignuniver.server.repository;

import com.digitaldesignuniver.server.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver findByLogin(String login);
}
