package com.example.demo.service;

import com.example.demo.controller.data.response.GeneralDataResponse;
import com.example.demo.entity.Event;
import com.example.demo.entity.TicketsEntity;
import com.example.demo.entity.TicketsNFT;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.ExtraDataRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.TicketsNFTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class ExtraDataService {
    @Autowired
    private ExtraDataRepository extraDataRepository;
    @Autowired
    private TicketsNFTRepository ticketsNFTRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private EventRepository eventRepository;

    public GeneralDataResponse getAllExtraDatabyId(Long id){
        List<TicketsEntity> ticketsEntities=ticketRepository.findByEvent_Id(id);
        Integer aforum=0;
        Integer actual=0;
        Integer interator=0;
        Float averageAge=0.0f;
        Integer male=0;
        Integer female=0;
        Integer edad=0;
        for (TicketsEntity ticketsEntity : ticketsEntities) {
            List<TicketsNFT> ticketsNFTS=ticketsNFTRepository.findByTicket_Id(ticketsEntity.getId());


            for (TicketsNFT ticketsNFT : ticketsNFTS) {
                if (ticketsNFT.getClient() != null) {
                    // Validar género
                    System.out.println(ticketsNFT.getClient().getGender());
                    if ("Femenino".equals(ticketsNFT.getClient().getGender())) {
                        female++;
                    } else {
                        male++;
                    }

                    // Calcular edad si la fecha de nacimiento no es nula
                    if (ticketsNFT.getClient().getBirthDate() != null) {
                        Integer age = Period.between(ticketsNFT.getClient().getBirthDate(), LocalDate.now()).getYears();
                        System.out.println(age);
                        edad += age;
                        interator++;
                    }
                }
                actual++;
            }
            aforum+=ticketsEntity.getQuantity();
        }
        if (interator!=0){
            averageAge= (float) (edad/interator);
        } else {
            averageAge=0.0f;
        }



        return new GeneralDataResponse(
                eventRepository.findById(id).get().getInteraccion(),
                aforum,
                actual,
                male,
                female,
                averageAge
        );
    }

    public Integer addInter(Long eventId){
        List<Event> events=eventRepository.findByActivity_Id(eventId);
        for (Event event : events) {
            event.setInteraccion(event.getInteraccion()+1);
            eventRepository.save(event);
        }
        return 1;
    }
}
