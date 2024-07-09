package com.eg.ihse.service.request;

import java.math.BigDecimal;

public class UpdateStockPriceServiceReq {

    public final String name;
    public final BigDecimal price;

    public UpdateStockPriceServiceReq(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}
