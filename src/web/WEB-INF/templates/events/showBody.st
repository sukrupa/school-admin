<div>
    <div class="title">$event.title$</div>

    <div class="eventEntry">
        <div class="eventLabel">Date</div>
        <div class="date">$event.date$</div>
    </div>

    <div class="eventEntry">
        <div class="eventLabel">Day</div>
        <div class="day">$event.day$</div>
    </div>

    <div class="eventEntry">
        <div class="eventLabel">Time</div>
        <div class="time">$event.time$</div>
    </div>

    <div class="eventEntry">
        <div class="eventLabel">Venue</div>
        <div class="venue">$event.venue$</div>
    </div>

    <div class="eventEntry spacer">
        <div class="description">$event.description$</div>
    </div>

    <div class="eventEntry spacer">
        <div class="notes">$event.notes$</div>
    </div>

    <div class="eventEntry">
        <div class="eventLabel">Attendees</div>
        <ul>
            $event.attendees:{
            <li class="attendee">$it.name$</li>
            }$
        </ul>
    </div>
</div>

