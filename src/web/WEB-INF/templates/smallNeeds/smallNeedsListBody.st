$successMessage(condition=shouldDisplayMessage, message=message)$
<div>
    <div class="page" id="pageDiv">
        <form id="smallNeedsForm" method="post" action = "/smallneeds/create">
            <input type="hidden" name="itemName" />
            <input type="hidden" name="priority" />
            <input type="hidden" name="itemCost" />
            <input type="hidden" name="itemId" />
            <input type="hidden" name="comment"/>
        </form>
            <span class="errorMessage" id="error"></span>
        <table border="0">
            <thead class="header">
            <tr>
                <td><label for="priority">Priority</label></td>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td><label for="comment">Comment</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            $priority:{
            <tr>
                <td><input class="item-priority" id="priority" value="$it$"/></td>
                <td><input class="item-name" id="itemName"/></td>
                <td><input class="item-cost" id="cost"/></td>
                <td><input class="item-comment" id="comment"/></td>
                <td>
                    <input type="button" class="add-smallneed-button" value="Add"/>
                </td>
            </tr>
            }$
            $smallNeedList:{
            <tr>
                <td class="hidden"><input class="item-id" value="$it.id$"/></td>
                <td class="edit"><input class="item-priority" value="$it.priority$" name="priority"/></td>
                <td class="edit"><input class="item-name" value="$it.itemName$" name="itemName"/></td>
                <td class="edit"><input class="item-cost" value="$it.cost$" name="cost"/></td>
                <td class="edit"><input class="item-comment" value="$it.comment$" name="comment"/></td>
                <td class="edit">
                    <input type="button" class="save-row-button" value="Save"/>
                </td>
                <td class="edit">
                    <input type="button" class="cancel-row-edit" value="Cancel"/>
                </td>
                <td class="display">$it.priority$</td>
                <td class="display">$it.itemName$</td>
                <td class="display">$it.cost$</td>
                <td class="display">$it.comment$</td>
                <td class="display">
                    <input type="button" class="edit-smallneed-button" name="edit" value="Edit"/>
                </td>
                <td class="display">
                    <input type="button" class="delete-smallneed-button" id="deleteNeedButton" value="Delete"/>
                </td>
            </tr>
            }$
            </tbody>
        </table>
    </div>
</div>
