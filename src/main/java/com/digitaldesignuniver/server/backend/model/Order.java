package com.digitaldesignuniver.server.backend.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "order_table")
@Component
public class Order {
    public Order(){}

    private Long id;
    private boolean status;
    private Request request;
    private Driver driver;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @OneToOne
    @JoinColumn(name = "request")
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver")
    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Transient
    public String getDriverName(){
        return driver.getName();
    }
    @Transient
    public String getAddressFrom(){
        return request.getAddressFrom();
    }
    @Transient
    public String getAddressTo(){
        return request.getAddressTo();
    }
    @Transient
    public String getTariffName(){
        return request.getTariff().getTariffName();
    }
}