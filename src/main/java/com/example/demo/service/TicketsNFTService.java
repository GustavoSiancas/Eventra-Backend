package com.example.demo.service;

import com.example.demo.controller.ticket.Response.TicketNFTResponse;
import com.example.demo.controller.ticket.Response.TicketVResponse;
import com.example.demo.controller.ticket.request.BuyInTicketRequest;
import com.example.demo.entity.TicketsEntity;
import com.example.demo.entity.TicketsNFT;
import com.example.demo.entity.TransactionEntity;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.TicketsNFTRepository; // Asegúrate de tener este repositorio
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TicketsNFTService {

    @Autowired
    private TicketsNFTRepository ticketsNFTRepository;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionRepository transactionRepository;


    public TicketsNFT createTicketsNFT(BuyInTicketRequest buyInTicketRequest) {
        TicketsEntity ticket = ticketService.getTicketById(buyInTicketRequest.idTicket()).orElseThrow(() -> new RuntimeException("Ticket not found"));;
        if (ticket.getActualquantity() >= ticket.getQuantity()) {
            throw new RuntimeException("No tickets available");
        }else {
            ticket.setActualquantity(ticket.getActualquantity() + 1);
            ticketRepository.save(ticket);
            TicketsNFT ticketsNFT = new TicketsNFT();
            ticketsNFT.setTicket(ticket);
            ticketsNFT.setClient(clientService.getClientById(buyInTicketRequest.idClient()).get());
            ticketsNFT=ticketsNFTRepository.save(ticketsNFT);
            TransactionEntity transactionEntity=new TransactionEntity();
            transactionEntity.setTicketNftId(ticketsNFT.getId());
            transactionEntity.setMethodPayment(buyInTicketRequest.methodPayment());
            BigDecimal price = ticketsNFT.getTicket().getPrice().multiply(new BigDecimal("0.15"));
            transactionEntity.setFee(price);
            transactionRepository.save(transactionEntity);
            return ticketsNFT;
        }
    }

    public List<TicketsNFT> getAllTicketsNFTs() {
        return ticketsNFTRepository.findAll();
    }

    public List<TicketsNFT> createMultipleTicketsNFTs(TicketsEntity ticket) {
        if (ticket.getQuantity() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        List<TicketsNFT> ticketsNFTs = new ArrayList<>();

        for (int i = 0; i < ticket.getQuantity(); i++) {
            TicketsNFT ticketsNFT = new TicketsNFT();
            ticketsNFT.setTicket(ticket);
            ticketsNFTs.add(ticketsNFT);
        }

        return ticketsNFTRepository.saveAll(ticketsNFTs);
    }

    public List<TicketNFTResponse> getTicketsNFTsByTicketId(Long ticketId) {
        List<TicketsNFT> ticketsNFTs = ticketsNFTRepository.findByTicket_Id(ticketId);
        return ticketsNFTs.stream()
                .map(ticketNFT -> new TicketNFTResponse(
                        ticketNFT.getUuid(),
                        ticketNFT.getActualOwnerPublicDirection(),
                        ticketNFT.getNft(),
                        ticketNFT.getTicket().getId(), // Asumiendo que el ticket está asociado
                        ticketNFT.getClient() != null ? ticketNFT.getClient().getId() : null // Verifica si el cliente no es null
                ))
                .toList(); // Si usas Java 11 o superior
    }

    public List<TicketVResponse> getTicketsByUserId(Long userId) {
        List<TicketsNFT> ticketsNFTs = ticketsNFTRepository.findByClient_Id(userId);
        ticketsNFTs.sort(Comparator.comparing(ticket->ticket.getTicket().getEvent().getDateTime(),Comparator.reverseOrder()));
        List<TicketVResponse> ticketVResponses = new ArrayList<>();
        for (TicketsNFT ticket : ticketsNFTs) {
            ticketVResponses.add(new TicketVResponse(
                    ticket.getId(),
                    ticket.getTicket().getEvent().getActivity().getPhoto(),
                    ticket.getTicket().getName(),
                    ticket.getTicket().getEvent().getDateTime(),
                    ticket.getNft(),
                    ticket.getTicket().getName()
            ));
        }
        return ticketVResponses;
    }
}
