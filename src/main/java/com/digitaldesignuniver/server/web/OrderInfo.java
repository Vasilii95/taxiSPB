package com.digitaldesignuniver.server.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfo {
    private String clientName;
    private String clientPhone;
    private String addressFrom;
    private String addressTo;
    private String price;
    private String comment;
}