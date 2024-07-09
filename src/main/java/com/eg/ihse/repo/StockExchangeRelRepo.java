package com.eg.ihse.repo;

import com.eg.ihse.entity.StockExchangeRel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StockExchangeRelRepo extends JpaRepository<StockExchangeRel, Long> {

    Long countByExchangeName(String exchangeName);

    void deleteByExchangeNameAndStockName(String exchangeName, String stockName);

    boolean existsByStockName(String stockName);

    StockExchangeRel findByExchangeNameAndStockName(String exchangeName, String stockName);
}
