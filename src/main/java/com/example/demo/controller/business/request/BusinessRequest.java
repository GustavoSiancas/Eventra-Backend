package com.example.demo.controller.business.request;


public record BusinessRequest(
        String businessName,
        String ruc,
        String address,
        String reasonSocial,
        String comercialName,
        String category,
        Long userId // ID del usuario asociado
) {}
