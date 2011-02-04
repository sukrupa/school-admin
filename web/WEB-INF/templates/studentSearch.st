<html>
<head>
	<title>Search</title>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="../../scripts/studentSearch.js"></script>
</head>
<body>

<form name="searchParam" action="searchResult" method="post">
	<h1>Student Search</h1>

	<table>

		<tr>
			<td>
				<span>Class</span>
			</td>
			<td>
				<select name="studentClass">
					$classes:{
					<option>$it$</option>
					}$
				</select>
			</td>
		</tr>

		<tr>
			<td>
				<span>Gender</span>
			</td>
			<td>
				<select name="gender">
					$genders:{
					<option>$it$</option>
					}$
				</select>
			</td>
		</tr>

		<tr>
			<td>
				<span>Caste</span>
			</td>
			<td>
				<select name="caste">
					$castes:{
					<option>$it$</option>
					}$
				</select>
			</td>
		</tr>

		<tr>
			<td>
				<span>Area</span>
			</td>
			<td>
				<select name="area">
					$areas:{
					<option>$it$</option>
					}$
				</select>
			</td>
		</tr>

		<tr>
			<td>
				<span>Age From</span>
			</td>
			<td>
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
			</td>
		</tr>

		<tr>
			<td>
				<span>Talent</span>
			</td>
			<td>
				<select name="talent">
					$talents:{
					<option>$it$</option>
					}$
				</select>
			</td>
		</tr>
	</table>

	<br/>
	<input type="submit" value="Search"/>
</form>
</body>
</html>