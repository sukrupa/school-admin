<form name="searchParam" action="." method="get">
    <table>


        <tr>
            <td>
                <span>Talent</span>
            </td>
            <td>
                <select name="talent">
                    <option value="">Any</option>
                    $talents:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <span>Age From</span>
            </td>
            <td>
                <select name="ageFrom" id="ageFrom" onchange="changeAgeRange()">
                    <option value="">Any</option>
                    $agesFrom:{
                    <option value="$it$">$it$</option>
                    }$
                </select>

                &nbsp;<span>to</span>&nbsp;
                <select name="ageTo" id="ageTo">
                    <option value="">Any</option>
                    $agesTo:{
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
                    <option value="">Any</option>
                    $classes:{
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
                    <option value="">Any</option>
                    $genders:{
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
                    <option value="">Any</option>
                    $religions:{
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
                    <option value="">Any</option>
                    $castes:{
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
                    <option value="">Any</option>
                    $communityLocations:{
                    <option value="$it$">$it$</option>
                    }$
                </select>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input style="float:right;" type="submit" value="Search"/></td>
        </tr>
    </table>
</form>