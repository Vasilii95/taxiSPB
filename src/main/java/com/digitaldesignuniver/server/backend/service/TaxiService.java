package com.digitaldesignuniver.server.backend.service;

import com.digitaldesignuniver.server.backend.model.*;

import java.util.List;

public interface TaxiService {
    void add(Car car);
    Car getCarByNumber(Integer numb);
    Car getCarById(Long id);
    List<Car> getCarsAllByTariff(Tariff tariff);

    void add(Dispatcher dispatcher);
    Dispatcher getDispatcherById(Long id);
    Dispatcher getDispatcherByLogin(String login);

    void add(Driver driver);
    Driver getDriverById(Long id);
    Driver getDriverByLogin(String login);

    void add(Order order);
    Long getIdByOrder(Order order);
    Order getOrderById(Long id);
    List<Order> getOrders();
    List<Order> getOrdersByDriver(Driver driver);
    List<Order> getOrdersByStatus(Boolean status);
    Order findByRequest(Request request);
    void deleteOrder(Order order);

    List<Request> getAllRequests();
    void add(Request request);
    List<Request> findAllByDispatcher(Dispatcher dispatcher);
    Request findById(Long id);
    List<Request> findAllByStatus(Boolean status);
    List<Request> findAll();
    void deleteRequest(Request request);


    void add(Tariff tariff);
    Tariff getTariffById(Long id);
    List<Tariff> getTariffs();
    Tariff getTariffByTariffName(String tariffName);
}
