<div>

    $if (noteAddedSuccesfully)$
        <div id="successDiv">
            <img id="successImage" src="/images/success.png" alt="Success" />
            <div id ="confirmMessage">$noteUpdateStatus$</div>
        </div>
    $endif$

$students/studentForm()$

 <hr/>
 <h2> Notes </h2>

 <div id="noteTooLongMessage" class="errorMessages"></div>

$studentNotes()$

</div>