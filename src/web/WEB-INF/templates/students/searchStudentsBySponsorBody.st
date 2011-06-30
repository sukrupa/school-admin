
<form id="searchStudentsBySponsorForm" name="searchParam" action="listsponsorsearch" method="get" >
    <table>

        <tr>
            <td>
                <span>Enter Sponsor's Name</span>
            </td>
             <input type="hidden" name="sponsored" id="sponsored" onchange="updateSponsor()" value="Yes"> </input>

            <td>
                 <input type="text" id="sponsorName" name="sponsorName"  />
            </td>
        </tr>

        <tr>
            <td><input type="submit" value="Search"/></td>
            <td></td>
        </tr>
    </table>
</form>