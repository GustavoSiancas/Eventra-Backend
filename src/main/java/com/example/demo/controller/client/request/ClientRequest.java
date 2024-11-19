package com.example.demo.controller.client.request;

import java.time.LocalDate;

public record ClientRequest(
        String realName,
        LocalDate birthDate,
        String dni,
        String country,
        String gender,
        String direction,
        String phone,
        Long clientId // Si necesitas
) {}
