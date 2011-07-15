<div class="adminOptions">
  <div class="page">

      <table>
            <thead class="header">
            <tr >
                <td >Student Name</td>
                <td >Sponsor Name</td>
                <td >Sponsor's Email</td>
                <td >Send End of Sponsorship Email </td>

               
            </tr>
            </thead>
            <tbody>
              $page.students:{
            <tr >
                <td >$it.name$</td>
                <td>$it.sponsor$</td>
                <td >$it.sponsorEmail$</td>
                <td ><a href="/admin/endofsponsorshipform?sponsor_email=$it.sponsorEmail$"> Send mail </a></td>
            </tr>
            }$
            </tbody>
        </table>
    </div>
</div>