package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/events")
public class EventController {
    private static final String RECORD_EVENT_VIEW = "recordEvent";
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @RequestMapping(value = "new")
    public String display(EventRecord eventRecord, Map<String, String> model) {
        model.put("eventtitle", eventRecord.getTitle());
        model.put("date", eventRecord.getDate());
        model.put("description", eventRecord.getDescription());
        model.put("time", eventRecord.getTime());
        model.put("coordinator", eventRecord.getCoordinator());
        model.put("notes", eventRecord.getNotes());
        model.put("attendees", eventRecord.getAttendees());
        model.put("venue", eventRecord.getVenue());
        model.put("errorMessage", eventRecord.getError());
        return RECORD_EVENT_VIEW;
    }

    @RequestMapping(value = "save", method = POST)
    public String save(@ModelAttribute(value = "eventRecord") EventRecord eventRecord,Map<String, String> model) {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(eventRecord,"Errors");
        new EventFormValidator().validate(eventRecord, result);

        if(result.hasErrors()){
            model.put("eventtitle", eventRecord.getTitle());
            model.put("date", eventRecord.getDate());
            model.put("description", eventRecord.getDescription());
            model.put("time", eventRecord.getTime());
            model.put("coordinator", eventRecord.getCoordinator());
            model.put("notes", eventRecord.getNotes());
            model.put("attendees", eventRecord.getAttendees());
            model.put("venue", eventRecord.getVenue());
            model.put("errorMessage", result.getFieldErrors().toString());
            return RECORD_EVENT_VIEW;
        }else{
                Event event = Event.from(eventRecord);
                service.save(event, eventRecord.getStudentIdsOfAttendees());
                return format("redirect:/events/%s", event.getId());
        }
    }

    @RequestMapping(value = "/{eventId}")
    public String display(@PathVariable int eventId, Map<String, Event> model) {
        model.put("event", service.getEvent(eventId));
        return "events/show";
    }
}
