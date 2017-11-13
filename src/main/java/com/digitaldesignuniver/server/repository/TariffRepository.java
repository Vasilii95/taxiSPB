package com.digitaldesignuniver.server.repository;

import com.digitaldesignuniver.server.model.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends CrudRepository<Tariff, Long> {
    Tariff findByTariffName(String tariffName);
}
