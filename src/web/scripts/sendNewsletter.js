function copylink() {

    (document.forms[0].fakebox.value) = document.sendnewsletter.attach.value;

}

function validateForm() {
    var x = document.forms[0].to.value;
    var atpos = x.indexOf("@");
    var dotpos = x.lastIndexOf(".");
    if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length) {
        alert("Not a valid e-mail address");
        return false;
    }
}




