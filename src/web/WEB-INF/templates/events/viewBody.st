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

    $event.time:{ time |
    <div class="eventEntry">
        <div class="eventLabel">Time</div>
        <div class="time">$time$</div>
    </div>
    }$

    $event.venue:{ venue |
    <div class="eventEntry">
        <div class="eventLabel">Venue</div>
        <div class="venue">$venue$</div>
    </div>
    }$

    <div class="eventEntry">
        <div class="eventDescription">$event.description$</div>
    </div>

    $event.notes:{ notes |
    <div class="eventEntry">
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
            <form action="/events/$event.id$/edit" method="GET">
                <input type="submit" value="Edit"/>
            </form>
        </div>
</div>

