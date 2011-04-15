<div id="talentInfo">

    $if (talentAddedSuccesfully)$
    <div id="successDiv">
        <img id="successImage" src="/images/success.png" alt="Success"/>

        <div id="confirmMessage">$talentDescription$ added successfully.</div>
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

        <div id="createEventErrors">
		<div id="errorMessages">
			$if (talentInvalid)$
			Invalid Talent
			$endif$
		</div>
	</div>

	</div>
    </form>
</div>