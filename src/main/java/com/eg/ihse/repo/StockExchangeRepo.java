package com.eg.ihse.repo;

import com.eg.ihse.entity.Stock;
import com.eg.ihse.entity.StockExchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockExchangeRepo extends JpaRepository<StockExchange, Long> {

}
