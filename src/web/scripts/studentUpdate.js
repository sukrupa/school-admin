$(document).ready(function () {
    $('#dateOfBirth').keyup(function () {
        var dateStr = $('#dateOfBirth').val();
        var dummyTimeStr = "01:01";

        if (!new DateValidator().validate(dateStr, dummyTimeStr)) {
            $('#dateErrorMessage').html("Invalid date.  Format: dd/mm/yyyy");
        } else {
            $('#dateErrorMessage').html("");
        }
    });

    $('#save').click(function () {
        if (validateFields()) {
            $('#updateStudent').submit();
        }
    });
});


function validateFields() {
    var dateStr = $('#dateOfBirth').val();
    var dummyTimeStr = "01:01";
    var valid = true;
    var errorMessage = "";

    if (dateStr === "") {
        errorMessage += "Please insert a date<br />";
        valid = false;
    } else if (!new DateValidator().validate(dateStr, dummyTimeStr)) {
        $('#dateErrorMessage').html("Invalid date.  Format: dd/mm/yyyy.");
        errorMessage += "Please insert a valid date (Format: dd/mm/yyyy).<br/>";
        valid = false;
    }
    if ($('#name').val() === "") {
        errorMessage += "Please insert a valid name.";
        valid = false;
    }
    $('#errorMessages').html(errorMessage);

    return valid;
}