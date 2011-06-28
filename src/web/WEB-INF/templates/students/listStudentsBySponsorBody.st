<div id="studentsBySponsorListPages">
   <div id="searchCriteria" class="searchCriteria">
       Search results for:
       $searchCriteria:{
            <td>$it$ |</td>
       }$
   </div>
    <div class="page">
        <table class="listTable">
            <thead class="header">
            <tr>
                <td>Sponsor Name</td>
                <td>Name</td>

            </tr>
            </thead>
            <tbody>

            $page.students:{
            <tr class="student">
                <td class="sponsor">$it.sponsor$</td>
                <td class="name"><a href="/students/$it.studentId$">$it.name$</a></td>


            </tr>
            }$
            </tbody>
        </table>
    </div>



    <br />
    $if(page.previousEnabled)$<a href="$page.previousPageUrl$" id="previousButton" class="navLink"><b>Previous</b></a>$else$<b>Previous</b>$endif$
    &nbsp;&nbsp;
    Current page: $page.pageNumber$
    &nbsp;&nbsp;
    $if(page.nextEnabled)$<a href="$page.nextPageUrl$" id="nextButton" class="navLink"><b>Next</b></a>$else$<b>Next</b>$endif$

</div>



