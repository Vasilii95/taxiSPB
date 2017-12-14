package com.digitaldesignuniver.server.web;

import com.digitaldesignuniver.server.app.DriversOnline;
import com.digitaldesignuniver.server.backend.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DriverController {
    private DriversOnline driversOnline;

    @Autowired
    public DriverController(DriversOnline driversOnline) {
        this.driversOnline = driversOnline;
    }

    @GetMapping("/driver_login/{log}&{pass}")
    public Long login(@PathVariable String log,
                      @PathVariable String pass) {
        return driversOnline.addDriver(log,pass);
    }

    @GetMapping("/driver_coordinates/{id}&{lng}&{lat}")
    public OrderInfo coordinates(@PathVariable String id,
                                 @PathVariable String lng,
                                 @PathVariable String lat) {
        Order order = driversOnline.newCoordinates(new Long(id), new Double(lat), new Double(lng));
        if(order!=null) {
            return OrderInfo.builder().addressFrom(order.getAddressFrom()).addressTo(order.getAddressTo())
                    .clientName(order.getRequest().getCustomerName()).clientPhone(order.getRequest().getCustomerPhoneNumber())
                    .price(order.getRequest().getPrice().toString()).comment(order.getRequest().getComment()).build();
        }
        return null;
    }

    @GetMapping("/driver_order_is_done/{id}")
    public Boolean orderIsDone(@PathVariable String id) {
        driversOnline.orderIsDone(new Long(id));
        return true;
    }

    @GetMapping("/driver_logout/{id}")
    public Boolean logout(@PathVariable String id) {
        driversOnline.delDriver(new Long(id));
        return true;
    }
}