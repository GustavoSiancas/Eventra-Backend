package com.example.demo.controller.ticket;

import com.example.demo.controller.ticket.Response.TicketNFTResponse;
import com.example.demo.controller.ticket.Response.TicketVResponse;
import com.example.demo.controller.ticket.request.BuyInTicketRequest;
import com.example.demo.controller.ticket.request.TicketRequest;
import com.example.demo.entity.TicketsEntity;
import com.example.demo.entity.TicketsNFT;
import com.example.demo.service.TicketService;
import com.example.demo.service.TicketsNFTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketsNFTService ticketsNFTService;


    @PostMapping("/buy")
    public ResponseEntity<TicketsNFT> createBuyInTicket(@RequestBody BuyInTicketRequest ticketRequest) {
        TicketsNFT createdTicket = ticketsNFTService.createTicketsNFT(ticketRequest);
        return ResponseEntity.ok(createdTicket);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TicketVResponse>> getTicketsByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketsNFTService.getTicketsByUserId(id));
    }
/*
    @GetMapping
    public ResponseEntity<List<TicketsEntity>> getAllTickets() {
        List<TicketsEntity> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<TicketsEntity>> getTicketsByEventId(@PathVariable Long eventId) {
        List<TicketsEntity> tickets = ticketService.getTicketsByEventId(eventId);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/nft/{ticketId}")
    public ResponseEntity<List<TicketNFTResponse>> getTicketsNFTsByTicketId(@PathVariable Long ticketId) {
        List<TicketNFTResponse> ticketNFTResponses = ticketsNFTService.getTicketsNFTsByTicketId(ticketId);
        return ResponseEntity.ok(ticketNFTResponses);
    }*/
}