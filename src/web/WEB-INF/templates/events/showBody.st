<div class="title">$event.title$</div>

<div class="entry">
<div class="label">Date</div>
<div class="date">$event.date$</div>
</div>

<div class="entry">
<div class="label">Day</div>
<div class="day">$event.day$</div>
</div>

<div class="entry">
<div class="label">Time</div>
<div class="time">$event.time$</div>
</div>

<div class="entry">
<div class="label">Venue</div>
<div class="venue">$event.venue$</div>
</div>

<div class="entry spacer">
<div class="description">$event.description$</div>
</div>

<div class="entry spacer">
<div class="notes">$event.notes$</div>
</div>

<div class="entry">
<div class="label">Attendees</div>
<ul>
    $event.attendees:{
    <li class="attendee">$it.name$</li>
    }$
</ul>
</div>

