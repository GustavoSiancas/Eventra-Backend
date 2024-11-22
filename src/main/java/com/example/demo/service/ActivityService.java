package com.example.demo.service;

import com.example.demo.controller.activity.request.ActivityFullRequest;
import com.example.demo.controller.activity.request.ActivityTypeUpdate;
import com.example.demo.controller.activity.request.FilterCardsRequest;
import com.example.demo.controller.activity.response.ActivityCard;
import com.example.demo.controller.ticket.request.TickeSimpleRequest;
import com.example.demo.entity.ActivityEntity;
import com.example.demo.entity.Event;
import com.example.demo.entity.TicketsEntity;
import com.example.demo.entity.enums.ActivityType;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private EventService eventService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TagService tagService;
    @Autowired
    private EventRepository eventRepository;


    public ActivityEntity createActivity(ActivityFullRequest activity) {
        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setName(activity.name());
        activityEntity.setDescription(activity.description());
        activityEntity.setPhoto(activity.photo());
        activityEntity.setActivityLocation(activity.location());
        activityEntity.setBusiness(businessService.getBusinessById(activity.businessId()).get());
        activityEntity=activityRepository.save(activityEntity);
        //creacion de tags
        tagService.guardarMuchos(activity.tags(),activityEntity);
        //ahora se crean los eventos
        for (LocalDateTime fecha: activity.fechas_eventos()){
            Event event=new Event();
            event.setDateTime(fecha);
            event.setActivity(activityEntity);
            eventService.createEvent(event);
            for (TickeSimpleRequest ticket: activity.tickets()){
                TicketsEntity ticketEntity=new TicketsEntity();
                ticketEntity.setName(ticket.getName());
                ticketEntity.setColor(ticket.getColor());
                ticketEntity.setPrice(ticket.getPrice());
                ticketEntity.setQuantity(ticket.getQuantity());
                ticketEntity.setEvent(event);
                ticketService.createTicket(ticketEntity);
            }
        }
        return activityEntity;
    }

    public List<ActivityFullRequest> getAllActivities(){
        List<ActivityEntity> activities=activityRepository.findAll();
        List<ActivityFullRequest> activityFullRequests=new ArrayList<>();
        for (ActivityEntity activity: activities){
            List<TickeSimpleRequest> tickeSimpleRequests=ticketService.getTicketsByEventId(eventService.getEventId(activity.getId()));
            List<LocalDateTime> fechas= eventService.getDateAllEventsByActivityId(activity.getId());
            List<String> tags= tagService.getAllTagsByEventId(activity.getId());
            activityFullRequests.add(new ActivityFullRequest(
                    activity.getId(),
                    activity.getName(),
                    activity.getDescription(),
                    activity.getPhoto(),
                    activity.getActivityLocation(),
                    tags,
                    fechas,
                    tickeSimpleRequests,
                    activity.getBusiness().getId()
            ));
        }
        return activityFullRequests;
    }

    public ActivityFullRequest getActivityById(Long id){
        ActivityEntity activity= activityRepository.findById(id).get();
        List<TickeSimpleRequest> tickeSimpleRequests=ticketService.getTicketsByEventId(eventService.getEventId(activity.getId()));
        List<LocalDateTime> fechas= eventService.getDateAllEventsByActivityId(activity.getId());
        List<String> tags= tagService.getAllTagsByEventId(activity.getId());
        ActivityFullRequest actividad =new ActivityFullRequest(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getPhoto(),
                activity.getActivityLocation(),
                tags,
                fechas,
                tickeSimpleRequests,
                activity.getBusiness().getId()
        );
        return actividad;
    }

    public List<ActivityCard> getAllCardActivities(ActivityType activityType){
        List<ActivityEntity> lists = activityRepository.findByActivityType(activityType);
        List<ActivityCard> activityCards=new ArrayList<>();
        for (ActivityEntity activity: lists){
            Long EventId=eventService.getEventId(activity.getId());
            Event evento=eventService.getEvent(activity.getId());
            List<String> tags= tagService.getAllTagsByEventId(activity.getId());
            BigDecimal price=ticketService.getLowestPrice(EventId);
            activityCards.add(new ActivityCard(
                    activity.getId(),
                    activity.getPhoto(),
                    evento.getDateTime(),
                    activity.getName(),
                    tags,
                    price,
                    activity.getActivityType()

            ));
        }
        return activityCards;
    }


    public List<ActivityCard> getAllCardAFilter(FilterCardsRequest filterCardsRequest){
        List<ActivityEntity> lists = activityRepository.findAll();
        List<ActivityCard> activityCards=new ArrayList<>();
        for (ActivityEntity activity: lists){
            List<Event> events=eventRepository.findByActivity_Id(activity.getId());
            if (events.get(0).getDateTime().toLocalDate().isAfter(filterCardsRequest.startDate())&&events.get(0).getDateTime().toLocalDate().isBefore(filterCardsRequest.endDate())){
                Long EventId=eventService.getEventId(activity.getId());
                Event evento=eventService.getEvent(activity.getId());
                List<String> tags= tagService.getAllTagsByEventId(activity.getId());
                BigDecimal price=ticketService.getLowestPrice(EventId);
                activityCards.add(new ActivityCard(
                        activity.getId(),
                        activity.getPhoto(),
                        evento.getDateTime(),
                        activity.getName(),
                        tags,
                        price,
                        activity.getActivityType()

                ));
            }
        }
        return activityCards;
    }

    public ActivityTypeUpdate updateType(ActivityTypeUpdate activityTypeUpdate){
        List<ActivityEntity> lists = new ArrayList<>();
        for (Long id: activityTypeUpdate.ids()){
            lists.add(activityRepository.findById(id).get());
        }

        for (ActivityEntity activity: lists){
            activity.setActivityType(activityTypeUpdate.activityType());
            activityRepository.save(activity);
        }
        return activityTypeUpdate;
    }

    /*public List<ActivityCard> getAllCardActivities(ActivityType activityType){
        List<ActivityEntity> activities=activityRepository.findAll();
        List<ActivityCard> activityCards=new ArrayList<>();
        List<ActivityEntity> filteredActivities = activities.stream()
                .filter(activity -> activity.getActivityType().equals(activityType))
                .collect(Collectors.toList());

        for (ActivityEntity activity: filteredActivities){

            activityCards.add(ActivityCard(activity.getId(),activity.getPhoto(),activity.getName()));
        }
    }*/
}
