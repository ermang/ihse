package com.eg.ihse.util;

import com.eg.ihse.controller.request.*;
import com.eg.ihse.service.request.*;
import org.springframework.stereotype.Component;

@Component
public class Req2ServiceReq {

    public CreateStockServiceReq createStockReq2CreateStockServiceReq(CreateStockReq req) {

        CreateStockServiceReq serviceReq = new CreateStockServiceReq(req.name, req.description, req.price);

        return serviceReq;
    }

    public CreateExchangeServiceReq createExchangeReq2CreateExchangeServiceReq(CreateExchangeReq req) {

        CreateExchangeServiceReq serviceReq = new CreateExchangeServiceReq(req.name, req.description);

        return serviceReq;
    }

    public UpdateStockPriceServiceReq updateStockPriceReq2UpdateStockPriceServiceReq(UpdateStockPriceReq req) {
        UpdateStockPriceServiceReq serviceReq = new UpdateStockPriceServiceReq(req.name, req.price);

        return serviceReq;
    }

    public DeleteStockFromExchangeServiceReq deleteStockFromExchangeReq2DeleteStockFromExchangeServiceReq(DeleteStockFromExchangeReq req) {
        DeleteStockFromExchangeServiceReq serviceReq = new DeleteStockFromExchangeServiceReq(req.exchangeName, req.stockName);

        return serviceReq;
    }

    public AddStock2ExchangeServiceReq addStock2ExchangeReq2AddStock2ExchangeServiceReq(AddStock2ExchangeReq req) {
        AddStock2ExchangeServiceReq serviceReq = new AddStock2ExchangeServiceReq(req.exchangeName, req.stockName);

        return serviceReq;
    }
}
