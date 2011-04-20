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
                <div class="label">Family Status</div>
                <div class="value" id="family-status">$student.familyStatus$</div>
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

            <div class="entry">
                 <div class="label inline-block">Events</div>
                 <div class="value inline-block" id="student-event" name="student-event">$student.eventsForDisplay$</div>
            </div>

        </div>
        <div id="secondaryInfo">
            <div class="entry">
                <div class="label">Student Status</div>
                <div class="value $statusType$">$student.status$</div>
            </div>

            <img src="/students/$student.imageLink$/image" class="studentImage" id="$student.imageLink$">

             <div class="entry">
                 $if(sponsored)$
                <div class="label">Sponsor</div>
                <div class="value" id="sponsor">$student.sponsor$</div>
                 $else$
                 <div class="label">Not Sponsored</div>
                $endif$
            </div>


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
            <h2>Parent Details</h2>
            <div id="fatherDetails">
                <div class="labelHead">Father</div>
                $students/caregiver(caregiver=student.father,label="father")$
            </div>
            <div id="motherDetails">
                <div class="labelHead">Mother</div>
                $students/caregiver(caregiver=student.mother,label="mother")$
            </div>
            <div style="clear:both"></div>
            <div id="guardianDetails">
                <div class="labelHead">Guardian</div>
                <div class="entry">
                    <div class="label">Name</div>
                    <div class="value" id="guardianName" >$student.guardian.name$</div>
                </div>
                <div class="entry">
                    <div class="label">Education</div>
                    <div class="value" id="guardianEducation">$student.guardian.education$</div>
                </div>
                <div class="entry">
                    <div class="label">Contact</div>
                    <div class="value" id="guardianContact">$student.guardian.contact$</div>
                </div>
                <div class="entry">
                    <div class="label">Occupation</div>
                    <div class="value" id="guardianOccupation">$student.guardian.occupation$</div>
                </div>
                <div class="entry">
                    <div class="label">Salary</div>
                    <div class="value" id="guardianSalary">$student.guardian.salary$</div>
                </div>
            </div>
        </div>

        <div id="update">
            <form action="/students/$student.studentId$/edit" method="GET">
                <input type="submit" value="Edit" id="student-edit"/>
            </form>
        </div>
    </div>
</div>
