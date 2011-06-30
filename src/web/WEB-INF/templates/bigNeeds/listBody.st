<div>
    <div class="page">
        <table border="0">
            <thead class="header">
            <tr>
                <td>Item</td>
                <td>Cost</td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form name="createBigNeed" method="post" action="/bigneeds/create">
                    <td><input type="text" name="itemName" /></td>
                    <td><input type="text" name="cost" /></td>
                    <td><input type="submit" name="add" value="Add" /></td>
                </form>
            </tr>

            $bigNeedList:{
            <tr>
                <td>$it.itemName$</td>
                <td>$it.cost$</td>
                <td><input type="button" name="edit" value="Edit" /></td>
                <td><input type="button" name="del" value="Delete" /></td>
            </tr>


            }$
            </tbody>
        </table>
    </div>

</div>
