function resetFields() {
    $('#title').val("");
    $('#date').val("");
    $('#time').val("");
    $('#venue').val("");
    $('#description').val("");
    $('#coordinator').val("");
    $('#attendees').val("");
    $('#notes').val("");
}

function validateMandatoryFields() {
    if ($('#title').val() === "" || $('#date').val() === "" || $('#time').val() === ""
    		|| $('#description').val() === ""  || $('#attendees').val() === "" ) {
    	$('#errorMessages').html("data should be entered in fields title,date,time,attendees and description");
	} else {
		if (verifyDateFormat()) {
        	$('#eventRecord').submit();
        }
	}
}

function verifyDateFormat(){
	return true;
}
