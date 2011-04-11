<!--<div class="informationMessage">
    <p>You are about to move ALL the students up one class.</p>
    <p>This means that for example, students in class LKG will move up to UKG.</p>
</div>-->
<div class='lastUpdate'>
      <p>The records were last updated on: <span id='updateDate'>$if(classUpdateDate)$ $classUpdateDate$ $else$-- - -- - ----$endif$</span></p>
</div>
$if(!classAlreadyUpdated)$
        <p> Are you sure you want to update ALL students?</p>
    <div class="buttonBar">
        <form action="confirmation" method="POST">
            <input type="submit" value="Yes" />
            <input type="button" id="cancel" value="No" />
        </form>
    </div>
$else$
    <p> Class has already been updated this year</p>
$endif$