<div>
    <div class="eventEntry">
        <div class="eventLabel">Title</div>
        <div class="title">$event.title$</div>
    </div>


    <div class="eventEntry">
        <div class="eventLabel">Date</div>
        <div class="date">$event.date$</div>
    </div>

    $if(event.day)$
    <div class="eventEntry">
        <div class="eventLabel">Day</div>
        <div class="day">$event.day$</div>
    </div>
    $endif$

    $event.startTimeWithAmPm:{ time |
    <div class="eventEntry">
        <div class="eventLabel">Start Time</div>
        <div class="startTime">$time$</div>
    </div>
    }$

    $event.endTimeWithAmPm:{ time |
    <div class="eventEntry">
        <div class="eventLabel">End Time</div>
        <div class="endTime">$time$</div>
    </div>
    }$

    $event.venue:{ venue |
    <div class="eventEntry">
        <div class="eventLabel">Venue</div>
        <div class="venue">$venue$</div>
    </div>
    }$

    $event.coordinator:{ coordinator |
    <div class="eventEntry">
        <div class="eventLabel">Coordinator</div>
        <div class="coordinator">$coordinator$</div>
    </div>
    }$

    <div class="eventEntry">
        <div class="eventLabel">Description</div>
        <div id="eventDescription" class="eventDescription">$event.description$</div>
    </div>

    $event.notes:{ notes |
    <div class="eventEntry">
        <div class="eventLabel">Notes</div>
        <div class="eventNotes">$notes$</div>
    </div>
    }$

    <div class="eventEntry">
        <div class="eventLabel">Attendees</div>
        <div class="attendees">
            $event.attendeesForDisplay$
        </div>
    </div>
        <div id="update">
            <form action="/events/edit/$event.id$" method="GET">
                <input type="submit" value="Edit"/>
            </form>
        </div>
</div>

