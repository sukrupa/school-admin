<div>
<div id="student">
    <form id="updateStudent" name="updateStudent" action="/students/$studentId$" method="post">

        <div id="studentInfo">
            <div id="basicInfo">
                <div class="updateEntry">
                    <div class="label">Student ID</div>
                    <input type="text" name="studentId" id="studentId" value="$studentId$" readonly
                           class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Name</div>
                    <input type="text" id="name" name="name" value="$name$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Date of Birth</div>
                    <input type="text" id="dateOfBirth" name="dateOfBirth" value="$dateOfBirth$" class="updateValue"></input>
                    <div id="dateErrorMessage"></div>
                </div>

                <div class="updateEntry">
                    <div class="label">Gender</div>
                    <select name="gender" class="updateValue">
                        $genders:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Class</div>
                    <select name="studentClass" class="updateValue">
                        $classes:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Religion</div>
                    <select name="religion" class="updateValue">
                        $religions:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Caste</div>
                    <select name="caste" class="updateValue">
                        $castes:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Subcaste</div>
                    <select name="subCaste" class="updateValue">
                        $subcastes:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Community Location</div>
                    <select name="communityLocation" class="updateValue">
                        $communityLocations:{
                        <option $if (it.selected)$ selected="selected"$endif$>$it.value$</option>
                        }$
                    </select>
                </div>

                <div class="updateEntry">
                    <div class="label">Father's Name</div>
                    <input type="text" name="father" value="$father$" class="updateValue"></input>
                </div>

                <div class="updateEntry">
                    <div class="label">Mother's Name</div>
                    <input type="text" name="mother" value="$mother$" class="updateValue"></input>
                </div>
            </div>

            <div class="talentsList">
                <div class="talentsLabel">Talents</div>
                <div class="talentsCheckBoxes">
                    $talents:{
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
            <input type="button" id="save" value="Save"/>
            <input type="button" value="Cancel" onclick="location.href = '/students/$studentId$';"/>
        </div>
        <div id="errorMessages" class="errorMessages"></div>

    </form>
</div>

 <hr/>
 <h2> Notes </h2>
<div class="confirm">
    $note_message$
</div>

$studentNotes()$
</div>