<form name="searchParam" action="." method="get">
    <table>


        <tr>
            <td>
                <span>Talent</span>
            </td>
            <td>
                    $formhelper.talents:{
                    <input type="checkbox" value="$it$">$it$</input>
                    }$

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