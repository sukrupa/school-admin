<html>
<body>
<form name="updateStudent" action="update" method="post">
<form name="updateStudent" action="update" method="post">

<h1>Update Student Record</h1>

	<span>Student ID</span>
	<input type="text" name="studentId" value=
		$studentId:{
		$it$
		}$ >
	</input>

	<br/>

	<span>Name</span>
	<input type="text" name="name" value=
		$name:{
		$it$
		}$ >
	</input>

	<br/>

	<span>Date of birth</span>
	<input type="text" name="dateOfBirth" value=
		$dateOfBirth:{
		$it$
		}$  >
	</input>

	<br/>

	<span>Gender</span>
	<input type="text" name="gender" value=
		$gender:{
		$it$
		}$    >
	</input>

	<br/>

	<span>Religion</span>
	<input type="text" name="religion" value=
		$religion:{
	    $it$
		}$    >
	</input>

	<br/>

	<span>Caste</span>
	<input type="text" name="caste" value=
		$caste:{
		$it$
		}$   >
	</input>

	<br/>

	<span>Subcaste</span>
	<input type="text" name="subCaste" value=
		$subCaste:{
		$it$
		}$   >
	</input>

	<br/>

	<span>Area</span>
	<input type="text" name="area" value=
		$area:{
		$it$
		}$ >
	</input>

	<br/>

	<span>Father</span>
	<input type="text" name="father" value=
		$father:{
		$it$
		}$ >
	</input>

	<br/>

	<span>Mother</span>
	<input type="text" name="mother" value=
		$mother:{
		$it$
		}$   >
	</input>

	<br/>

	<span>Talents</span>
	<input type="text" name="talents" value=
		$talents:{
		$it$
		}$   >
	</input>

	<br/>

<input type="submit" value="Update"/>

</form>
</body>
</html>