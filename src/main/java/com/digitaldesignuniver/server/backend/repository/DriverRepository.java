package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {
    Driver findByLogin(String login);
}
