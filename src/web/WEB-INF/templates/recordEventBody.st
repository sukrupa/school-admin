<form id="eventRecord" name="eventRecord" action="save" method="post" >

	<span>Title</span>
	<input type="text" name="title" id="title"/>

	<span>Date (dd/mm/yyyy)</span>
	<input size = 12 type="text" name="date" id="date"/>

	<span>Time (hh:mm)</span>
	<input size=7  type="text" name="time" id="time"/> <br/>

	<br/>
	<span>Venue</span>
	<input type="text" name="venue" id= "venue"/><br />

    <br/>
	<span>Description</span>
	<textarea name="description" rows="3" cols="20" id="description"></textarea> <br/>

    <br/>
	<span>Coordinator</span>
	<input type="text" name="coordinator" id="coordinator"/><br/>

    <br/>
	<span>Attendees</span>
	<textarea name="attendees" rows="3" cols="20" id="attendees"></textarea><br />

    <br/>
	<span>Notes</span>
	<textarea name="notes" rows="3" cols="20" id="notes"></textarea> <br/>

    <br/><br/>
	<input type="button" value="Save"  onclick="validateFields()"/>
	<input type="button" value="Clear" onclick="resetFields()"/>

	<br/><br/>

	<div class="errorMessages" id="errorMessages"></div>



</form>