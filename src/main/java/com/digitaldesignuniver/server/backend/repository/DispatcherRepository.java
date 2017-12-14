package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Dispatcher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends CrudRepository<Dispatcher, Long> {
    Dispatcher findByLogin(String login);
}
