<div class="adminOptions">
  <div class="page">

      <table class="listTable">
            <thead class="header">
            <tr class="student">
                <td class="sponsor">Student Name</td>
                <td class="name">Sponsor Name</td>
                <td class="email">Sponsor's Email</td>

            </tr>
            </thead>
            <tbody>
              $page.students:{
            <tr class="student">
                <td class="sponsor">$it.name$</td>
                <td class="name">$it.sponsor$</td>
                <td class="email">$it.sponsor_email$
                </td>

            </tr>
            }$
            </tbody>
        </table>
    </div>
</div>