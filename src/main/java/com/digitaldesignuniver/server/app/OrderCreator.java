package com.digitaldesignuniver.server.app;

import com.digitaldesignuniver.server.backend.model.Driver;
import com.digitaldesignuniver.server.backend.model.Order;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

@Component
public class OrderCreator implements Runnable {
    private TaxiServiceImpl taxiService;
    private DriversOnline driversOnline;
    private static Logger log = Logger.getLogger(OrderCreator.class.getName());

    @Autowired
    public OrderCreator(TaxiServiceImpl taxiService, DriversOnline driversOnline) {
        this.taxiService = taxiService;
        this.driversOnline = driversOnline;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<Request> elements = taxiService.findAllByStatus(false).stream().filter(request -> request.getTime()
                    .isBefore(LocalDateTime.now().plusHours(1))).collect(Collectors.toList());
            elements.forEach(request -> createOrder(request));
        }
    }

    /**
     * Create a new order if we found the right driver
     * @param request suitable request
     */
    private void createOrder(Request request){
        Driver driver = driversOnline.findDriver(request.getStartLatitude(),request.getStartLongitude(),request.getTariff()); //поиск подходящего водителя
        if(driver!=null){
            Order order = new Order();
            order.setDriver(driver);
            order.setRequest(request);
            order.setStatus(false);
            taxiService.add(order);
            order.setId(taxiService.getIdByOrder(order));
            request.setStatus(true);
            taxiService.add(request);
            driversOnline.addOrder(order,driver);
            log.info("Added new order "+order.getId()+" to "+driver.getName()+" driver");
        }
    }
}
