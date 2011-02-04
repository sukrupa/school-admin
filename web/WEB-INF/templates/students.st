<html>
<head>
    <title>Student List</title>
    <link rel="stylesheet" type="text/css" href="../../styles/studentList.css"/>
</head>
<body>

<table>
    <thead class="header">
    <tr>
        <td>Name</td>
        <td>Student ID</td>
        <td>Gender</td>
        <td>Age</td>
        <td>Talents</td>
    </tr>
    </thead>
    <tbody>
    $students:{
    <tr class="student">
        <td class="name">$it.name$</td>
        <td class="studentId">$it.studentId$</td>
        <td class="gender">$it.gender$</td>
        <td class="age">$it.age$</td>
        <td class="talents">$it.talentsForDisplay$</td>
    </tr>
    }$
    </tbody>
</table>

</body>
</html>
