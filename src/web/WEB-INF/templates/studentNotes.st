<div class="notes">
    <form action="/students/$studentId$/notes" method="post">
        <textarea name="new-note" rows="3" cols="80"></textarea> <br/><br/>
        <input type="submit" class="add-note" value="Add a Note"/>
        <input type="button" class="add-note" value="Cancel"/>

        <div class="box">
            $student.notes:{
            <div class="note">

                $it.message$
            </div>
            }$
        </div>
    </form>
</div>