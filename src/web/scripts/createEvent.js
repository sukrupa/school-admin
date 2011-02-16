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
    var dateStr = $.trim($('#date').val());
    var timeStr = $.trim($('#time').val());
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

    if ($.trim($('#title').val()) === "" || $.trim($('#description').val()) === "" || dateStr === "" || $.trim($('#attendees').val()) === "") {
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
