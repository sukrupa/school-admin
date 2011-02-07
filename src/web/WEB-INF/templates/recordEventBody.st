<form name="eventRecord" action="save" method="post">
	<h1>Record an Event</h1>
	<span>Title</span>
	<input type="text" name="title"/>

	<span>Date</span>
	<input type="text" name="date"/>

	<span>Time</span>
	<input type="text" name="time"/> <br/>

	<span>Venue</span>
	<input type="text" name="venue"/>

	<span>Description</span>
	<textarea name="description" rows="3" cols="20"></textarea> <br/>

	<span>Coordinator</span>
	<input type="text" name="coordinator"/><br/>

	<span>Attendees</span>
	<textarea name="attendees" rows="3" cols="20"></textarea> <br/>

	<span>Notes</span>
	<textarea name="notes" rows="3" cols="20"></textarea> <br/>

	<input type="submit" value="Save"/>
	<input type="button" value="Cancel"/>
</form>