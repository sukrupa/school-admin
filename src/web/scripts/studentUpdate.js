function validateDate() {
	var dateStr = $('#dateOfBirth').val();
	var dummyTimeStr = "01:01";

	if (!new DateValidator().validate(dateStr, dummyTimeStr)) {
        $('#dateErrorMessage').html("Invalid date.  Format: dd/mm/yyyy");
	} else {
		$('#dateErrorMessage').html("");
	}
}