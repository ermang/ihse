package com.eg.ihse.test.util;

import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.util.Req2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class Req2EntityTest {

    private final Req2Entity req2Entity = new Req2Entity();

    @Test
    public void create_stock_req_2_stock() {
        CreateStockReq req = new CreateStockReq();
        req.name = "STOCK";
        req.description = "description";
        req.price = BigDecimal.ONE;

        Stock actual = req2Entity.createStockReq2Stock(req);

        Assertions.assertEquals("STOCK", actual.getName());
        Assertions.assertEquals("description", actual.getDescription());
        Assertions.assertEquals(BigDecimal.ONE, actual.getPrice());
    }

    @Test
    public void create_exchange_req_2_exchange() {
        CreateExchangeReq req = new CreateExchangeReq();
        req.name = "BIST";
        req.description = "description";

        Exchange actual = req2Entity.createExchangeReq2Exchange(req);

        Assertions.assertEquals("BIST", actual.getName());
        Assertions.assertEquals("description", actual.getDescription());
    }
}
