package com.example.demo.controller.ticket.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TickeSimpleRequest {
    public Long id;
    public String name;
    public String color;
    public Integer quantity;
    public BigDecimal price;

    public TickeSimpleRequest(Long id,String name, String color, Integer quantity, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
    }
}
