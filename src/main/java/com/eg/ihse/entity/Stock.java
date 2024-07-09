package com.eg.ihse.entity;

import com.eg.ihse.util.Constant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Stock extends BaseEntity{

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Column(nullable = false, precision = Constant.STOCK_PRICE_DB_PRECISION, scale = Constant.STOCK_PRICE_MIN_FRACTIONAL_PART)
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
