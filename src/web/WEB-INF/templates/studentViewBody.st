<p class="name">$student.name$</p>

<form action="./update">
    <input type="Submit" value="Update" />
    <input type="hidden" name="studentId" value="$student.studentId$"></input>
    <input type="button" value="Cancel" onclick="history.go(-1);return false;" />
</form>