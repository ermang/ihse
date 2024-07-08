package com.eg.ihse.repo;

import com.eg.ihse.entity.StockExchangeRel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StockExchangeRelRepo extends JpaRepository<StockExchangeRel, Long> {

    List<StockExchangeRel> findAllByExchangeName(String exchangeName);

    Long countByExchangeName(String exchangeName);
}
