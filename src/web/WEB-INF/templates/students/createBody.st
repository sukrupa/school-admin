<div id="student">
    <form id="createStudent" name="createStudent" action="/students/create" method="post">

        <div id="studentInfo">
            <div id="basicInfo" >
                <div class="updateEntry">
                    <div class="label">Student ID<span class="mandatory">*</span></div>
                    <div class="textSpace">
                        <input type="text" name="studentId" id="studentId" value="$student.studentId$" class="updateValue"></input>
                        <span class="errorMessage">$studentIdError$</span>
                    </div>
                </div>

                <div class="updateEntry">
                    <div class="label">Name<span class="mandatory">*</span></div>
                    <div class="textSpace">
                        <input type="text" id="name" name="name" value="$student.name$" class="updateValue"></input>
                        <span class="errorMessage">$nameError$</span>
                    </div>
                </div>

                <div class="updateEntry">
                    <div class="label">Date of Birth (dd-mm-yyyy)<span class="mandatory">*</span></div>
                    <div class="textSpace">
                        <input type="text" id="dateOfBirth" name="dateOfBirth" value="$student.dateOfBirthForDisplay$" class="updateValue"></input>
                        <span class="errorMessage">$dateOfBirthError$</span>
                    </div>
                </div>

                <div class="updateEntry">
                    <div class="label">Gender</div>
                    <div class="textSpace">
                        <select name="gender" class="updateValue">
                            $formhelper.gendersDropDownList:{
                            <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                            }$
                        </select>
                    </div>
                </div>
        </div>

        <div id="update">
            <input type="submit" id="register" value="Register"/>
            <input type="button" id="cancel" value="Cancel" />
        </div>

        <div id="errorMessages"></div>

    </form>
</div>