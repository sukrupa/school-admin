<html>
<body>
<form name="updateStudent" action="updateConfirm" method="post">

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
    <select name="gender">
        $genders:{
        <option$if (it.selected)$ selected="selected"$endif$>$it.value$</option>
        }$
    </select>

    <br/>

    <span>Class</span>
    <select name="studentClass" >
        $classes:{
        <option$if (it.selected)$ selected="selected"$endif$>$it.value$</option>
        }$
    </select>

    <br/>

    <span>Religion</span>
    <input type="text" name="religion" value=
            $religion:{
    $it$
    }$    >
    </input>

    <br/>

    <span>Caste</span>
    <select name="caste" >
        $castes:{
        <option$if (it.selected)$ selected="selected"$endif$>$it.value$</option>
        }$
    </select>

	<br/>

    <span>Subcaste</span>
    <input type="text" name="subCaste" value=
    $subCaste:{
    $it$
    }$   >
    </input>

    <br/>

    <span>Area</span>
    <select name="area" >
        $areas:{
        <option$if (it.selected)$ selected="selected"$endif$>$it.value$</option>
        }$
    </select>

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