<div id="event">

    <form id="createEvent" name="createEvent" action="save" method="post">

        <div id="eventInfo">

            <div class="createEntry">
                <div class="label">Title<span class="mandatory">*</span></div>
                <input type="text" name="title" id="title" value="$eventtitle$" class="createValue"></input>
            </div>

            <div class="createEntry">
                <div class="label">Date (dd-mm-yyyy)<span class="mandatory">*</span></div>
                <input type="text" name="date" id="date" value="$date$" class="createValue"></input>
            </div>

            <div class="createEntry">
                <div class="label">Time (hh:mm)</div>
                <input type="text" name="time" id="time" value="$time$" class="createValue"></input>
            </div>

            <div class="createEntry">
                <div class="label">Venue</div>
                <input type="text" name="venue" id="venue" value="$venue$" class="createValue"></input>
            </div>

            <div class="createEntry">
                <div class="label">Description<span class="mandatory">*</span></div>
                <textarea name="description" id="description" value="$description$" class="createValue"></textarea>
            </div>

            <div class="createEntry">
                <div class="label">Coordinator</div>
                <input type="text" name="coordinator" id="coordinator" value="$coordinator$" class="createValue"></input>
            </div>

            <div class="createEntry">
                <div class="label">Attendees<span class="mandatory">*</span></div>
                <textarea name="attendees" id="attendees" value="$attendees$" class="createValue"></textarea>
            </div>

            <div class="createEntry">
                <div class="label">Notes</div>
                <textarea name="notes" id="notes" value="$notes$" class="createValue"></textarea>
            </div>

            <input type="submit" id="save" value="Save"/>
            <input type="button" id="clear" value="Clear"/>

            <div id="errorMessages" class="errorMessages"></div>

        </div>

    </form>

</div>