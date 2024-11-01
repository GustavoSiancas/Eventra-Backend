package com.example.demo.controller.business.response;

import com.example.demo.entity.enums.UserRole;

public record BusinessResponse(
        Long id,
        String username,
        String email,
        String businessName,
        String ruc,
        String address,
        String reasonSocial,
        String comercialName,
        String category,
        UserRole role
) { }
