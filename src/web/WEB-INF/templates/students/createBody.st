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
                    <br></br>
                    <div class="errorMessage">$dateOfBirthError$</div>
                </div>

              <div class="updateEntry">
                    <div class="label">Gender</div>
                    <select name="gender" class="updateValue">
                        $formhelper.gendersDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>
        <div class="updateEntry">
                    <div class="label">Class</div>
                    <select name="studentClass" class="updateValue">
                        $formhelper.classesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Religion</div>
                    <select name="religion" class="updateValue">
                        $formhelper.religionsDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Caste</div>
                    <select name="caste" class="updateValue">
                        $formhelper.castesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Subcaste</div>
                    <select name="subCaste" class="updateValue">
                        $formhelper.subCastesDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Community Location</div>
                    <select name="communityLocation" class="updateValue">
                        $formhelper.communityLocationsDropDownList:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Father's Name</div>
                    <input type="text" name="father" value="$student.father$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Mother's Name</div>
                    <input type="text" name="mother" value="$student.mother$" class="updateValue"></input>
                </div>
            </div>

            <div class="talentsList">
                <div class="talentsLabel">Talents</div>
                <div class="talentsCheckBoxes">
                    $formhelper.talentsCheckBoxList:{
                    <div>
                        <input type="checkbox" name="talents" value="$it.value$" $if (it.checked)$ checked $endif$>
                            $it.value$
                        </input>
                    </div>
                    }$
                </div>
            </div>
        </div>

        <div id="update">
            <input type="submit" id="submit" value="Submit"/>
            <input type="button" id="cancel" value="Cancel" />
        </div>

        <div id="errorMessages"></div>

    </form>
</div>