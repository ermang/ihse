package com.eg.ihse.test.service;

import com.eg.ihse.controller.request.CreateExchangeReq;
import com.eg.ihse.entity.Exchange;
import com.eg.ihse.repo.ExchangeRepo;
import com.eg.ihse.repo.StockExchangeRelRepo;
import com.eg.ihse.repo.StockRepo;
import com.eg.ihse.service.ExchangeService;
import com.eg.ihse.util.Req2Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

}
