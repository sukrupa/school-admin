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
        <th>Sex</th>
        <th>Age</th>
    </tr>
    <tbody>

    $students:{
    <tr>
        <td>$it.name$</td>
        <td>$it.studentId$</td>
        <td>$it.sex$</td>
        <td>$it.age$</td>
    </tr>
    }$

    </tbody>
</table>

</body>
</html>
