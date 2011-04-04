<div id="student">
    <form id="createStudent" name="createStudent" action="/students/create" method="post">

        <div id="studentInfo">
            <div id="basicInfo" >
                <div class="updateEntry">
                    <div class="label">Student ID<span class="mandatory">*</span></div>
                    <input type="text" name="studentId" id="studentId" value="$student.studentId$" class="updateValue"></input>
                    <div class="errorMessage">$studentIdError$</div>
                </div>

                <div class="updateEntry">
                    <div class="label">Name<span class="mandatory">*</span></div>
                    <input type="text" id="name" name="name" value="$student.name$" class="updateValue"></input>
                    <div class="errorMessage">$nameError$</div>
                </div>

                <div class="updateEntry">
                    <div class="label">Date of Birth<br/>(dd-mm-yyyy)<span class="mandatory">*</span></div>
                    <input type="text" id="dateOfBirth" name="dateOfBirth" value="$student.dateOfBirthForDisplay$" class="updateValue"></input>
                    <div class="errorMessage">$dateOfBirthError$</div>
                </div>


        </div>

        <div id="update">
            <input type="submit" id="register" value="Register"/>
            <input type="button" id="cancel" value="Cancel" />
        </div>

        <div id="errorMessages"></div>

    </form>
</div>