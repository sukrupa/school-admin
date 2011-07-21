<div class="subscriberList">
    <!-- <div id= "susbcriberList">-->
    <div class="page">

     <table border="0">
            <thead class="header">
                    <tr>
                        <td>Name</td>
                        <td>Email</td>
                        <td>Action</td>
                    </tr>
           </thead>
             $subscriberList:{
                <tr>
                    <td>$it.subscriberName$</td>
                    <td>$it.subscriberEmail$</td>
                    <td><a href = "deletesubscriber?subid=$it.id$" onclick="return validateDelete()">Delete</a></td>
                </tr>
            }$
      </table>
     </div>
</div>