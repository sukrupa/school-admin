<span>Student ID</span>
<span>$student.studentId$</span>
<br />

<span>Name</span>
<span class="name">$student.name$</span>

<br/>

<span>Date of birth</span>
<span>$student.dateOfBirth$</span>

<br/>

<span>Gender</span>
<span>$student.gender$</span>

<br/>

<span>Class</span>
<span>$student.studentClass$</span>

<br/>

<span>Religion</span>
<span>$student.religion$</span>

<br/>

<span>Caste</span>
<span>$student.caste$</span>

<br/>

<span>Subcaste</span>
<span>$student.subCaste$</span>

<br/>

<span>Community location</span>
<span>$student.communityLocation$</span>

<br />
<span>Talents</span>
$student.talents: {
    <span>$it.description$</span>
}$

<br/>

<form action="./update">
    <input type="Submit" value="Update" />
    <input type="hidden" name="studentId" value="$student.studentId$"></input>
    <input type="button" value="Cancel" onclick="history.go(-1);return false;" />
</form>