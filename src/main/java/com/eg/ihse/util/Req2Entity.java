package com.eg.ihse.util;

import com.eg.ihse.controller.request.CreateStockReq;
import com.eg.ihse.entity.Stock;
import org.springframework.stereotype.Service;

@Service
public class Req2Entity {

    public Stock createStockReq2Stock(CreateStockReq csr) {
        Stock s = new Stock();

        s.setName(csr.name);
        s.setDescription(csr.description);

        return s;
    }
}
