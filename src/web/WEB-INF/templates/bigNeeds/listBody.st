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
                <form name="createBigNeed" method="post" action="/bigneeds/create">
                    <td><input type="text" id="itemName" name="itemName" /></td>
                    <td><input type="text" id="cost" name="cost" /></td>
                    <td><input type="submit" name="add" value="Add" /></td>
                </form>
            </tr>

            $bigNeedList:{
            <tr>
                <td>$it.itemName$</td>
                <td>$it.cost$</td>
                <td><input type="button" name="edit" value="Edit" /></td>
                <td>
                    <form action="/bigneeds/$it.id$/delete" method="post">
                        <input type="submit" name="del" value="Delete" />
                    </form>
                </td>
            </tr>


            }$
            </tbody>
        </table>
        <div id="msg">$message$ $bigneed.itemName$</div>
    </div>

</div>
