<div class="eventInfo">

    <div class="eventInfoLeft">
        <div class="createEntry">
            <div class="label">Title<span class="mandatory">*</span></div>
            <input type="text" name="title" id="title" value="$event.title$" class="createValue" />
        </div>

        <div class="createEntry">
            <div class="label dateTime">Date <br/>(dd-mm-yyyy)<span class="mandatory">*</span></div>
            <input type="text" name="date" id="date" value="$event.date$" class="createValue" />
        </div>

        <div class="createEntryDateTime">
            <div class="label dateTime">Start Time <br/>(hh:mm)</div>
            <input type="text" name="startTime" id="startTime" value="$event.startTime$" class="createValue" />
      <div class="label amPm">
                <label for="startTimeAm">a.m.</label>
                <input type="radio" id="startTimeAm" class="amPm" name="startTimeAmPm" value="am" $if (!event.startTimePm)$ checked $endif$ />
                 <label for="startTimePm">p.m.</label>
                <input type="radio" id="startTimePm" class="amPm" name="startTimeAmPm" value="pm" $if (event.startTimePm)$ checked $endif$  />
            </div>
        </div>

        <div class="createEntryDateTime">
            <div class="label dateTime">End Time <br/>(hh:mm)</div>
            <input type="text" name="endTime" id="endTime" value="$event.endTime$" class="createValue" />
            <div class="label amPm">
                <label for="endTimeAm">a.m.</label>
                <input type="radio" id="endTimeAm" class="amPm" name="endTimeAmPm" value="am" $if (!event.endTimePm)$ checked $endif$ />
                 <label for="endTimePm">p.m.</label>
                <input type="radio" id="endTimePm" class="amPm" name="endTimeAmPm" value="pm" $if (event.endTimePm)$ checked $endif$  />
            </div>

        </div>

        <div class="createEntry">
            <div class="label">Venue</div>
            <input type="text" name="venue" id="venue" value="$event.venue$" class="createValue" />
        </div>

        <div class="createEntry">
            <div class="label">Coordinator</div>
            <input type="text" name="coordinator" id="coordinator" value="$event.coordinator$" class="createValue" />
        </div>

        <div class="createEntry">
            <div class="attendeeLabel">Attendees<span class="mandatory">*</span></div>
            <textarea name="attendees" id="attendees" class="createValue" rows="5" cols="38">$if (event.attendees)$$trunc(event.attendees):{$it.studentId$,}$$last(event.attendees).studentId$$endif$</textarea>

        </div>
    </div>

    <div class="eventInfoRight">
        <div class="createEntry">
            <div class="label">Description<span class="mandatory">*</span></div>
            <textarea name="description" id="description" class="createValue" rows="7.5"cols="30">$event.description$</textarea></div>


        <div class="createEntry">
            <div class="label">Notes</div>
            <textarea name="notes" id="notes" class="createValue" rows="5" cols="30">$event.notes$</textarea>
        </div>
    </div>

</div>