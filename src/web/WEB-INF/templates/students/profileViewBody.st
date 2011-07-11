<div id="student">
    <div id="studentInfo">
        <div id="basicInfo">
            <form name="profileView" id="profileView" action="/students/sendprofileview" method="post">

                <div class="entry">
                    <div class="label">Name</div>
                    <div class="value">$student.name$</div>
                    <input type="hidden" name="studentName" id="studentName" value="$student.name$"/>
                    <input type="hidden" name="studentId" id="studentId" value="$student.studentId$" />
                </div>

                <div class="entry">
                    <div class="label">Date of Birth</div>
                    <div class="value">$student.dateOfBirthForDisplay$</div>
                    <input type="hidden" name="dateOfBirth" id="dateOfBirth" value="$student.dateOfBirth$"/>
                </div>

                <div class="entry">
                    <div class="label">Gender</div>
                    <div class="value">$student.gender$</div>
                    <input type="hidden" name="gender" id="gender" value="$student.gender$"/>
                </div>

                <div class="entry">
                    <div class="label inline-block">Background</div>$if(student.profile)$<div class="value inline-block" id="student-background">$student.profile.background$</div><input type="hidden" name="studentBackground" id="studentBackground" value="$student.profile.background$"/>$endif$
                </div>

                <div class="entry">
                    <div class="label inline-block">Disciplinary</div><div class="value inline-block" id="student-disciplinary">$student.disciplinary$</div><input type="hidden" name="studentDisciplinary" id="studentDisciplinary" value="$student.disciplinary$"/>
                </div>

                <div class="entry" id="noteToSendEmail">
                    <h3> Send Profile To Sponsors</h3>
                </div>

                <div class="createEntry">
                    <div class="label">To:<span class="mandatory">*</span></div>
                    <input type="text" id="sendTo" name="sendTo"/>
                    <span class="errorMessage">$toEmpty$</span>
                </div>

                <div class="createEntry">
                    <div class="label">Subject:<span class="mandatory">*</span></div>
                    <input type="text" id="subject" name="subject"/>
                    <span class="errorMessage">$subjectEmpty$</span>
                </div>

                <div class="createEntry">
                    <div class="label">Comments:</div><br></br>
                    <textarea name="comments" id="comments" cols="40" rows="10"></textarea>
                </div>

                <div id="sendProfileButton" class="sendProfile">
                    <input type="submit" name="sendProfile" id="sendProfile" value="Send Profile"/>
                </div>
            </form>
        </div>
        <div id="secondaryInfo">

            <div class="entry">
                <div class="label">Student Status</div>
                <div class="value existing $statusType$">$student.status$</div>
            </div>

            <img alt="$student.name$" src="/students/$student.imageLink$/image" class="studentImage"
                 id="$student.imageLink$"/>

        </div>
    </div>
</div>
