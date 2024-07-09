package com.eg.ihse.test.service;

import com.eg.ihse.entity.Exchange;
import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchangeRel;
import com.eg.ihse.entity.projection.ReadStock;
import com.eg.ihse.repo.ExchangeRepo;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.ExchangeService;
import com.eg.ihse.service.request.AddStock2ExchangeServiceReq;
import com.eg.ihse.service.request.CreateExchangeServiceReq;
import com.eg.ihse.service.request.DeleteStockFromExchangeServiceReq;
import com.eg.ihse.util.ServiceReq2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class ExchangeServiceTest {

    private ServiceReq2Entity serviceReq2Entity = new ServiceReq2Entity();
    private ExchangeRepo exchangeRepo;
    private StockRepo stockRepo;
    private StockExchangeRelRepo stockExchangeRelRepo;
    private ExchangeService exchangeService;

    @BeforeEach
    public void setup() {
        exchangeRepo = Mockito.mock(ExchangeRepo.class);
        stockRepo = Mockito.mock(StockRepo.class);
        stockExchangeRelRepo = Mockito.mock(StockExchangeRelRepo.class);
        exchangeService = new ExchangeService(serviceReq2Entity, exchangeRepo, stockRepo, stockExchangeRelRepo);
    }

    @Test
    public void create_exchange() {
        CreateExchangeServiceReq req = new CreateExchangeServiceReq("BIST", "description");

        exchangeService.createExchange(req);
    }

    @Test
    public void create_exchange_throws_ex_when_an_exchange_with_same_name_exists() {
        CreateExchangeServiceReq req = new CreateExchangeServiceReq("BIST", "description");

        Mockito.when(exchangeRepo.findByName(req.name)).thenReturn(new Exchange());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           exchangeService.createExchange(req);} );
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_exchange_does_not_exist() {
        AddStock2ExchangeServiceReq req = new AddStock2ExchangeServiceReq("BIST", null);

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.addStock2Exchange(req);
        });

        Assertions.assertEquals("exchange with name BIST does not exist", actual.getMessage());
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_stock_does_not_exist() {
        AddStock2ExchangeServiceReq req = new AddStock2ExchangeServiceReq("BIST", "STOCK");

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.addStock2Exchange(req);
        });

        Assertions.assertEquals("stock with name STOCK does not exist", actual.getMessage());
    }

    @Test
    public void add_stock_2_exchange_throws_ex_when_stock_already_registered_to_exchange() {
        AddStock2ExchangeServiceReq req = new AddStock2ExchangeServiceReq("BIST", "STOCK");

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
        AddStock2ExchangeServiceReq req = new AddStock2ExchangeServiceReq("BIST", "STOCK");

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());
        Mockito.when(stockRepo.findByName(req.stockName)).thenReturn(new Stock());

        exchangeService.addStock2Exchange(req);
    }

    @Test
    public void delete_stock_from_exchange_throws_ex_when_stock_not_registered() {
        DeleteStockFromExchangeServiceReq req = new DeleteStockFromExchangeServiceReq(null, null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.deleteStockFromExchange(req);} );
    }

    @Test
    public void delete_stock_from_exchange() {
        DeleteStockFromExchangeServiceReq req = new DeleteStockFromExchangeServiceReq("BIST", "STOCK");

        Mockito.when(exchangeRepo.findByName(req.exchangeName)).thenReturn(new Exchange());
        Mockito.when(stockExchangeRelRepo.findByExchangeNameAndStockName(req.exchangeName, req.stockName)).thenReturn(new StockExchangeRel());

        exchangeService.deleteStockFromExchange(req);
    }

    @Test
    public void get_all_stocks_in_exchange_throws_ex_when_exchange_does_not_exist() {

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.getAllStocksInExchange("NO_SUCH_EXCHANGE");
        });

        Assertions.assertEquals("exchange with name NO_SUCH_EXCHANGE does not exist", actual.getMessage());
    }

    @Test
    public void get_all_stocks_in_exchange_throws_ex_when_exchange_is_not_live() {

        Mockito.when(exchangeRepo.findByName("EXCHANGE_NOT_LIVE")).thenReturn(new Exchange());

        Throwable actual = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            exchangeService.getAllStocksInExchange("EXCHANGE_NOT_LIVE");
        });

        Assertions.assertEquals("exchange with name EXCHANGE_NOT_LIVE is not live", actual.getMessage());
    }

    @Test
    public void get_all_stocks_in_exchange() {

        Exchange exchange = new Exchange();
        exchange.setLive(true);

        Mockito.when(exchangeRepo.findByName("BIST")).thenReturn(exchange);

        List<ReadStock> actual = exchangeService.getAllStocksInExchange("BIST");

        Assertions.assertTrue(actual.isEmpty());
    }

}
