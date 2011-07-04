<div id="talentInfo">
    $successMessage(condition=talentAddedSuccesfully, message={$talentDescription$ added successfully.})$
    <form id="createTalent" name="createTalent" action="/admin/talents/new" method="post">
        <div class="updateEntry">
            <div class="label">Talent Description<span class="mandatory">*</span></div>
            <div class="textSpace">
                <input type="text" name="description" id="description" value="$talent.description$" class="updateValue"/>
            </div>
        </div>

        <div id="update">
            <input type="submit" id="addNewTalent" value="Add Talent"/>
        </div>

        <div id="createEventErrors">
            <div id="errorMessages">
                $if (talentDuplicated)$
                Talent already exists. Please enter a new talent.
                $endif$
                $if (talentInvalid)$
                Please enter talent name in talent field.
                $endif$
            </div>
        </div>
    </form>
</div>