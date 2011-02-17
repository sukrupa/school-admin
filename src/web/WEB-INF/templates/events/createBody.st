<div id="event">

	<form id="createEvent" name="createEvent" action="save" method="post">

		<div class="eventInfo">

			<div class="eventInfoLeft">
				<div class="createEntry">
					<div class="label">Title<span class="mandatory">*</span></div>
					<input type="text" name="title" id="title" value="$eventtitle$" class="createValue"></input>
				</div>

				<div class="createEntryDateTime">
					<div class="label dateTime">Date <br/>(dd-mm-yyyy)<span class="mandatory">*</span></div>
					<input type="text" name="date" id="date" value="$date$" class="createValue"></input>
				</div>

				<div class="createEntryDateTime">
					<div class="label dateTime">Time <br/>(hh:mm)</div>
					<input type="text" name="time" id="time" value="$time$" class="createValue"></input>
				</div>

				<div class="createEntry">
					<div class="label">Venue</div>
					<input type="text" name="venue" id="venue" value="$venue$" class="createValue"></input>
				</div>

				<div class="createEntry">
					<div class="label">Coordinator</div>
					<input type="text" name="coordinator" id="coordinator" value="$coordinator$"
						   class="createValue"></input>
				</div>

				<div class="createEntry">
					<div class="attendeeLabel">Attendees<span class="mandatory">*</span></div>
					<textarea name="attendees" id="attendees" class="createValue" rows="5" cols="38">$attendees;
						separator=", "$</textarea>

				</div>
			</div>

			<div class="eventInfoRight">
				<div class="createEntry">
					<div class="label">Description<span class="mandatory">*</span></div>
					<textarea name="description" id="description" class="createValue" rows="7.5"
							  cols="30">$description$</textarea>
				</div>


				<div class="createEntry">
					<div class="label">Notes</div>
					<textarea name="notes" id="notes" class="createValue" rows="5" cols="30">$notes$</textarea>
				</div>
			</div>

		</div>

		<div class="eventButtons">
			<input type="submit" id="save" value="Save"/>
			<input type="button" id="clear" value="Clear"/>
		</div>
	</form>


	<div id="createEventErrors">
		<div id="errorMessages" class="errorMessages"></div>
		<div class="errorMessages">
			$if (invalidAttendees)$
			Invalid student ids: $invalidAttendees; separator=", "$
			$endif$
		</div>
	</div>

</div>