package org.sukrupa.student;

import com.natpryce.makeiteasy.Instantiator;
import com.natpryce.makeiteasy.Property;
import static com.natpryce.makeiteasy.Property.newProperty;
import com.natpryce.makeiteasy.PropertyLookup;
import org.sukrupa.event.Event;
import org.sukrupa.platform.date.Date;

public class Builders {
    public static final Property<Event, String> title = newProperty();
    public static final Property<Event, Date> date = newProperty();
    public static final Property<Event, String> venue = newProperty();
    public static final Property<Event, String> coordinator = newProperty();
    public static final Property<Event, String> description = newProperty();
    public static final Property<Event, String> notes = newProperty();

    
    public static final Instantiator<Event> Event = new Instantiator<Event>() {
        @Override
        public Event instantiate(PropertyLookup<Event> lookup) {
            return new Event(lookup.valueOf(title, "dummy.notes"),
                             lookup.valueOf(date, Date.now()),
                             lookup.valueOf(venue, "dummy.venue"),
                             lookup.valueOf(coordinator, "dummy.coordinator"),
                             lookup.valueOf(description, "dummy.description"),
                             lookup.valueOf(notes, "dummy.notes"));
        }
    };


}
