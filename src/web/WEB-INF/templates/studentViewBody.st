<span>Student ID</span>
<span class="studentID">$student.studentId$</span>
<br />

<span>Name</span>
<span class="name">$student.name$</span>
<br />

<span>Date of birth</span>
<span class="dateOfBirth">$student.dateOfBirth$</span>
<br />

<span>Gender</span>
<span class="gender">$student.gender$</span>
<br />

<span>Class</span>
<span class="class">$student.studentClass$</span>
<br />

<span>Religion</span>
<span class="religion">$student.religion$</span>
<br />

<span>Caste</span>
<span class="caste">$student.caste$</span>
<br />

<span>Subcaste</span>
<span class="subcaste">$student.subCaste$</span>
<br />

<span>Area</span>
<span class="area">$student.area$</span>
<br />

<span>Father</span>
<span class="father">$student.father$</span>
<br />

<span>Mother</span>
<span class="area">$student.mother$</span>
<br />

<span>Talents</span>
<br/>
$student.talents:{
    <span>$it$</span>
    <br/>
}$

<br />
<br />





<form action="./update">
    <input type="Submit" value="Update" />
    <input type="hidden" name="studentId" value="$student.studentId$"></input>
    <input type="button" value="Cancel" onclick="history.go(-1);return false;" />
</form>