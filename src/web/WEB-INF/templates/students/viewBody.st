<div id="student">

    $if (studentUpdatedSuccesfully)$
    <div id="successDiv">
        <img id="successImage" src="/images/success.png" alt="Success"/>

        <div id="confirmMessage">Student record updated successfully.</div>
    </div>
    $endif$

    <div id="studentInfo">
        <div id="basicInfo">
            <div class="entry">
                <div class="label">Student ID</div>
                <div class="value">$student.studentId$</div>
            </div>

            <div class="entry">
                <div class="label">Name</div>
                <div class="value">$student.name$</div>
            </div>

            <div class="entry">
                <div class="label">Date of Birth</div>
                <div class="value">$student.dateOfBirthForDisplay$</div>
            </div>

            <div class="entry">
                <div class="label">Gender</div>
                <div class="value">$student.gender$</div>
            </div>

            <div class="entry">
                <div class="label">Class</div>
                <div class="value">$student.studentClass$</div>
            </div>

            <div class="entry">
                <div class="label">Religion</div>
                <div class="value">$student.religion$</div>
            </div>

            <div class="entry">
                <div class="label">Caste</div>
                <div class="value">$student.caste$</div>
            </div>

            <div class="entry">
                <div class="label">Subcaste</div>
                <div class="value">$student.subCaste$</div>
            </div>

            <div class="entry">
                <div class="label">Community Location</div>
                <div class="value">$student.communityLocation$</div>
            </div>

            <div class="entry">
                 <div class="label inline-block">Background</div>
                 $if(student.profile)$<div class="value inline-block" id="student-background" name="student-background">$student.profile.background$</div>$endif$
            </div>

            <div class="entry">
                 <div class="label inline-block">Academic Performance</div>
                 <div class="value inline-block" id="student-performance" name="student-performance">$student.performance$</div>
            </div>


            <div class="entry">
                 <div class="label inline-block">Disciplinary</div>
                 <div class="value inline-block" id="student-disciplinary" name="student-disciplinary">$student.disciplinary$</div>
            </div>


        </div>
        <div id="secondaryInfo">
            <div class="entry">
                <div class="label">Student Status</div>
                <div class="value $statusType$">$student.status$</div>
            </div>

            <img src="/application/images/$student.imageLink$" class="studentImage" id="$student.imageLink$">

            <div id="talents">
                <div class="talentsLabel">Talents</div>
                $student.talents:{
                <div class="talent">$it.description$</div>
                }$
            </div>
        </div>

        <div class="notes">
            $studentNotesDisplay()$
        </div>

        <div class="parentDetails">
            <div id="fatherDetails">
                <div class="labelHead">Father</div>
                <div class="entry">
                    <div class="label">Name</div>
                    <div class="value">$student.father.name$</div>
                </div>
                <div class="entry">
                    <div class="label">Education</div>
                    <div class="value">$student.father.education$</div>
                </div>
            </div>
            <div id="motherDetails">
                <div class="labelHead">Mother</div>
                <div class="entry">
                    <div class="label">Name</div>
                    <div class="value">$student.mother.name$</div>
                </div>
            </div>
            <div id="guardianDetails">
                                    Guardian
            </div>
            <div style="clear:both"></div>
        </div>

        <div id="update">
            <form action="/students/$student.studentId$/edit" method="GET">
                <input type="submit" value="Edit" id="student-edit"/>
            </form>
        </div>
    </div>
</div>
