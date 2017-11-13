package com.digitaldesignuniver.server.repository;

import com.digitaldesignuniver.server.model.Dispatcher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DispatcherRepository extends CrudRepository<Dispatcher, Long> {
    Dispatcher findByLogin(String login);
}
