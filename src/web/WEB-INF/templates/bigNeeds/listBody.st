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
                <form name="bigNeeds" method="post" action=".">
                    <td><input type="text" name="item" /></td>
                    <td><input type="text" name="cost" /></td>
                    <td><input type="button" name="add" value="Add" /></td>
                </form>
            </tr>
            $needs:{
            <tr>
                <td>$it.item$</td>
                <td>$it.cost$</td>
                <td><input type="button" name="edit" value="Edit" /></td>
                <td><input type="button" name="del" value="Delete" /></td>
            </tr>

            }$

            </tbody>
        </table>
    </div>

</div>
