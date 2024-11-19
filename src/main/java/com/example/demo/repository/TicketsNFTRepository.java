package com.example.demo.repository;

import com.example.demo.entity.TicketsNFT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketsNFTRepository extends JpaRepository<TicketsNFT, Long> {
    List<TicketsNFT> findByTicket_Id(Long ticketId);
    List<TicketsNFT> findByClient_Id(Long clientId);
}
