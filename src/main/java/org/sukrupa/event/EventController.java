package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/events")
public class EventController {
    private final EventService service;

    @Autowired
    public EventController(EventService service) {
        this.service = service;
    }

    @RequestMapping(value = "create")
    public String create() {
        return "events/create";
    }

    @RequestMapping(value = "save", method = POST)
    public String save(@ModelAttribute(value = "eventCreateParameter") EventCreateParameter eventCreateParameter, BindingResult result, Map<String, Object> model) {
        Event event = Event.from(eventCreateParameter);
        service.save(event, eventCreateParameter.getStudentIdsOfAttendees());
        return format("redirect:/events/%s", event.getId());
    }

    @RequestMapping(value = "/{eventId}")
    public String display(@PathVariable int eventId, Map<String, Event> model) {
        model.put("event", service.getEvent(eventId));
        return "events/show";
    }
}
