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
                <td><label for="priority">Priority</label></td>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            <tr>
             $priority:{
                <form name="createBigNeed" method="post" action="/bigneeds/create">
                    <td><input type="text" id="priority" name="priority" value="$it$"/></td>
                    <td><input type="text" id="itemName" name="itemName" /></td>
                    <td><input type="text" id="cost" name="cost" /></td>
                    <td><input type="submit" name="add" value="Add" /></td>
                </form>
              }$
            </tr>
            $bigNeedList:{

                    <!--
                <form id="editBigNeedsForm" name="editBigNeedsForm" method="post" action="/bigneeds/$it.id$/edit">

                    <td class="edit"><textbox value="$it.itemName$" id="$it.id$.itemName" name="$it.id$.itemName"/></td>
                    <td class="edit"><textbox value="$it.cost$" id="$it.id$.cost" name="$it.id$.cost" /></td>

                    <td class="edit">
                        <input type="button" onclick="saveEditedNeed($it.id$, $it.id$.itemName.value, $it.id$.cost.value)" id="saveNeedButton" value="Save"/>
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
-->
                <form name="editBigNeedsForm" method="post" action="">
                    <td class="hidden"><input class="item-id" value="$it.id$" /></td>
                    <td class="edit"><input class="item-name" value="$it.itemName$" id="itemName3" name="itemName3"/></td>
                    <td class="edit"><input class="item-cost" value="$it.cost$" id="cost$it.id$" name="cost2" /> </td>
                    <td class="edit">
                        <input type="button" class="save-row-button" value="Save"/>
                    </td>
                    <td>$it.priority$</td>
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
