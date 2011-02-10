<div id="studentListPages">
    <div class="page">
        <table>
            <thead class="header">
            <tr>
                <td>Student ID</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Age</td>
                <td>Talents</td>
                <td>Class</td>
                <td>Religion</td>
                <td>Caste</td>
                <td>Community Location</td>
            </tr>
            </thead>
            <tbody>

    $page.students:{
        <tr class="student">
            <td class="studentId">$it.studentId$</td>
            <td class="name"><a href="/students/$it.studentId$">$it.name$</a></td>
            <td class="gender">$it.gender$</td>
            <td class="age">$it.age$</td>
            <td class="talents">$it.talentsForDisplay$</td>
            <td class="class">$it.studentClass$</td>
            <td class="religion">$it.religion$</td>
            <td class="caste">$it.caste$</td>
            <td class="communityLocation">$it.communityLocation$</td>
        </tr>
    }$
        </tbody>
    </table></div>
    <a href="#" id="previousButton" class="navLink">Previous</a> &nbsp;&lt;&lt;&nbsp;
    $pages: {
    <a href="#" class="pageButton navLink">$i$</a> &nbsp;
    }$
    &gt;&gt;&nbsp;
    <a href="#" id="nextButton" class="navLink">Next</a>
 </div>



