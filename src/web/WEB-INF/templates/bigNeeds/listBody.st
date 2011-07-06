
<style>
    .edit {
        display: none;
    }

    .editable .edit {
        display: table-cell;
    }
    .editable .display {
        display: none;
    }
</style>
<div>
    <div class="page">
        <table border="0">
            <thead class="header">
            <tr>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form name="createBigNeedsForm" method="post" action="/bigneeds/create">
                    <td><input type="text" id="itemName" name="itemName"/></td>
                    <td><input type="text" id="cost" name="cost"/></td>
                    <td><input type="submit" name="add" value="Add"/></td>
                </form>
            </tr>
            $bigNeedList:{
            <tr>
                <form id="editBigNeedsForm" name="editBigNeedsForm" method="post" action="/bigneeds/$it.id$/edit">
                    <td class="edit"><input value="$it.itemName$"/></td>
                    <td class="edit"><input value="$it.cost$"/></td>
                    <td class="edit">
                        <input type="button" onclick="saveEditedNeed($it.id$)" id="saveNeedButton" value="Save"/>
                    </td>
                    <td class="display">$it.itemName$</td>
                    <td class="display">$it.cost$</td>
                    <td class="display">
                        <input type="button" id="editNeedButton" name="edit" value="Edit"/>
                    </td>
                    <td>
                        <input type="button" onclick="deleteNeed($it.id$)" id="deleteNeedButton" value="Delete"/>
                    </td>
                </form>
            </tr>
            }$
            </tbody>
        </table>
        <div id="msg">$message$ $bigneed.itemName$</div>
    </div>

</div>
