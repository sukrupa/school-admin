<h1 class="title"">$event.title$</h1>

<div class="entry">
    <label>Date</label>
    <span class="date">$event.date$</span>
</div>

<div class="entry">
    <label>Day</label>
    <span class="day">$event.day$</span>
</div>

<div class="entry">
    <label>Time</label>
    <span class="time">$event.time$</span>
</div>

<div class="entry">
    <label>Venue</label>
    <span class="venue">$event.venue$</span>
</div>

<div class="entry spacer">
    <div class="description">$event.description$</div>
</div>

<div class="entry spacer">
    <div class="notes">$event.notes$</div>
</div>

<div class="entry">
    <label>Attendees</label>
    <ul>
    $event.attendees:{
        <li class="attendee">$it.name$</li>
    }$
    </ul>
</div>

