package com.digitaldesignuniver.server.app;

import com.digitaldesignuniver.server.backend.model.Driver;
import com.digitaldesignuniver.server.backend.model.Order;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.model.Tariff;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;



/**
 * List of online drivers
 */
@Component
public class DriversOnline {

    private static Map<Long,Driver> driverMap;
    private final TaxiServiceImpl taxiService;
    private RequestList requestList;
    private static Logger log = Logger.getLogger(DriversOnline.class.getName());

    @Autowired
    public DriversOnline(TaxiServiceImpl taxiService, RequestList requestList) {
        this.taxiService = taxiService;
        this.requestList = requestList;
    }

    @PostConstruct
    public void init(){
        driverMap = Collections.synchronizedMap(new HashMap<>());
    }

    /**
     * Add new order to the selected driver
     * @param order new order
     * @param driver the driver who is closest to the starting point
     */
    public void addOrder(Order order, Driver driver){
        if(!driver.getStatus()){
            driver.setNextOrderId(order.getId().toString());
        }else {
            driver.setStatus(false);
            driver.setOrderId(order.getId().toString());
        }
        log.info("Added order to "+driver.getName()+" driver");
        taxiService.add(driver);
    }

    /**
     *  Driver authorization
     * @param login login of driver account
     * @param password password of driver account
     * @return id of authorized driver
     */
    public Long addDriver(String login, String password){
        Driver driver = taxiService.getDriverByLogin(login);
        if(driver==null||!driver.getPassword().equals(password)){
            return null;
        }
        if(!driverMap.containsKey(driver.getId())){
            driverMap.put(driver.getId(),driver);
        }
        return driver.getId();
    }

    /**
     *  Driver logout
     * @param id driver id
     */
    public void delDriver(Long id){
        Driver driver = driverMap.get(id);
        if(!driverMap.get(id).getStatus()){
            if(driver.getNextOrderId().equals("noOrder")){
                Order order = taxiService.getOrderById(new Long(driver.getNextOrderId()));
                Request request = order.getRequest();
                request.setStatus(false);
                driver.setNextOrderId("noOrder");
                taxiService.deleteOrder(order);
                requestList.addRequest(request);
            }
            taxiService.add(driver);
        }
        log.info("Removed "+driver.getName()+" driver");
        driverMap.remove(id);
    }

    /**
     *Update coordinates of driver
     * @param id driver id
     * @param latitude driver latitude
     * @param longitude driver longitude
     * @return order of this driver
     */
    public synchronized Order newCoordinates(Long id, Double latitude, Double longitude){
        Driver driver = driverMap.get(id);
        driver.setLatitude(latitude);
        driver.setLongitude(longitude);
        if(!driver.getOrderId().equals("noOrder")) {
            return taxiService.getOrderById(new Long(driver.getOrderId()));
        }else{
            return null;
        }
    }

    public int freeDrivers(){
        List<Driver> drivers = new ArrayList<>(driverMap.values());
        int size = drivers.stream().filter(driver-> driver.getStatus()==true).collect(Collectors.toList()).size();
        return size;
    }

    /**
     *Update status of driver, and recount of taxi driver's cash
     * @param id drivers id
     */
    public void orderIsDone(Long id){
        Driver driver = driverMap.get(id);
        Order order = taxiService.getOrderById(new Long(driver.getOrderId()));
        driver.setMoney(driver.getMoney().add(order.getRequest().getPrice()));
        order.setStatus(true);
        if(driver.getNextOrderId().equals("noOrder")) {
            driver.setStatus(true);
            driver.setOrderId("noOrder");
        }else {
            driver.setOrderId(driver.getNextOrderId());
            driver.setNextOrderId("noOrder");
        }
        log.info("Order "+order.getId()+" is done");
        taxiService.add(order);
        taxiService.add(driver);
    }

    /**
     * Find the nearest driver
     * @param latitude  request latitude
     * @param longitude request longitude
     * @param tariff request tariff
     * @return nearest driver
     */
    public Driver findDriver(Double latitude, Double longitude, Tariff tariff) {
        List<Driver> drivers = new ArrayList<>(driverMap.values());
        Driver result = null;
        List<Driver> freeDrivers = drivers.stream()
                .filter(Driver::getStatus).filter(driver -> driver.getCar().getTariff()
                        .getTariffName().equals(tariff.getTariffName())).collect(Collectors.toList()); //список автомобилистов готовых принять заказ
        List<Driver> worksDrivers = drivers.stream().filter(driver -> !driver.getStatus()&&driver.getNextOrderId()==null)
                .filter(driver -> driver.getCar().getTariff().getTariffName().equals(tariff.getTariffName()))
                .collect(Collectors.toList());
        if (!freeDrivers.isEmpty()) {
            freeDrivers = freeDrivers.stream().filter(driver -> driver.getCar().getTariff().getTariffName()
                    .equals(tariff.getTariffName())).collect(Collectors.toList());
            result = freeDrivers.stream().min(Comparator.comparingDouble(p -> Math.sqrt(Math.pow(p.getLatitude()
                    - latitude, 2) + Math.pow(p.getLongitude() - longitude, 2)))).get();
        }
        if (!worksDrivers.isEmpty()) {
            worksDrivers = worksDrivers.stream().filter(driver -> driver.getNextOrderId().equals("noOrder"))
                    .filter(driver -> driver.getCar().getTariff().getTariffName()
                            .equals(tariff.getTariffName())).collect(Collectors.toList());
            Driver worksDriver = worksDrivers.stream().min(Comparator.comparingDouble(p ->
                    Math.sqrt(Math.pow(p.getLatitude() - taxiService.getOrderById(new Long(p.getOrderId())).getRequest().getEndLatitude(), 2)
                    + Math.pow(p.getLongitude() - taxiService.getOrderById(new Long(p.getOrderId())).getRequest().getEndLongitude(), 2))
                    + Math.sqrt(Math.pow(taxiService.getOrderById(new Long(p.getOrderId())).getRequest().getEndLatitude() - latitude, 2)
                    + Math.pow(taxiService.getOrderById(new Long(p.getOrderId())).getRequest().getEndLongitude() - longitude, 2)))).get();
            if (result == null) {
                result = worksDriver;
            }else{
                if(Math.sqrt(Math.pow(result.getLatitude()
                        - latitude, 2) + Math.pow(result.getLongitude() - longitude, 2)) > Math.sqrt(Math.pow(worksDriver.getLatitude()
                        - taxiService.getOrderById(new Long(worksDriver.getOrderId())).getRequest().getEndLatitude(), 2)
                        + Math.pow(worksDriver.getLongitude() - taxiService.getOrderById(new Long(worksDriver.getOrderId())).getRequest().getEndLongitude(), 2))
                        + Math.sqrt(Math.pow(taxiService.getOrderById(new Long(worksDriver.getOrderId())).getRequest().getEndLatitude() - latitude, 2)
                        + Math.pow(taxiService.getOrderById(new Long(worksDriver.getOrderId())).getRequest().getEndLongitude() - longitude, 2))){
                    result = worksDriver;
                }
            }
        }
        if(result!=null) {
            log.info("Nearest driver is " + result.getName());
        }
        return result;
    }
}


