<div class="adminOptions">
  <div class="page">

      <table class="listTable">
            <thead class="header">
            <tr>
                <td>Student Name</td>
                <td>Sponsor Name</td>
                <td>Sponsor's Email</td>

            </tr>
            </thead>
            <tbody>
              $page.students:{
            <tr class="student">
                <td class="sponsor">$it.name$</td>
                <td class="name">$it.sponsor$</td>
                <td></td>

            </tr>
            }$
            </tbody>
        </table>
    </div>
</div>