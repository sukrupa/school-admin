<div id="student">
    <div id="successDiv">
        <img id="successImage" src="/images/success.png" alt="Success" />
        <div id ="confirmMessage">$studentUpdatedSuccesfullyMessage$</div>
	</div>

	<div id="studentInfo">
		<div id="basicInfo">
			<div class="entry">
				<div class="label">Student ID</div>
				<div class="value">$student.studentId$</div>
			</div>

			<div class="entry">
				<div class="label">Name</div>
				<div class="value">$student.name$</div>
			</div>

			<div class="entry">
				<div class="label">Date of Birth</div>
				<div class="value">$student.datofBirthForDisplay$</div>
			</div>

			<div class="entry">
				<div class="label">Gender</div>
				<div class="value">$student.gender$</div>
			</div>

			<div class="entry">
				<div class="label">Class</div>
				<div class="value">$student.studentClass$</div>
			</div>

			<div class="entry">
				<div class="label">Religion</div>
				<div class="value">$student.religion$</div>
			</div>

			<div class="entry">
				<div class="label">Caste</div>
				<div class="value">$student.caste$</div>
			</div>

			<div class="entry">
				<div class="label">Subcaste</div>
				<div class="value">$student.subCaste$</div>
			</div>

			<div class="entry">
				<div class="label">Community Location</div>
				<div class="value">$student.communityLocation$</div>
			</div>

			<div class="entry">
				<div class="label">Father's Name</div>
				<div class="value">$student.father$</div>
			</div>

			<div class="entry">
				<div class="label">Mother's Name</div>
				<div class="value">$student.mother$</div>
			</div>
		</div>

		<div id="talents">
			<div class="talentsLabel">Talents</div>
			$student.talents:{
			<div class="talent">$it.description$</div>
			}$
		</div>
	</div>

	<div class="notes">
		$studentNotesDisplay()$
	</div>

	<div id="update">
		<form action="./update">
			<input type="submit" value="Edit"/>
			<input type="hidden" name="studentId" value="$student.studentId$"></input>
		</form>
	</div>

</div>
