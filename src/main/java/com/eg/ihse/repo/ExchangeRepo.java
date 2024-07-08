package com.eg.ihse.repo;

import com.eg.ihse.entity.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeRepo extends JpaRepository<Exchange, Long> {

    Exchange findByName(String name);

}
