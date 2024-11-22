package com.example.demo.controller.activity.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FilterCardsRequest (
        BigDecimal max,
        BigDecimal min,
        LocalDate startDate,
        LocalDate endDate
){
}
