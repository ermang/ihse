package com.eg.ihse.test.util;

import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.service.request.CreateExchangeServiceReq;
import com.eg.ihse.service.request.CreateStockServiceReq;
import com.eg.ihse.util.ServiceReq2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ServiceReq2EntityTest {

    private final ServiceReq2Entity serviceReq2Entity = new ServiceReq2Entity();

    @Test
    public void create_stock_req_2_stock() {
        CreateStockServiceReq req = new CreateStockServiceReq("STOCK", "description", BigDecimal.ONE);

        Stock actual = serviceReq2Entity.createStockServiceReq2Stock(req);

        Assertions.assertEquals("STOCK", actual.getName());
        Assertions.assertEquals("description", actual.getDescription());
        Assertions.assertEquals(BigDecimal.ONE, actual.getPrice());
    }

    @Test
    public void create_exchange_req_2_exchange() {
        CreateExchangeServiceReq req = new CreateExchangeServiceReq("BIST", "description");


        Exchange actual = serviceReq2Entity.createExchangeReq2Exchange(req);

        Assertions.assertEquals("BIST", actual.getName());
        Assertions.assertEquals("description", actual.getDescription());
    }
}
