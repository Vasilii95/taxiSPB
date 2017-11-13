package com.digitaldesignuniver.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_table")
@Data
public class Order {
    public Order(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    private boolean status;

    @Column(name = "addressFrom")
    private String addressFrom;

    @Column(name = "addressTo")
    private String addressTo;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "tariff", nullable = false)
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "dispatcher", nullable = false)
    private Dispatcher dispatcher;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "driver")
    private Driver driver;
}