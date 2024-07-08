package com.eg.ihse.controller;

import com.eg.ihse.controller.request.AddStock2ExchangeReq;
import com.eg.ihse.controller.request.CreateStockExchangeReq;
import com.eg.ihse.controller.request.DeleteStockFromExchangeReq;
import com.eg.ihse.service.ExchangeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @PostMapping
    public void createStockExchange(@RequestBody @Valid CreateStockExchangeReq createStockExchangeReq) {
        exchangeService.createStockExchange(createStockExchangeReq);
    }

    @PostMapping("/stock")
    public void addStock(@RequestBody @Valid AddStock2ExchangeReq addStock2ExchangeReq) {
        exchangeService.addStock2Exchange(addStock2ExchangeReq);
    }

    @DeleteMapping("/stock")
    public void deleteStock(@RequestBody @Valid DeleteStockFromExchangeReq deleteStockFromExchangeReq) {
        exchangeService.deleteStockFromExchange(deleteStockFromExchangeReq);
    }
}
