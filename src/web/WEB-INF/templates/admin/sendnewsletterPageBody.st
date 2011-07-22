<form id="sendnewsletter" name="sendnewsletter" action="/admin/sendnewsletteremail" onsubmit="return validateForm()" method="POST" enctype="multipart/form-data" >
    <div><label for="to">To</label></div>
    <input type="text" id="to" name="to" value="$toEmailAddress$" />
    <div><label for="bcc">Bcc:</label></div>
    <input type="text" id="bcc" name="bcc" value="$bccList$"/>
    <div><label for="subject">Subject:</label></div>
    <input type="text" id="subject" name="subject" value="Sukrupa Monthly Newsletter" />
    <div><label for="comments">Comments:</label></div>
    <textarea name="comments" id="comments" rows="5" cols="30"></textarea>
    <div><input type="file"  id="attach" name="attach" value="Browse" onchange="javascript:copylink();"/></div>
    <div><input type ="text" id="fakebox" name="fakebox" />
    <input type="button" value="Remove" onclick="javascript:remove();"/></div>
    <div><input type="submit" id="send" name="send" value="Send"/></div>
</form>
