package com.digitaldesignuniver.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "driver_table")
@Data
public class Driver {
    public Driver(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne(optional = false)
    @JoinColumn(name="car", unique = true, nullable = false, updatable = false)
    private Car car;

    @Column(name = "money")
    private BigDecimal money;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "driver")
    private Set<Order> orders;
}
