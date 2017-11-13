package com.digitaldesignuniver.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "tariff_table")
@Data
public class Tariff {
    public Tariff(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tariffName")
    private String tariffName;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tariff")
    private Set<Car> cars;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tariff")
    private Set<Order> orders;
}
