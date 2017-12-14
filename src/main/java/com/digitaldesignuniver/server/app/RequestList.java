package com.digitaldesignuniver.server.app;

import com.digitaldesignuniver.server.backend.model.Driver;
import com.digitaldesignuniver.server.backend.model.Order;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import lombok.Data;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Data
public class RequestList {

    private TaxiServiceImpl taxiService;
    private static Logger log = Logger.getLogger(RequestList.class.getName());

    @Autowired
    public RequestList( TaxiServiceImpl taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find request by id
     * @param id request id
     * @return found request
     */
    public Request findById(Long  id){
        return taxiService.findById(id);
    }


    /**
     * Add request to db
     * @param request saved request
     */
    public void addRequest(Request request){
            taxiService.add(request);
    }

    /**
     * Cancel request order and request
     * @param request request to cancel
     */
    public void cancelRequest(Request request){
        Order order = taxiService.findByRequest(request);
        if(order!=null){
            Driver driver = order.getDriver();
            if(driver.getNextOrderId()==null) {
                driver.setOrderId(null);
                driver.setStatus(true);
            }else {
                driver.setOrderId(driver.getNextOrderId());
                driver.setNextOrderId(null);
            }
            taxiService.add(driver);
            log.info("Removed order "+order.getId());
            taxiService.deleteOrder(order);
        }
        log.info("Removed request "+request.getId());
        taxiService.deleteRequest(request);
    }
}
