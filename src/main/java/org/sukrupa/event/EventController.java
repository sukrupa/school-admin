package org.sukrupa.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventController {
    private static final String EVENTS_MODEL = "events";
    private static final String EVENTS_VIEW = "events";
    private static final String EVENTS_SAVE_VIEW = "events_saved";
    private EventRepository repository;

    @Autowired
    public EventController(EventRepository repository) {
        this.repository = repository;
    }

    @RequestMapping()
    public String display() {
        return EVENTS_VIEW;
    }

    @RequestMapping(value = "save")
    public String save() {
        return EVENTS_SAVE_VIEW;
    }

}
