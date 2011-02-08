<table>
    <thead class="header">
    <tr>
        <td>Student ID</td>
        <td>Name</td>
        <td>Gender</td>
        <td>Age</td>
        <td>Talents</td>
    </tr>
    </thead>
    <tbody>
    $students:{
    <tr class="student">
        <td class="studentId"><a href="/sukrupa/app/students/$it.studentId$">$it.studentId$</a></td>
        <td class="name">$it.name$</td>
        <td class="gender">$it.gender$</td>
        <td class="age">$it.age$</td>
        <td class="talents">$it.talentsForDisplay$</td>
    </tr>
    }$
    </tbody>
</table>
