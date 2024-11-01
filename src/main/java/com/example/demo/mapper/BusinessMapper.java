package com.example.demo.mapper;

import com.example.demo.controller.business.response.BusinessResponse;
import com.example.demo.entity.Business;
import com.example.demo.entity.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class BusinessMapper {
    public BusinessResponse toBusinessResponse(Business business) {
        if (business == null) {
            return null;
        }

        return new BusinessResponse(
                business.getId(),
                business.getUser().getUsername(),
                business.getUser().getEmail(),
                business.getBusinessName(),
                business.getRuc(),
                business.getAddress(),
                business.getReasonSocial(),
                business.getComercialName(),
                business.getCategory(),
                UserRole.BUSINESS
        );
    }
}
