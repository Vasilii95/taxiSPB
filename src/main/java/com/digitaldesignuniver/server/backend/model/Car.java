package com.digitaldesignuniver.server.backend.model;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "car_table")
@Component
public class Car {
    public Car(){}
    private Long id;
    private int carNumber;
    private Tariff tariff;
    private Driver driver;
    private String carModel;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(int carNumber) {
        this.carNumber = carNumber;
    }

    @ManyToOne
    @JoinColumn(name = "tariff")
    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @OneToOne(mappedBy = "car")
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
