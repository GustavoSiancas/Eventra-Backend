package com.example.demo.mapper;

import com.example.demo.controller.client.response.ClientResponse;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.enums.UserRole;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {


    public ClientResponse toClientResponse(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }

        ClientResponse clientResponse = new ClientResponse(
                clientEntity.getId(),
                clientEntity.getUser().getUsername(),
                clientEntity.getUser().getEmail(),
                clientEntity.getRealName(),
                clientEntity.getBirthDate(),
                clientEntity.getDni(),
                clientEntity.getCountry(),
                clientEntity.getGender(),
                clientEntity.getDirection(),
                clientEntity.getPhone(),
                UserRole.CLIENT
                );
        return clientResponse;
    }
}
