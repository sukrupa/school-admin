<div class="notes">
    <form id="addNoteForm" action="/students/$student.studentId$/notes" method="post">
        <textarea id="new-note" name="new-note" class="noHorizontalResize" rows="3" cols="80"  autocomplete="off"></textarea> <br/><br/>
        <input type="submit" class="add-note" id="addNote" value="Add a Note"/>
        <input type="button" class="add-note" id="clearNote" value="Clear"/>

        <div class="box">
            $student.notes:{
            <p class="note">
                $it.message$
            </p>
            }$
        </div>
    </form>
</div>