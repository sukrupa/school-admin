<html>
<head>
	<title>Student List</title>

	<link rel="stylesheet" type="text/css" href="../../styles/studentList.css" />
</head>
<body>

<table>
    <tr class="header">
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
