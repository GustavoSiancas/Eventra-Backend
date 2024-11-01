package com.example.demo.controller.client.response;

import com.example.demo.entity.enums.UserRole;

import java.time.LocalDate;

public record ClientResponse (
        Long id,
        String username,
        String email,
        String realname,
        LocalDate birthdate,
        String dni,
        String country,
        String gender,
        String Direction,
        String phone,
        UserRole role
) { }
