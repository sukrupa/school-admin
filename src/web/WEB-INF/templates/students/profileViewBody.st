<div id="Generate View">

</div>
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
                <div class="label">Name</div>
                <div class="value">$student.name$</div>
            </div>

            <div class="entry">
                <div class="label">Date of Birth</div>
                <div class="value">$student.dateOfBirthForDisplay$</div>
            </div>




            <div class="entry">
                 <div class="label inline-block">Background</div>
                 $if(student.profile)$<div class="value inline-block" id="student-background" name="student-background">$student.profile.background$</div>$endif$
            </div>
            <h4> Comments </h4>
        <div id="comments">
                <textarea name="comments" id="comments"> </textarea>
                </div>


        </div>
        <div id="secondaryInfo">

            <div class="entry">

                <div class="label">Student Status</div>
                <div class="value $statusType$">$student.status$</div>
            </div>

            <img src="/students/$student.imageLink$/image" class="studentImage" id="$student.imageLink$">

        </div>


        </div>
    </div>
</div>
