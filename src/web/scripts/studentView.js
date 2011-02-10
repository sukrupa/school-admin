$(document).ready(function () {
    if ($('#confirmMessage').html() === "") {
        $('#successDiv').hide();
    } else {
        $('#successDiv').fadeOut(6000);
    }
});
