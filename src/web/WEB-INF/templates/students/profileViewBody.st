<div id="student">
    $successMessage(condition=studentUpdatedSuccesfully, message="Student record updated successfully.")$

    <div id="studentInfo">
        <div id="basicInfo">

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
                <div class="label inline-block">Background</div>$if(student.profile)$<div class="value inline-block" id="student-background" name="student-background">$student.profile.background$</div>$endif$
            </div>

            <div class="entry">
                <div class="label inline-block">Disciplinary</div><div class="value inline-block" id="student-disciplinary" name="student-disciplinary">$student.disciplinary$</div>
            </div>


            <div class="entry" id="noteToSendEmail">
            <h3> Send Profile To Sponsors </h3>
            </div>

            <div class="createEntry">
                <div class="label"> To: </div>
                <input type="text" id="sendTo" name="SendTo"  />
            </div>

            <div class="createEntry">
                <div class="label"> Subject: </div>
                <input type="text" id="subject" name="Subject"  />
            </div>

            <div class="createEntry">
                <div class="label"> Comments </div>
                <textarea name="comments" id="comments" cols="40" rows="10"> </textarea>
            </div>


            <div id="sendProfileButton" class = "sendProfile">
                  <a href = "/students/thankYouView"><input type="submit" name="sendProfile" id="sendProfile" value="Send Profile"/></a>
              </div>
            
        </div>
        <div id="secondaryInfo">

            <div class="entry">
                <div class="label">Student Status</div>
                <div class="value existing $statusType$">$student.status$</div>
            </div>

            <img src="/students/$student.imageLink$/image" class="studentImage" id="$student.imageLink$"/>

        </div>
    </div>
</div>
