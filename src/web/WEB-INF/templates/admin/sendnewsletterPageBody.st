
<form id="sendnewsletter" name="sendnewsletter" action="/admin/sendnewsletteremail" method="POST" enctype="multipart/form-data" >
    <div>To:</div>
    <input type="text" id="to" name="to" />
    <div>Subject:</div>
    <input type="text" id="subject" name="subject" />
    <div>Comments:</div>
    <textarea name="comments" id="comments" rows="5" cols="30"></textarea>
    <div><input type="file"  id="attach" name="attach" value="Browse" onchange="javascript:copylink();"/></div>
    <div><input type ="text" id="fakebox" name="fakebox" /> </div>
    <div><input type="submit" id="send" name="send" value="Send"/></div>

</form>
