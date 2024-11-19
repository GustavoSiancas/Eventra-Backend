package com.example.demo.controller.event;

import com.example.demo.controller.event.request.EventCompleteRequest;
import com.example.demo.controller.event.request.EventRequest;
import com.example.demo.controller.event.response.EventTagResponse;
import com.example.demo.controller.event.response.EventTypeResponse;
import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/events") // Base path para eventos
public class EventController {

    /*
    @Autowired
    private EventService eventService;

    // Endpoint para crear un nuevo evento
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventRequest eventRequest) {
        Event createdEvent = eventService.createEvent(eventRequest);
        return ResponseEntity.ok(createdEvent);
    }

    @PostMapping("/complete")
    public ResponseEntity<Event> createCompleteEvent(@RequestBody EventCompleteRequest event) {
        Event createdEvent = eventService.createCompleteEvent(event);
        return ResponseEntity.ok(createdEvent);
    }

    // Endpoint para obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    // Endpoint para obtener un evento por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<EventTagResponse> getEventTagById(@PathVariable Long id) {
        EventTagResponse eventTagResponse = eventService.getEventTagById(id);

        if (eventTagResponse != null) {
            return ResponseEntity.ok(eventTagResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEventType(@PathVariable Long id, @RequestBody EventTypeResponse eventTypeResponse) {
        try {
            Event updatedEvent = eventService.updateEventType(id, eventTypeResponse);
            return ResponseEntity.ok(updatedEvent);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
     */
}
