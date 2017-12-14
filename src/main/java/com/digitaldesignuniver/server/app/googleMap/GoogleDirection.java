package com.digitaldesignuniver.server.app.googleMap;


import com.digitaldesignuniver.server.app.CallCentre;
import com.digitaldesignuniver.server.backend.model.Request;
import com.digitaldesignuniver.server.backend.model.Tariff;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GoogleDirection {
    private static final String BASE_URL = "http://maps.googleapis.com/maps/api/directions/json";

    /**
     * Get information about request
     * @param time request time
     * @param comment request comment
     * @param name customer name
     * @param phone customer phone number
     * @param addressFrom request addressFrom
     * @param addressTo request addressTo
     * @param tariff request tariff
     * @return Created request
     * @throws IOException
     */
    public Request getOrderInfo(LocalDateTime time, String comment, String name, String phone, String addressFrom,
                                String addressTo, Tariff tariff) throws IOException {
        Map<String, String> params = Maps.newHashMap();
        params.put("sensor", "false");
        params.put("language", "ru");
        params.put("mode", "driving");
        params.put("origin", addressFrom);
        params.put("destination", addressTo);
        String url = BASE_URL + '?' + encodeParams(params);
        JSONObject response = JsonReader.read(url);
        JSONObject location = response.getJSONArray("routes").getJSONObject(0);
        location = location.getJSONArray("legs").getJSONObject(0);

        Double distance = location.getJSONObject("distance").getDouble("value");
        Double startlatitude = location.getJSONObject("start_location").getDouble("lat");
        Double srartlongitude = location.getJSONObject("start_location").getDouble("lng");
        Double endlatitude = location.getJSONObject("end_location").getDouble("lat");
        Double endlongitude = location.getJSONObject("end_location").getDouble("lng");
        String duration = location.getJSONObject("duration").getString("text");

        Request request = new Request();
        request.setStatus(false);
        request.setTariff(tariff);
        request.setComment(comment);
        request.setAddressFrom(addressFrom);
        request.setAddressTo(addressTo);
        request.setDistance(distance);
        request.setCustomerName(name);
        request.setCustomerPhoneNumber(phone);
        request.setPrice(BigDecimal.valueOf(tariff.getStartPrice().
                doubleValue()+distance*(tariff.getPrice()).doubleValue()));
        request.setDuration(duration);
        request.setStatus(false);
        request.setTime(time);
        request.setTariff(tariff);
        request.setDispatcher(CallCentre.dispatcher);
        request.setStartLatitude(startlatitude);
        request.setStartLongitude(srartlongitude);
        request.setEndLatitude(endlatitude);
        request.setEndLongitude(endlongitude);
        return request;
    }

    /**
     * Create string params
     * @param params query parameters
     * @return string params
     */
    private static String encodeParams(Map<String, String> params) {
        String paramsUrl = Joiner.on('&').join(
                params.entrySet().stream().map(input -> {
                    try {
                        StringBuffer buffer = new StringBuffer();
                        buffer.append(input.getKey());
                        buffer.append('=');
                        buffer.append(URLEncoder.encode(input.getValue(), "utf-8"));
                        return buffer.toString();
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList()));
        return paramsUrl;
    }
}
