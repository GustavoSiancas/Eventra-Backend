package com.example.demo.service;

import com.example.demo.controller.ticket.Response.TicketNFTResponse;
import com.example.demo.controller.ticket.Response.TypeTIcketResponse;
import com.example.demo.controller.ticket.request.TickeSimpleRequest;
import com.example.demo.controller.ticket.request.TicketRequest;
import com.example.demo.entity.TicketsEntity;
import com.example.demo.entity.TicketsNFT;
import com.example.demo.repository.TicketRepository; // Asegúrate de tener este repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;




    /*public TicketsEntity createTicket(TicketRequest ticketRequest) {
        TicketsEntity ticket = new TicketsEntity();
        ticket.setName(ticketRequest.name());
        ticket.setColor(ticketRequest.color());
        ticket.setPrice(ticketRequest.price());
        ticket.setQuantity(ticketRequest.quantity());
        ticket.setEvent(eventService.getEventById(ticketRequest.event_id()).get());
        ticket=ticketRepository.save(ticket);

        ticketsNFTService.createMultipleTicketsNFTs(ticket);

        return ticket;
    }
    */


    public TicketsEntity createTicket(TicketsEntity ticket) {
        TicketsEntity ticketsEntity=ticketRepository.save(ticket);
        //ticketsNFTService.createMultipleTicketsNFTs(ticket);
        return ticketsEntity;
    }

    public List<TickeSimpleRequest> getTicketsByEventId(Long eventId) {
        List<TicketsEntity> tickets=ticketRepository.findByEvent_Id(eventId);
        List<TickeSimpleRequest> ticketList= new ArrayList<>();
        for (TicketsEntity ticketEntity : tickets) {
            ticketList.add(new TickeSimpleRequest(ticketEntity.getName(),ticketEntity.getColor(),ticketEntity.getQuantity(),ticketEntity.getPrice()));
        }
        return ticketList;
    }

    public BigDecimal getLowestPrice(Long eventId) {
        List<TicketsEntity> tickets=ticketRepository.findByEvent_Id(eventId);
        BigDecimal lowestPrice=tickets.get(0).getPrice();
        for (TicketsEntity ticketEntity : tickets) {
            if (ticketEntity.getPrice().compareTo(lowestPrice) < 0) {
                lowestPrice=ticketEntity.getPrice();
            }
        }
        return lowestPrice;
    }

    public Optional<TicketsEntity> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public List<TypeTIcketResponse> getAllTickets(Long eventId) {
        List<TicketsEntity> tickets=ticketRepository.findByEvent_Id(eventId);
        List<TypeTIcketResponse> ticketList= new ArrayList<>();
        for (TicketsEntity ticketEntity : tickets) {
            ticketList.add(new TypeTIcketResponse(
               ticketEntity.getId(),
               ticketEntity.getName(),
               ticketEntity.getPrice()
            ));
        }
        return ticketList;
    }

    /*
    public List<TicketsEntity> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<TicketsEntity> getTicketsByEventId(Long eventId) {
        return ticketRepository.findByEvent_Id(eventId); // Llama al método del repositorio
    }

    //ARREGLAR ESTO PLS
    public List<TicketNFTResponse> getTicketsNFTsByTicketId(Long ticketId) {
        return ticketsNFTService.getTicketsNFTsByTicketId(ticketId);
    }

     */
}
