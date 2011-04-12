<div class="annualUpdateBody">
    <div class="informationMessage">
        $if(!classAlreadyUpdated)$
        <p>You are about to move ALL the students up one class.</p>
        <p>Students in Class 10 will be moved to the status Alumni. Records with status Alumni and Dropouts will not be affected</p>
        $else$
            <p> Class has already been updated this year</p>
        $endif$
    </div>
    <div class='lastUpdate'>
          <p>The records were last updated on: <span id='updateDate'>$if(classUpdateDate)$ $classUpdateDate$ $else$-- - -- - ----$endif$</span></p>
    </div>
    $if(!classAlreadyUpdated)$
        <div class="buttonBar">
            <form action="/admin/annualupdate/confirmation" method="GET">
                <input type="submit" value="Update students" />
            </form>
        </div>
            $endif$

</div>

