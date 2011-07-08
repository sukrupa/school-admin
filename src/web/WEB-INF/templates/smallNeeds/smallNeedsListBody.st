<div>
    <div class="page">
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
            <tr>
                <form name="createSmallNeedsForm" method="post" action="/smallneeds/create">
                    <td><input type="text" id="priority" name="priority" class="item-priority" value="$priority$"/></td>
                    <td><input type="text" id="itemName" name="itemName"/></td>
                    <td><input type="text" id="cost" name="cost"/></td>
                    <td><input type="text" id="comment" name="comment"/></td>
                    <td><input type="submit" name="add" value="Add"/></td>
                </form>
            </tr>
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
                <td class="display">$it.priority$</td>
                <td class="display">$it.itemName$</td>
                <td class="display">$it.cost$</td>
                <td class="display">$it.comment$</td>
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
    </div>
</div>
