package com.example.demo.controller.ticket.Response;

import java.math.BigDecimal;

public record TypeTIcketResponse (
        Long idTicket,
        String name,
        BigDecimal price
){
}
