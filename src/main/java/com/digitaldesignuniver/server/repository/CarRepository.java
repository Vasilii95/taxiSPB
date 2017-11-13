package com.digitaldesignuniver.server.repository;

import com.digitaldesignuniver.server.model.Car;
import com.digitaldesignuniver.server.model.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    Car findCarByCarNumber(int carNumber);
    List<Car> findAllByTariff(Tariff tariff);
}
