package com.eg.ihse.test.service;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.StockService;
import com.eg.ihse.service.request.CreateStockServiceReq;
import com.eg.ihse.service.request.UpdateStockPriceServiceReq;
import com.eg.ihse.util.Req2Entity;
import com.eg.ihse.util.ServiceReq2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class StockServiceTest {

    private ServiceReq2Entity serviceReq2Entity = new ServiceReq2Entity();
    private StockRepo stockRepo;
    private StockExchangeRelRepo stockExchangeRelRepo;

    private StockService stockService;

    @BeforeEach
    public void setup() {
        stockRepo = Mockito.mock(StockRepo.class);
        stockExchangeRelRepo = Mockito.mock(StockExchangeRelRepo.class);
        stockService = new StockService(serviceReq2Entity, stockRepo, stockExchangeRelRepo);
    }

    @Test
    public void sample() {
        Assertions.assertTrue(true);
    }

    @Test
    public void create_stock() {
        CreateStockServiceReq req = new CreateStockServiceReq("STOCK", "description", BigDecimal.ONE);

        stockService.createStock(req);
    }

    @Test
    public void create_stock_throws_ex_when_a_stock_with_same_name_exists() {
        CreateStockServiceReq req = new CreateStockServiceReq("STOCK", "description", BigDecimal.ONE);

        Mockito.when(stockRepo.findByName(req.name)).thenReturn(new Stock());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {stockService.createStock(req);} );
    }

    @Test
    public void delete_stock() {
        String stockName = "STOCK";

        stockService.deleteStock(stockName);
    }

    @Test
    public void delete_stock_throws_ex_when_stock_is_registered_to_an_exchange() {
        String stockName = "STOCK";

        Mockito.when(stockExchangeRelRepo.existsByStockName(stockName)).thenReturn(true);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {stockService.deleteStock(stockName);} );
    }

    @Test
    public void update_price() {
        UpdateStockPriceServiceReq req = new UpdateStockPriceServiceReq("STOCK", BigDecimal.ONE);

        Mockito.when(stockRepo.findByName(req.name)).thenReturn(new Stock());

        stockService.updatePrice(req);
    }

    @Test
    public void update_price_throws_ex_when_stock_does_not_exist() {
        UpdateStockPriceServiceReq req = new UpdateStockPriceServiceReq("STOCK", BigDecimal.ONE);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {stockService.updatePrice(req);} );
    }

}
