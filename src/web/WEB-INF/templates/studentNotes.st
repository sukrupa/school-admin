<div class="notes">
    <form action="/students/$student.studentId$/notes" method="post">
        <textarea name="new-note" rows="3" cols="80"></textarea>
        <input type="submit" class="add-note" value="Add a Note"/>
        <div class="box">
        $student.notes:{
        <div class ="note">

            $it.message$
            </div>
        }$
         </div>
    </form>
</div>