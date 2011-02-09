package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/events")
public class EventController {
    private static final String RECORD_EVENT_VIEW = "recordEvent";
    private static final String EVENT_SAVE_VIEW = "eventSaved";
    private EventRepository repository;

    @Autowired
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "record")
    public String display() {
        return RECORD_EVENT_VIEW;
    }

    @RequestMapping(value = "save")
    public String save(@ModelAttribute(value = "eventRecord")  EventRecord eventRecord) {
	    if(repository.save(eventRecord))
            return EVENT_SAVE_VIEW;
        else
            return RECORD_EVENT_VIEW;
    }

    @RequestMapping(value = "/{eventId}")
    public String display(@PathVariable int eventId, Map<String, Event> model) {
        model.put("event", repository.getEvent(eventId));
        return "events/show";
    }



}
