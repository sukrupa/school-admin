package org.sukrupa.app.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.sukrupa.event.Event;
import org.sukrupa.event.EventForm;
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

    @RequestMapping(value = "{eventId}", method = POST)
    public String update(@PathVariable String eventId,
            @ModelAttribute("editEvent") EventForm eventForm,
            Map<String, Object> model)
    {
        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");

        if (eventForm.isInvalid(errors)){
            model.put("errors", errors);
            model.put("event", eventForm);
            addErrorToFields(model,errors);
            return "events/edit";
        }

        Set<String> studentIdsOfAttendees =   eventForm.getStudentIdsOfAttendees();
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);

        if (!invalidAttendees.isEmpty()) {
            model.put("event", eventForm);
            model.put("invalidAttendees",invalidAttendees);
            return "events/edit";

        } else {
            service.update(eventForm);
            return format("redirect:/events/%s", eventId);
        }
    }

	@RequestMapping(value = "save", method = POST)
	public String save(@ModelAttribute(value = "createEventForm") EventForm eventForm, Map<String, Object> model) {

        Errors errors = new BeanPropertyBindingResult(eventForm, "EventForm");

        if (eventForm.isInvalid(errors)){
            model.put("errors", errors);
            model.put("event", eventForm);
            addErrorToFields(model,errors);
            return "events/create";
        }

        Event event = eventForm.createEvent();

        Set<String> studentIdsOfAttendees = eventForm.getStudentIdsOfAttendees();
        Set<String> invalidAttendees = service.validateStudentIdsOfAttendees(studentIdsOfAttendees);

		if (!invalidAttendees.isEmpty()) {
			model.put("invalidAttendees",invalidAttendees);
			model.put("event", eventForm);
			return "events/create";
		} else {
			service.save(event, studentIdsOfAttendees.toArray(new String[]{}));
			return format("redirect:/events/%s", event.getId());
		}
	}

    private void addErrorToFields(Map<String, Object> model, Errors errors) {
          for (FieldError error : errors.getFieldErrors()) {
              model.put(format("%sError", error.getField()), new UnencodedString(error.getDefaultMessage()));
          }

      }

    private class UnencodedString {
        private String value;

        public UnencodedString(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }
}
