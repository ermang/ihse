package com.eg.ihse.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Table(uniqueConstraints = {
        @UniqueConstraint(name = "stock_exchange_rel", columnNames = {"stock_id", "exchange_id"})
})
@Entity
public class StockExchangeRel extends BaseEntity{

    @ManyToOne(optional = false)
    private Stock stock;

    @ManyToOne(optional = false)
    private Exchange exchange;

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}
