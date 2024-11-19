package com.example.demo.controller.data.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MoneyRangeWeek(
        LocalDate date,
        BigDecimal amount
) {
}
