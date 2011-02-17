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

    clearErrorMessages();
	var dateValid = validateDate(dateStr);
	var timeValid = validateTime(timeStr);

	if (dateStr !== "" && timeValid && dateValid) {
		if (!isBeforeCurrentDate(dateStr, timeStr)) {
			valid = false;
		}
	} else {
		valid = false;
	}

    if (!validateMandatoryFields($('#title').val(), $('#description').val(), dateStr, $('#attendees').val())) {
        valid = false;
    }

    return valid;
}

function clearErrorMessages() {
	$('#errorMessages').html("");
}

function addErrorMessage(message) {
    $('#errorMessages').html($('#errorMessages').html() + message);
}

function validateMandatoryFields(title, description, date, attendees) {
	if ($.trim(title) === "" || $.trim(description) === "" || date === "" || $.trim(attendees) === "") {
		addErrorMessage("Please fill in all required fields.");
        return false;
	}
	return true;
}

function isBeforeCurrentDate(dateStr, timeStr) {
	if (timeStr === "") {
		timeStr = "00:00";
	}
    if (!new DateValidator().validate(dateStr, timeStr, new Date())) {
        addErrorMessage("You can only record past events.<br/>");
        return false;
    }
    return true;
}

function validateDate(dateStr) {
    var dummyTimeStr = "00:00";
    if (dateStr !== "" && !new DateValidator().validate(dateStr, dummyTimeStr)) {
		addErrorMessage("Invalid date.<br/>");
        return false;
    }
    return true;
}

function validateTime(timeStr) {
    var dummyDateStr = "01-01-2000";
    if (!(timeStr === "") && !new DateValidator().validate(dummyDateStr, timeStr)) {
        addErrorMessage("Invalid time.<br/>");
        return false;
    }
    return true;
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
