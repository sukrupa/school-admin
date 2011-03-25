
<form id="searchStudentsForm" name="searchParam" action="." method="get">
    <table>
        <tr>
            <td>
                <span>Talent</span>
            </td>
            <td>
                <table border="0">
                <tr>
                    <td>
                        <select multiple size="6" style="width:150px"  id="availableTalents">
                            $formhelper.talents:{
                            <option value="$it$">$it$</option>
                            }$
                        </select>
                    </td>
                    <td>
                        <input type="button" style="width:90" id='addTalent' name="addTalent" value="Add >"><BR>
                        <input type="button" style="width:90" id='removeTalent' name="removeTalent" value="Remove <"><BR>
                        <input type="button" style="width:90" id='clearTalents' name="clearTalents" value="Clear <<"><BR>

                    </td>
                    <td>
                        <div id="hiddenChosenOnes">

                        </div>


                        <select multiple size="6" style="width:150px" id="chosenTalents">
                            <option value="*" disabled=disabled>Any</option>
                        </select>

                    </td>
                </tr>
            </table>
            </td>
        </tr>

        <tr>
            <td>
                <span>Age From</span>
            </td>
            <td>
                <select name="ageFrom" id="ageFrom">
                    <option value="*">Any</option>
                    $formhelper.ages:{
                    <option value="$it$">$it$</option>
                    }$
                </select>

                &nbsp;<span>to</span>&nbsp;
                <select name="ageTo" id="ageTo">
                    <option value="*">Any</option>
                    $formhelper.ages:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Class</span>
            </td>
            <td>
                <select name="studentClass">
                    <option value="*">Any</option>
                    $formhelper.studentClasses:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Gender</span>
            </td>
            <td>
                <select name="gender">
                    <option value="*">Any</option>
                    $formhelper.genders:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Religion</span>
            </td>
            <td>
                <select name="religion">
                    <option value="*">Any</option>
                    $formhelper.religions:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Caste</span>
            </td>
            <td>
                <select name="caste">
                    <option value="*">Any</option>
                    $formhelper.castes:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Community Location</span>
            </td>
            <td>
                <select name="communityLocation">
                    <option value="*">Any</option>
                    $formhelper.communityLocations:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Search"/></td>
            <td></td>
        </tr>
    </table>
</form>