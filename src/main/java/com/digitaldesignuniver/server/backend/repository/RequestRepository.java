package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Dispatcher;
import com.digitaldesignuniver.server.backend.model.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findAllByDispatcher(Dispatcher dispatcher);
    List<Request> findAllByStatus(Boolean status);
    Request findById(Long id);
    List<Request> findAll();
}
