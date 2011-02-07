package org.sukrupa.event;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/events")
public class EventController {
    private static final String EVENTS_MODEL = "events";
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
	    repository.save(eventRecord);
        return EVENT_SAVE_VIEW;
    }

    @RequestMapping(value = "checkStudentId")
    public String save(@RequestParam(value = "sendValue") String sendValue, EventRecord eventRecord) {
	    System.out.println("test");
	    return EVENT_SAVE_VIEW;
    }

}
