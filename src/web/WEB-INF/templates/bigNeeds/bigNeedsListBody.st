<div>
    <div class="page" id="pageDiv">
        <form id="bigNeedsForm" method="post" action="/bigneeds/create">
            <input type="hidden" name="itemName" />
            <input type="hidden" name="priority" />
            <input type="hidden" name="itemCost" />
            <input type="hidden" name="itemId" />
        </form>
        <table border="0">
            <thead class="header">
            <tr>
                <td>Priority</td>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            $priority:{
            <tr>
                <td><input class="item-priority" id="priority" value="$it$"/></td>
                <td><input class="item-name" id="itemName"/></td>
                <td><input class="item-cost" id="cost"/></td>
                <td>
                    <input type="button" class="add-bigneed-button" value="Add"/>
                </td>
            </tr>
            }$
            $bigNeedList:{
            <tr>
                <td class="hidden"><input class="item-id" value="$it.id$" /></td>
                <td class="edit"><input class="item-priority" value="$it.priority$" name="priority"/></td>
                <td class="edit"><input class="item-name" value="$it.itemName$"/></td>
                <td class="edit"><input class="item-cost" value="$it.cost$"/> </td>
                <td class="edit">
                    <input type="button" class="save-row-button" value="Save"/>
                </td>
                <td class="display">$it.priority$</td>
                <td class="display">$it.itemName$</td>
                <td class="display">$it.cost$</td>
                <td class="display">
                    <input type="button" class="edit-bigneed-button" name="edit" value="Edit"/>
                </td>
                <td>
                    <input type="button" class="delete-bigneed-button" id="deleteNeedButton" value="Delete"/>
                </td>
            </tr>
            }$
            </tbody>
        </table>
        <div id="msg">$message$ $bigneed.itemName$</div>
    </div>
</div>
