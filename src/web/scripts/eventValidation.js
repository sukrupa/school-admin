
function validateFields() {
    var errorMessage = "";
    var dateStr = $.trim($('#date').val());
    var timeStr = $.trim($('#time').val());

    clearErrorMessages();
	var dateValid = validateDate(dateStr);
	var timeValid = validateTime(timeStr);

    var valid = dateStr !== "" && timeValid && dateValid;

    if (!validateMandatoryFields($('#title').val(), $('#description').val(), dateStr)) {
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

function validateMandatoryFields(title, description, date) {
	if ($.trim(title) === "" || $.trim(description) === "" || date === "") {
		addErrorMessage("Please fill in all required fields.");
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