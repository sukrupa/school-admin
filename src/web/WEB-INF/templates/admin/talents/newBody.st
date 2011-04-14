<div id="talentInfo">

    $if (talentAddedSuccesfully)$
    <div id="successDiv">
        <img id="successImage" src="/images/success.png" alt="Success"/>

        <div id="confirmMessage">"$talent.description$ updated successfully.</div>
    </div>
    $endif$
    <form id="createTalent" name="createTalent" action="/admin/talents/new" method="post">
        <div class="updateEntry">
            <div class="label">Talent Description<span class="mandatory">*</span></div>
            <div class="textSpace">
                <input type="text" name="description" id="description" value="$talent.description$" class="updateValue"></input>
            </div>
        </div>

        <div id="update">
            <input type="submit" id="addNewTalent" value="Add Talent"/>
        </div>
    </form>
</div>