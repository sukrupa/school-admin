<html>
<head>
	<title>Search</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js" ></script>
	<script type="text/javascript" src="../../scripts/studentSearch.js"></script>
</head>
<body>

<form name="searchParam" action="searchResult" method="post" >
	<h1>Student Search</h1>

	<span>Class</span>
	<select name="studentClass" >
		$classes:{
		<option>$it$</option>
		}$
	</select>

	<br/>


	<span>Gender</span>
	<select name="gender" >
		$genders:{
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
	<select name="ageFrom" id="ageFrom" onClick="changeAgeRange()">
		$agesFrom:{
		<option>$it$</option>
		}$
	</select>

	&nbsp;<span>to</span>&nbsp;
	<select name="ageTo" id="ageTo">
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