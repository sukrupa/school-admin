$successMessage(condition=shouldDisplayMessage, message=message)$
<div>
    <div class="page" id="pageDiv">
        <form id="bigNeedsForm" method="post" action="/bigneeds/create">
            <input type="hidden" name="itemName" />
            <input type="hidden" name="priority" />
            <input type="hidden" name="itemCost" />
            <input type="hidden" name="itemId" />
            <input type="hidden" name="donatedAmount" />
        </form>
              <span class="errorMessage" id="error"></span>
        <table border="0">
            <thead class="header">
            <tr>
                <td><label for="priority">Priority</label></td>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td><label for="donatedAmount">Donated Amount</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            $priority:{
            <tr>
                <td><input class="item-priority" id="priority" value="$it$"/></td>
                <td><input class="item-name" id="itemName"/></td>
                <td><input class="item-cost" id="cost"/>
                <td><input class="item-donatedAmount" id="donatedAmount" value="0.0"/>
                </td>
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
                <td class="edit"><input class="item-donatedAmount" value="$it.donatedAmount$"/> </td>
                <td class="edit">
                    <input type="button" class="save-row-button" value="Save"/>
                </td>
                <td class="display">$it.priority$</td>
                <td class="display">$it.itemName$</td>
                <td class="display">$it.cost$</td>
                <td class="display">$it.donatedAmount$</td>
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
        <br><br>
        <table border="0" id="fulfilledItemsTable">
            <thead class="header">
            <tr>
                <th>Fulfilled Items</th>
            </tr>
            <tr>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td><label for="donatedAmount">Donated Amount</label></td>
            </tr>
            </thead>
            $bigNeedFulfilledList:{
            <tr class="fulfilled">
                <td class="hidden"><input class="item-id" value="$it.id$" /></td>
                <td class="display">$it.itemName$</td>
                <td class="display">$it.cost$</td>
                <td class="display">$it.donatedAmount$</td>
                <td>
                    <input type="button" class="delete-fulfilled-bigneed-button" id="deleteFulfilledNeedButton" value="Delete"/>
                </td>
            </tr>
            }$
        </table>
    </div>
</div>
