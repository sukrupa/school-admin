<form id="eventCreateParameter" name="eventCreateParameter" action="save" method="post">

    <span>Title<span class="mandatory">* </span> </span>
    <input type="text" name="title" id="title" value="$eventtitle$"/>

    <span>Date (dd/mm/yyyy)<span class="mandatory">* </span></span>
    <input size= 12 type="text" name="date" id="date" value="$date$"/>

    <span>Time (hh:mm)</span>
    <input size=7 type="text" name="time" id="time" value="$time$"/> <br/>

    <br/>
    <span>Venue</span>
    <input type="text" name="venue" id="venue" value="$venue$"/><br/>

    <br/>
    <span>Description<span class="mandatory">* </span></span>
    <textarea name="description" rows="3" cols="20" id="description">$description$</textarea> <br/>

    <br/>
    <span>Coordinator</span>
    <input type="text" name="coordinator" id="coordinator" value="$coordinator$"/><br/>

    <br/>
    <span>Attendees<span class="mandatory">* </span></span>
    <textarea name="attendees" rows="3" cols="20" id="attendees">$attendees$</textarea><br/>

    <br/>
    <span>Notes</span>
    <textarea name="notes" rows="3" cols="20" id="notes">$notes$</textarea> <br/>
    <br/>

    <input type="submit" value="Save"/>
    <input type="button" value="Clear" onclick="resetFields()"/>

    <br/><br/>

    $errors:{
        <div class="errorMessage">$it.defaultMessage$</div>
    }$

</form>
