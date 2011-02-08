<form id="eventRecord" name="eventRecord" action="save" method="post" >
	<span>Title</span>
	<input type="text" name="title" id="title"/><br />

	<span>Date (dd/mm/yyyy)</span>
	<input type="text" name="date" id="date"/><br />

	<span>Time (hh:mm)</span>
	<input type="text" name="time" id="time"/> <br/>

	<span>Venue</span>
	<input type="text" name="venue" id= "venue"/><br />

	<span>Description</span>
	<textarea name="description" rows="3" cols="20" id="description"></textarea> <br/>

	<span>Coordinator</span>
	<input type="text" name="coordinator" id="coordinator"/><br/>

	<span>Attendees</span>
	<textarea name="attendees" rows="3" cols="20" id="attendees"></textarea><br />

	<span>Notes</span>
	<textarea name="notes" rows="3" cols="20" id="notes"></textarea> <br/>

	<div class="errorMessages" id="errorMessages"></div>
	<input type="button" value="Save"  onclick="validateMandatoryFields()"/>
	<input type="button" value="Clear" onclick="resetFields()"/>
</form>