package com.eg.ihse.service.request;

import java.math.BigDecimal;

public class CreateStockServiceReq {

    public final String name;
    public final String description;
    public final BigDecimal price;

    public CreateStockServiceReq(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
