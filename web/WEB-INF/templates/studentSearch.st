<html>
<body>

<form name="searchParam" action="searchResult" method="post">

	<h1>Student Search</h1>

	<span>Class</span>
	<select name="class" >
		$classes:{
		<option>$it$</option>
		}$
	</select>

	<br/>


	<span>Gender</span>
	<select name="gender" >
		$sexes:{
		<option>$it$</option>
		}$
	</select>

	<br/>

	<span>Caste</span>
	<select name="caste" >
		$castes:{
		<option>$it$</option>
		}$
	</select>

	<br/>

	<span>Area</span>
	<select name="area" >
		$areas:{
		<option>$it$</option>
		}$
	</select>

	<br/>

	<span>Age From</span>
	<select name="ageFrom" >
		$agesFrom:{
		<option>$it$</option>
		}$
	</select>

	&nbsp;<span>to</span>&nbsp;
	<select name="ageTo" >
		$agesTo:{
		<option>$it$</option>
		}$
	</select>

	<br/>

	<span>Talent</span>
	<select name="talent" >
		$talents:{
		<option>$it$</option>
		}$
	</select>

	<br/><br/>

	<input type="submit" value="Search"/>
</form>
</body>
</html>