<div id="event">

	<form id="editEvent" name="editEvent" action="/events/update/$event.id$" method="post">
        <input type="hidden" name="id" id="id" value="$event.id$" class="createValue"></input>
		$events/eventForm()$

		<div class="eventButtons">
			<input type="button" id="save" value="Save"/>
			<input type="button" id="cancel" value="Cancel" />
		</div>
	</form>


	<div id="createEventErrors">
		<div id="errorMessages">
			$if (invalidAttendees)$
			Error: $invalidAttendees$
			$endif$
		</div>
	</div>

</div>