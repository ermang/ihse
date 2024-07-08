package com.eg.ihse.repo;

import com.eg.ihse.entity.Stock;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StockRepo extends JpaRepository<Stock, Long> {

    Stock findByName(String name);

    void deleteByName(String name);
}
