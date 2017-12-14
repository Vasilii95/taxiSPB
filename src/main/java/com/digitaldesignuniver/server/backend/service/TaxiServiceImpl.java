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
    private DispatcherRepository dispatcherRepository;
    private DriverRepository driverRepository;
    private OrderRepository orderRepository;
    private TariffRepository tariffRepository;
    private RequestRepository requestRepository;

    @Autowired
    public TaxiServiceImpl(CarRepository carRepository, DriverRepository driverRepository,
                           DispatcherRepository dispatcherRepository, OrderRepository orderRepository,
                           RequestRepository requestRepository, TariffRepository tariffRepository){
        this.carRepository = carRepository;
        this.dispatcherRepository = dispatcherRepository;
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.tariffRepository = tariffRepository;
        this.requestRepository= requestRepository;
    }

    /**
     * Add car in to database
     * @param car machine that we need to save
     */
    @Override
    @Transactional
    public void add(Car car) {
        carRepository.save(car);
    }

    /**
     * Get car by car number
     * @param numb car number
     * @return found car
     */
    @Override
    @Transactional(readOnly = true)
    public Car getCarByNumber(Integer numb) {
        return carRepository.findCarByCarNumber(numb);
    }

    /**
     * Get car by car id
     * @param id car id
     * @return found car
     */
    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Long id) {
        return carRepository.findOne(id);
    }

    /**
     * Get cars by tariff
     * @param tariff car tariff
     * @return found cars
     */
    @Override
    @Transactional(readOnly = true)
    public List<Car> getCarsAllByTariff(Tariff tariff) {
        return carRepository.findAllByTariff(tariff);
    }

    /**
     * Save dispatcher
     * @param dispatcher
     */
    @Override
    @Transactional
    public void add(Dispatcher dispatcher) {
        dispatcherRepository.save(dispatcher);
    }

    /**
     * Get dispatcher by id
     * @param id dispatcher id
     * @return found dispatcher
     */
    @Override
    @Transactional(readOnly = true)
    public Dispatcher getDispatcherById(Long id) {
        return dispatcherRepository.findOne(id);
    }

    /**
     * Get dispatcher by login
     * @param login dispatcher login
     * @return found dispatcher
     */
    @Override
    @Transactional(readOnly = true)
    public Dispatcher getDispatcherByLogin(String login) {
        return dispatcherRepository.findByLogin(login);
    }

    /**
     * Save driver
     * @param driver
     */
    @Override
    @Transactional
    public void add(Driver driver) {
        driverRepository.save(driver);

    }

    /**
     * Get driver by id
     * @param id driver id
     * @return found driver
     */
    @Override
    @Transactional(readOnly = true)
    public Driver getDriverById(Long id) {
        return driverRepository.findOne(id);
    }

    /**
     * Get driver by login
     * @param login driver login
     * @return found driver
     */
    @Override
    @Transactional(readOnly = true)
    public Driver getDriverByLogin(String login) {
        return driverRepository.findByLogin(login);
    }

    /**
     * Save order
     * @param order
     */
    @Override
    @Transactional
    public void add(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Long getIdByOrder(Order order) {
        return orderRepository.findByRequest(order.getRequest()).getId();
    }

    /**
     * Get order by id
     * @param id order id
     * @return found order
     */
    @Override
    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findOne(id);
    }

    /**
     * Get all orders
     * @return
     */
    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    /**
     * Get orders by driver
     * @param driver driver of this orders
     * @return found orders
     */
    @Override
    @Transactional(readOnly = true)
    public List<Order> getOrdersByDriver(Driver driver) {
        return orderRepository.findAllByDriver(driver);
    }

    /**
     * Delete order
     * @param order
     */
    @Override
    public void deleteOrder(Order order){
        orderRepository.delete(order);
    }

    /**
     * Get orders by status
     * @param status status of this orders
     * @return found orders
     */
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

    /**
     * Find order by request
     * @param request request of tis order
     * @return found order
     */
    @Override
    public Order findByRequest(Request request) {
        return orderRepository.findByRequest(request);
    }

    /**
     * Save request
     * @param request
     */
    @Override
    public void add(Request request) {
        requestRepository.save(request);
    }

    /**
     * Get add requests
     * @return found requests
     */
    @Override
    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    /**
     * Find all requests by dispatcher
     * @param dispatcher dispatcher of this
     * @return found requests
     */
    @Override
    public List<Request> findAllByDispatcher(Dispatcher dispatcher) {
        return requestRepository.findAllByDispatcher(dispatcher);
    }

    /**
     * Get request by id
     * @param id id of this request
     * @return found request
     */
    @Override
    public Request findById(Long id) {
        return requestRepository.findById(id);
    }

    /**
     * Delete request
     * @param request
     */
    @Override
    public void deleteRequest(Request request){
        requestRepository.delete(request);
    }

    /**
     * Find all requests by status
     * @param status status of this requests
     * @return found requests
     */
    @Override
    public List<Request> findAllByStatus(Boolean status) {
        return requestRepository.findAllByStatus(status);
    }

    /**
     * Find all requests
     * @return found requests
     */
    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    /**
     * Save tariff
     * @param tariff
     */
    @Override
    @Transactional
    public void add(Tariff tariff) {
        tariffRepository.save(tariff);
    }

    /**
     * Get tariff by id
     * @param id id of this tariff
     * @return found tariff
     */
    @Override
    @Transactional(readOnly = true)
    public Tariff getTariffById(Long id) {
        return tariffRepository.findOne(id);
    }

    /**
     * Get all tariffs
     * @return found tariffs
     */
    @Override
    public List<Tariff> getTariffs() {
        Iterable<Tariff> f = tariffRepository.findAll();
        return (List<Tariff>) f;
    }


    /**
     * Get tariff by tariffName
     * @param tariffName name of tariff
     * @return found tariff
     */
    @Override
    @Transactional(readOnly = true)
    public Tariff getTariffByTariffName(String tariffName) {
        return tariffRepository.findByTariffName(tariffName);
    }

}
