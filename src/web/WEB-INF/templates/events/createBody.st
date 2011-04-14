<div id="event">

	<form name="createEventForm" action="save" method="post">

        $events/eventForm()$

		<div class="eventButtons">
			<input type="submit" id="save" value="Save"/>
			<input type="button" id="clear" value="Clear" />
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