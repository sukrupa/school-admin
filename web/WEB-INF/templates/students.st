<html>
<head>
	<title>Student List</title>

	<style type="text/css">
		table,th, td {
			border: 1px solid black;
		}
	</style>
</head>
<body>

<table>
    <tr style="text-align:left;">
        <th>Name</th>
        <th>Student ID</th>
        <th>Gender</th>
        <th>Age</th>
    </tr>
    <tbody>

    $students:{
    <tr class="student">
        <td class="name">$it.name$</td>
        <td class="studentId">$it.studentId$</td>
        <td class="gender">$it.gender$</td>
        <td class="age">$it.age$</td>
    </tr>
    }$

    </tbody>
</table>

</body>
</html>
