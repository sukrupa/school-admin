$(document).ready(function () {
    $('#createEvent').submit(function () {
        if (validateFields()) {
            return true;
        } else {
            return false;
        }
    });

    $('#clear').click(function () {
        resetFields();
    });
});

function validateFields() {
    var valid = true;
    var errorMessage = "";
    var dateStr = $('#date').val();
    var dummyTimeStr = "01:01";

    if (!new DateValidator().validate(dateStr, dummyTimeStr)) {
        $('#dateErrorMessage').html("Invalid date.  Format: dd-mm-yyyy.");
        errorMessage += "Please insert a valid date (Format: dd-mm-yyyy).<br/>";
        valid = false;
    }

    if ($('#title').val() === "" || $('#description').val() === "" || $('#date').val() === "" || $('#attendees').val() === "") {
        errorMessage += "Please fill in all required fields.";
        valid = false;
    }

    $('#errorMessages').html(errorMessage);

    return valid;
}

function resetFields() {
    $('#title').val("");
    $('#date').val("");
    $('#time').val("");
    $('#venue').val("");
    $('#description').val("");
    $('#coordinator').val("");
    $('#attendees').val("");
    $('#notes').val("");
    $('#errorMessages').text('');
}
