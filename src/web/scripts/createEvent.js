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
    var timeStr = $('#time').val();
    var dummyTimeStr = "01:01";
    var dummyDateStr = "01-01-2001"

    if (!new DateValidator().validate(dateStr, dummyTimeStr)) {
        errorMessage += "Invalid date.<br/>";
        valid = false;
    }

    if ( !(timeStr === "") && !new DateValidator().validate(dummyDateStr, timeStr)) {
        errorMessage += "Invalid time.<br/>";
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
