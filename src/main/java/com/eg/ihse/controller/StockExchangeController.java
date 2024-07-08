package com.eg.ihse.controller;

import com.eg.ihse.controller.request.CreateStockExchangeReq;
import com.eg.ihse.service.StockExchangeService;
import com.eg.ihse.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    private final StockExchangeService stockExchangeService;

    public StockExchangeController(StockExchangeService stockExchangeService) {
        this.stockExchangeService = stockExchangeService;
    }

    @PostMapping
    public void createStockExchange(@RequestBody @Valid CreateStockExchangeReq createStockExchangeReq){
        stockExchangeService.createStockExchange(createStockExchangeReq);
    }
}
