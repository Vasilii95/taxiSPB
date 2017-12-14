package com.digitaldesignuniver.server;

import com.digitaldesignuniver.server.web.OrderInfo;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;

public class DriverTests {
    private static final String URL_LOGIN = "http://localhost:8056/driver_login";
    private static final String URL_UPDATE_COORDINATES = "http://localhost:8056/driver_coordinates";
    private static final String URL_ORDER_IS_DONE = "http://localhost:8056/driver_order_is_done";
    private static final String URL_LOGOUT = "http://localhost:8056/driver_logout";

    @Test
    public void driverTest() throws InterruptedException {
        String url = URL_LOGIN + "/driver&driver";
        RestTemplate restTemplate = new RestTemplate();
        Long id = restTemplate.getForObject(url, Long.class);
        assertTrue(id==1L);
        for(int i = 0; i<2;i++) {
            Boolean flag = true;
            while (flag) {
                sleep(6000);
                url = URL_UPDATE_COORDINATES + '/' + id + "&30.311429&59.966662";
                OrderInfo orderInfo = restTemplate.getForObject(url, OrderInfo.class);
                if (orderInfo != null) {
                    flag = false;
                    sleep(5000);
                    url = URL_ORDER_IS_DONE + '/' + id;
                    Boolean res = restTemplate.getForObject(url, Boolean.class);
                    assertTrue(res);
                }
            }
        }
        url = URL_LOGOUT + '/'+id;
        Boolean res = restTemplate.getForObject(url, Boolean.class);
        assertTrue(res);
    }

    @Test
    public void driver1Test() throws InterruptedException {
        String url = URL_LOGIN + "/driver1&driver1";
        RestTemplate restTemplate = new RestTemplate();
        Long id = restTemplate.getForObject(url, Long.class);
        assertTrue(id==2L);
        for(int i = 0; i<2;i++) {
            Boolean flag = true;
            while (flag) {
                sleep(6000);
                url = URL_UPDATE_COORDINATES + '/' + id + "&30.234493&59.948192";
                OrderInfo orderInfo = restTemplate.getForObject(url, OrderInfo.class);
                if (orderInfo != null) {
                    flag = false;
                    sleep(5000);
                    url = URL_ORDER_IS_DONE + '/'+id;
                    Boolean res = restTemplate.getForObject(url, Boolean.class);
                    assertTrue(res);
                }
            }
        }
        url = URL_LOGOUT + '/'+id;
        Boolean res = restTemplate.getForObject(url, Boolean.class);
        assertTrue(res);
    }
}
