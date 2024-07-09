package com.eg.ihse.test.service;

import com.eg.ihse.controller.request.AddStock2ExchangeReq;
import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.controller.request.DeleteStockFromExchangeReq;
import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchangeRel;
import com.eg.ihse.entity.projection.ReadStock;
import com.eg.ihse.repo.ExchangeRepo;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.ExchangeService;
import com.eg.ihse.util.Req2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ExchangeServiceTest {

    private ExchangeRepo exchangeRepo;
    private StockRepo stockRepo;
    private StockExchangeRelRepo stockExchangeRelRepo;
    private Req2Entity req2Entity;
    private ExchangeService exchangeService;

    @BeforeEach
    public void setup() {
        exchangeRepo = Mockito.mock(ExchangeRepo.class);
        stockRepo = Mockito.mock(StockRepo.class);
        stockExchangeRelRepo = Mockito.mock(StockExchangeRelRepo.class);
        req2Entity = Mockito.mock(Req2Entity.class);
        exchangeService = new ExchangeService(exchangeRepo, stockRepo, stockExchangeRelRepo, req2Entity);
    }

    @Test
    public void create_exchange() {
        CreateExchangeReq req = new CreateExchangeReq();
        req.name = "BIST";
        req.description = "description";

        exchangeService.createExchange(req);
    }

    @Test
    public void create_exchange_throws_ex_when_an_exchange_with_same_name_exists() {
        CreateExchangeReq req = new CreateExchangeReq();
        req.name = "BIST";
        req.description = "description";

        Mockito.when(exchangeRepo.findByName(req.name)).thenReturn(new Exchange());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           exchangeService.createExchange(req);} );
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_exchange_does_not_exist() {
        AddStock2ExchangeReq req = new AddStock2ExchangeReq();
        req.exchangeName = "BIST";

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.addStock2Exchange(req);
        });

        Assertions.assertEquals("exchange with name BIST does not exist", actual.getMessage());
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_stock_does_not_exist() {
        AddStock2ExchangeReq req = new AddStock2ExchangeReq();
        req.exchangeName = "BIST";
        req.stockName = "STOCK";

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.addStock2Exchange(req);
        });

        Assertions.assertEquals("stock with name STOCK does not exist", actual.getMessage());
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_stock_already_registered_to_exchange() {
        AddStock2ExchangeReq req = new AddStock2ExchangeReq();
        req.exchangeName = "BIST";
        req.stockName = "STOCK";

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());
        Mockito.when(stockRepo.findByName(req.stockName)).thenReturn(new Stock());
        Mockito.when(stockExchangeRelRepo.findByExchangeNameAndStockName(req.exchangeName, req.stockName)).thenReturn(new StockExchangeRel());

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.addStock2Exchange(req);
        });

        Assertions.assertEquals("stock STOCK is already registered to exchange BIST", actual.getMessage());
    }

    @Test
    public void add_stock_2_exchange() {
        AddStock2ExchangeReq req = new AddStock2ExchangeReq();
        req.exchangeName = "BIST";
        req.stockName = "STOCK";

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());
        Mockito.when(stockRepo.findByName(req.stockName)).thenReturn(new Stock());

        exchangeService.addStock2Exchange(req);
    }

    @Test
    public void delete_stock_from_exchange_throws_ex_when_stock_not_registered() {
        DeleteStockFromExchangeReq req = new DeleteStockFromExchangeReq();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.deleteStockFromExchange(req);} );
    }

    @Test
    public void delete_stock_from_exchange() {
        DeleteStockFromExchangeReq req = new DeleteStockFromExchangeReq();
        req.exchangeName = "BIST";
        req.stockName = "STOCK";

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());
        Mockito.when(stockExchangeRelRepo.findByExchangeNameAndStockName(req.exchangeName, req.stockName)).thenReturn(new StockExchangeRel());

        exchangeService.deleteStockFromExchange(req);
    }

    @Test
    public void get_all_stocks_in_exchange() {
        List<ReadStock> actual = exchangeService.getAllStocksInExchange(null);

        Assertions.assertTrue(actual.isEmpty());
    }

}
