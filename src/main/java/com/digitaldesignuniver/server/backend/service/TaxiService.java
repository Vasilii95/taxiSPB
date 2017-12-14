package com.digitaldesignuniver.server.backend.service;

import com.digitaldesignuniver.server.backend.model.*;


import java.util.List;

public interface TaxiService {
    void add(Car car);
    Car getCarByNumber(Integer numb);
    Car getCarById(Long id);
    List<Car> getCarsAllByTariff(Tariff tariff);
    void add(Customer customer);
    Customer getCustomerById(Long id);
    Customer getCustomerByName(String nameAndSurname);
    void add(Dispatcher dispatcher);
    Dispatcher getDispatcherById(Long id);
    Dispatcher getDispatcherByLogin(String login);
    void add(Driver driver);
    Driver getDriverById(Long id);
    Driver getDriverByLogin(String login);
    void add(Order order);
    Order getOrderById(Long id);
    List<Order> getOrders();
    List<Order> getOrdersByDispatcher(Dispatcher dispatcher);
    List<Order> getOrderBsyClient(Customer customer);
    List<Order> getOrdersByDriver(Driver driver);
    List<Order> getOrdersByStatus(Boolean status);
    void add(Tariff tariff);
    Tariff getTariffById(Long id);
    List<Tariff> getTariffs();
    Tariff getTariffByTariffName(String tariffName);
}
