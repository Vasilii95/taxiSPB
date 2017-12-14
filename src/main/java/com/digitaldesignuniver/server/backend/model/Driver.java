package com.digitaldesignuniver.server.backend.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "driver_table")
@Component
public class Driver {
    public Driver(){}

    private Long id;
    private String phoneNumber;
    private String name;
    private String login;
    private String password;
    private Car car;
    private BigDecimal money;
    private Set<Order> orders;
    private Boolean status;
    private String orderId;
    private String nextOrderId;
    @Transient
    private Double longitude;
    @Transient
    private Double latitude;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    @ManyToMany(mappedBy = "driver")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Transient
    public Double getLongitude() {
        return longitude;
    }

    @Transient
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Transient
    public Double getLatitude() {
        return latitude;
    }

    @Transient
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getNextOrderId() {
        return nextOrderId;
    }

    public void setNextOrderId(String nextOrderId) {
        this.nextOrderId = nextOrderId;
    }
}
