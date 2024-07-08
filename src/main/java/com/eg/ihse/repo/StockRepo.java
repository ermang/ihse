package com.eg.ihse.repo;

import com.eg.ihse.entity.Stock;

import com.eg.ihse.entity.projection.ReadStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StockRepo extends JpaRepository<Stock, Long> {

    Stock findByName(String name);

    void deleteByName(String name);

    @Query(value = "SELECT new com.eg.ihse.entity.projection.ReadStock(s.name AS name, s.description AS description, s.price AS price)" +
            "    FROM StockExchangeRel ser" +
            "    INNER JOIN Stock s ON s.id = ser.stock.id" +
            "    AND ser.exchange.name = :exchangeName")
    List<ReadStock> findAllStocksInExchangeRO(@Param("exchangeName") String exchangeName);
}
