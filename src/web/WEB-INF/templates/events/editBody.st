<div id="event">

	<form id="editEvent" name="editEvent" action="/events/$event.id$" method="post">
        <input type="hidden" name="id" id="id" value="$event.id$" class="createValue"></input>
		$events/eventForm()$

		<div class="eventButtons">
			<input type="submit" id="save" value="Save"/>
			<input type="button" id="cancel" value="Cancel" />
		</div>
	</form>


	<div id="createEventErrors">
		<div id="errorMessages">
			$if (invalidAttendees)$
			Invalid student IDs: $invalidAttendees; separator=", "$
			$endif$
		</div>
	</div>

</div>