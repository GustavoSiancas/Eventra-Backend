package com.example.demo.controller.ticket.Response;

import java.time.LocalDateTime;

public record TicketVResponse (
        Long id,
        String photo,
        String title,
        LocalDateTime dateTime,
        String publicHash,
        String description
){
}
