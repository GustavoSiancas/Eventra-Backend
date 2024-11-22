package com.example.demo.controller.ticket.Response;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketVResponse (
        Long id,
        String photo,
        String title,
        LocalDateTime dateTime,
        UUID publicHash,
        String description
){
}
