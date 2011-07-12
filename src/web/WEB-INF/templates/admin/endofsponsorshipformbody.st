<body onload="getParam('sponsor_email');"></body>
<form id="sendendofsponsorship" name="sendendofsponsorship" action="/admin/endofsponsorshipmailsentPage" onsubmit="return validateForm()"  method="post" >
    <div>
        <div><label for="to">To:</label></div>
        <input type="text" id="to" name="to" />
    </div>
    <div>
        <div><label for="subject">Subject:</label></div>
        <input type="text" id="subject" name="subject" value="Sukrupa sponsorship is expiring soon"/>
    </div>
    <br/><br/>
    <label for="message">Message:</label>
    <br/>
    <textarea name="message" id="message" rows="5" cols="30">Thanks for sponsoring.</textarea>
    <br><br/>
    <div><input type="submit" id="send" name="send" value="Send" /></div>
</form>