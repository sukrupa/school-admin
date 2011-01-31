<html>
<body>

<form action="parametricSearchResult" method="get">
	<span>Class</span>
	<select name="class" >
		$classes:{
			<option>$it$</option>
		}$
	</select>
	<br />
	<input type="submit" value="Search"/>
</form>
</body>
</html>