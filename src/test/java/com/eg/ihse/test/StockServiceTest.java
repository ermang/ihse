package com.eg.ihse.test;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.controller.request.UpdateStockPriceReq;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.StockService;
import com.eg.ihse.util.Req2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class StockServiceTest {

    private StockRepo stockRepo;
    private StockExchangeRelRepo stockExchangeRelRepo;
    private Req2Entity req2Entity;
    private StockService stockService;

    @BeforeEach
    public void setup() {
        stockRepo = Mockito.mock(StockRepo.class);
        stockExchangeRelRepo = Mockito.mock(StockExchangeRelRepo.class);
        req2Entity = Mockito.mock(Req2Entity.class);
        stockService = new StockService(stockRepo, stockExchangeRelRepo, req2Entity);
    }

    @Test
    public void sample() {
        Assertions.assertTrue(true);
    }

    @Test
    public void create_stock() {
        CreateStockReq req = new CreateStockReq();
        req.name = "STOCK";
        req.description = "description";
        req.price = BigDecimal.ONE;

        stockService.createStock(req);
    }

    @Test
    public void create_stock_throws_ex_when_a_stock_with_same_name_exists() {
        CreateStockReq req = new CreateStockReq();
        req.name = "STOCK";
        req.description = "description";
        req.price = BigDecimal.ONE;

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
        UpdateStockPriceReq req = new UpdateStockPriceReq();
        req.name = "STOCK";
        req.price = BigDecimal.ONE;

        Mockito.when(stockRepo.findByName(req.name)).thenReturn(new Stock());

        stockService.updatePrice(req);
    }

    @Test
    public void update_price_throws_ex_when_stock_does_not_exist() {
        UpdateStockPriceReq req = new UpdateStockPriceReq();
        req.name = "STOCK";
        req.price = BigDecimal.ONE;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {stockService.updatePrice(req);} );
    }

}
