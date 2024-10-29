package com.example.demo.controller.activity.response;

import com.example.demo.entity.enums.ActivityType;

import java.math.BigDecimal;
import java.util.List;

public record ActivityCard (
        Long id,
        String Imagen,
        String Titulo,
        List<String> tags,
        BigDecimal precio,
        ActivityType tipo
) {
}
