package com.digitaldesignuniver.server.model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "car_table")
@Data
public class Car {
    public Car(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "carNumber")
    private int carNumber;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tariff", nullable = false)
    private Tariff tariff;

    @OneToOne(optional = false, mappedBy="car")
    public Driver driver;

    @Column(name = "carModel")
    private String carModel;
}
