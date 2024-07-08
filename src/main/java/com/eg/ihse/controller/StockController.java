package com.eg.ihse.controller;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.service.StockService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public void createStock(@RequestBody @Valid CreateStockReq createStockReq){
        stockService.createStock(createStockReq);
    }
}
