package org.sukrupa.app.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.event.Event;
import org.sukrupa.event.EventCreateParameter;
import org.sukrupa.event.EventService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/events")
public class EventsController {
    private final EventService service;

    @Autowired
    public EventsController(EventService service) {
        this.service = service;
    }
    @RequestMapping()
    public String list(Map<String, List<Event>> model)
    {
        model.put("events", service.list());
        return "events/list";
    }

    @RequestMapping(value = "/{eventId}")
    public String view(@PathVariable int eventId, Map<String, Event> model) {
        model.put("event", service.getEvent(eventId));
        return "events/view";
    }

    @RequestMapping(value = "/{eventId}/edit", method = GET)
    public String edit(@PathVariable int eventId, Map<String, Event> model) {
        model.put("event",service.getEvent(eventId));
        return "events/edit";
    }


	@RequestMapping(value = "create", method = GET)
	public String create() {
		return "events/create";
	}

	@RequestMapping(value = "save", method = POST)
	public String save(@ModelAttribute(value = "createEvent") EventCreateParameter eventCreateParameter, BindingResult result, Map<String, Object> model) {
		Event event = Event.createFrom(eventCreateParameter);
		Set<String> studentIdsOfAttendees = eventCreateParameter.getStudentIdsOfAttendees();
		Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);
		if (!invalidAttendees.isEmpty()) {
			model.put("invalidAttendees",invalidAttendees);
			model.put("event", eventCreateParameter);
			return "events/create";
		} else {
			service.save(event, studentIdsOfAttendees.toArray(new String[]{}));
			return format("redirect:/events/%s", event.getId());
		}
	}

}
