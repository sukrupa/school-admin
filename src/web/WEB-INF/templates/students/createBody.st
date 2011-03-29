<div id="student">
    <form id="createStudent" name="createStudent" action="/students/create" method="post">

        <div id="studentInfo">
            <div id="basicInfo">
                <div class="updateEntry">
                    <div class="label">Student ID</div>
                    <input type="text" name="studentId" id="studentId" value="$student.studentId$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Name<span class="mandatory">*</span></div>
                    <input type="text" id="name" name="name" value="$student.name$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Date of Birth<br/>(dd-mm-yyyy)<span class="mandatory">*</span></div>
                    <input type="text" id="dateOfBirth" name="dateOfBirth" value="$student.dateOfBirthForDisplay$" class="updateValue"></input>
                </div>

        </div>

        <div id="update">
            <input type="submit" id="submit" value="Submit"/>
            <input type="button" id="cancel" value="Cancel" />
        </div>

        <div id="errorMessages"></div>

    </form>
</div>