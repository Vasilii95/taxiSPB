package com.digitaldesignuniver.server.backend.service;

import com.digitaldesignuniver.server.backend.model.*;
import com.digitaldesignuniver.server.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Component
public class TaxiServiceImpl implements TaxiService {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private DispatcherRepository dispatcherRepository;
    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private TariffRepository tariffRepository;

    @Autowired
    public TaxiServiceImpl(CarRepository carRepository, CustomerRepository customerRepository,
                            DriverRepository driverRepository, DispatcherRepository dispatcherRepository,
                           OrderRepository orderRepository,TariffRepository tariffRepository){
        this.carRepository = carRepository;
        this.dispatcherRepository = dispatcherRepository;
        this.customerRepository = customerRepository;
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    @Transactional
    public void add(Car car) {
        carRepository.save(car);
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarByNumber(Integer numb) {
        return carRepository.findCarByCarNumber(numb);
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getCarsAllByTariff(Tariff tariff) {
        return carRepository.findAllByTariff(tariff);
    }

    @Override
    @Transactional
    public void add(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomerByName(String name) {
        return customerRepository.findCustomerByName(name);
    }

    @Override
    @Transactional
    public void add(Dispatcher dispatcher) {
        dispatcherRepository.save(dispatcher);
    }

    @Override
    @Transactional(readOnly = true)
    public Dispatcher getDispatcherById(Long id) {
        return dispatcherRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Dispatcher getDispatcherByLogin(String login) {
        return dispatcherRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void add(Driver driver) {
        driverRepository.save(driver);

    }

    @Override
    @Transactional(readOnly = true)
    public Driver getDriverById(Long id) {
        return driverRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getDriverByLogin(String login) {
        return driverRepository.findByLogin(login);
    }

    @Override
    @Transactional
    public void add(Order order) {
        orderRepository.save(order);
    }

    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByDispatcher(Dispatcher dispatcher) {
        return orderRepository.findAllByDispatcher(dispatcher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrderBsyClient(Customer customer) {
        return orderRepository.findAllByCustomer(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByDriver(Driver driver) {
        return orderRepository.findAllByDriver(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByStatus(Boolean status) {
        List<Order> orders = orderRepository.findAllByStatus(status);
        if(orders==null){
            orders = new ArrayList<>();
            orders.add(new Order());
        }
        return orders;
    }

    @Override
    @Transactional
    public void add(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    @Override
    @Transactional(readOnly = true)
    public Tariff getTariffById(Long id) {
        return tariffRepository.findOne(id);
    }

    @Override
    public List<Tariff> getTariffs() {
        Iterable<Tariff> f = tariffRepository.findAll();
        return (List<Tariff>) f;
    }

    @Override
    @Transactional(readOnly = true)
    public Tariff getTariffByTariffName(String tariffName) {
        return tariffRepository.findByTariffName(tariffName);
    }
}
