package com.digitaldesignuniver.server.backend.repository;

import com.digitaldesignuniver.server.backend.model.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Long> {
    Tariff findByTariffName(String tariffName);
}
