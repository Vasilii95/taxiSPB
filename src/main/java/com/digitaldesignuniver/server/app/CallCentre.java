package com.digitaldesignuniver.server.app;

import com.digitaldesignuniver.server.backend.model.Dispatcher;
import com.digitaldesignuniver.server.backend.service.TaxiServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class CallCentre {

    public static Dispatcher dispatcher;
    private TaxiServiceImpl taxiService;
    private OrderCreator orderCreator;
    private static Logger log = Logger.getLogger(CallCentre.class.getName());

    @Autowired
    public CallCentre(TaxiServiceImpl taxiService, OrderCreator orderCreator) {
        this.taxiService = taxiService;
        this.orderCreator = orderCreator;
    }

    @PostConstruct
    public void init(){
        Thread thread = new Thread(orderCreator);
        thread.start();
    }


    /**
     * Search dispatcher by login and password
     * @param login dispatcher login
     * @param password dispatcher password
     * @return true if found, false if not found
     */
    public boolean isRegistrated(String login, String password) {
        dispatcher = taxiService.getDispatcherByLogin(login);
        if(dispatcher==null||!dispatcher.getPassword().equals(password)){
            return false;
        }
        log.info("Operator "+login+" "+password+" is authorized");
        return true;
    }
}
