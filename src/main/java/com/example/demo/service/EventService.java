package com.example.demo.service;


import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository; // Asegúrate de tener el repositorio
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {


    @Autowired
    private EventRepository eventRepository;



    /*
    @Autowired
    private BusinessService businessService; // Para acceder a los negocios
    */



    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEventsByActivityId(Long activityId) {
        return eventRepository.findByActivity_Id(activityId);

    }

    public List<LocalDateTime> getDateAllEventsByActivityId(Long activityId) {
        List<Event> events= eventRepository.findByActivity_Id(activityId);
        List<LocalDateTime> eventList= new ArrayList<>();
        for(Event event: events) {
            eventList.add(event.getDateTime());
        }
        return eventList;
    }

    public Long getEventId(Long activityId) {
        List<Event> events= eventRepository.findByActivity_Id(activityId);
        return events.getFirst().getId();
    }

    public Event getEvent(Long activityId) {
        List<Event> events= eventRepository.findByActivity_Id(activityId);
        return events.getFirst();
    }

    /*
    public Event createCompleteEvent(EventCompleteRequest eventCompleteRequest) {
        // Crear y guardar el evento primero
        Event event = new Event();
        event.setEventName(eventCompleteRequest.eventName());
        event.setEventDescription(eventCompleteRequest.eventDescription());
        event.setEventLocation(eventCompleteRequest.eventLocation());
        event.setPhoto(eventCompleteRequest.photo());
        event.setDateTime(eventCompleteRequest.date());

        // Obtener el negocio usando el ID
        var business = businessService.getBusinessById(eventCompleteRequest.businessId());
        business.ifPresent(event::setBusiness);

        // Guardar el evento
        event = eventRepository.save(event);

        tagService.guardarMuchos(eventCompleteRequest.tags(),event);

        return event; // Retorna el evento guardado
    }

    public EventTagResponse getEventTagById(Long id) {
        Event event = getEventById(id).get();

        List<TagEntity> listaTags= tagService.getAllTagsByEventId(id);

        List<TagRequest> tagRequestList= new ArrayList<>();
        for (TagEntity tagEntity : listaTags) {
            TagRequest tagRequest=new TagRequest(tagEntity.getNameTag());
            tagRequestList.add(tagRequest);
        }

        EventTagResponse eventTagResponse = new EventTagResponse(event.getId(), event.getEventName(), event.getEventDescription(), event.getEventLocation(),event.getPhoto(),event.getDateTime(),event.getEventType(),tagRequestList,event.getBusiness().getId());
        return eventTagResponse;
    }


    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event updateEventType(Long id, EventTypeResponse eventTypeResponse) {
        // Buscar el evento por ID
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            // Aquí puedes actualizar el tipo de evento
            event.setEventType(eventTypeResponse.activityType()); // Asegúrate de que `setEventType` exista en tu clase `Event`
            return eventRepository.save(event); // Guardar el evento actualizado
        } else {
            throw new IllegalArgumentException("Event not found with ID: " + id);
        }
    }

     */


}
