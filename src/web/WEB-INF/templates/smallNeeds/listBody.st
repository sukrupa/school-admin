<div>
    <div class="page">
        <table border="0">
            <thead class="header">
            <tr>
                <td><label for="itemName">Item</label></td>
                <td><label for="cost">Cost</label></td>
                <td><label for="comment">Comment</label></td>
                <td>Actions</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <form name="createBigNeedsForm" method="post" action="/bigneeds/create">
                    <td><input type="text" id="itemName" name="itemName"/></td>
                    <td><input type="text" id="cost" name="cost"/></td>
                    <td><input type="text" id="comment" name="itemName"/></td>
                    <td><input type="submit" name="add" value="Add"/></td>
                </form>
            </tr>
         </table>
</div>
