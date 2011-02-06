

function changeAgeRange() {
	var minVal = $('#ageFrom').val();
	$('#ageTo').val(minVal);
	if (minVal === "") {
	    hideAllExpectAnyAgeTo();
	} else {
		hideAllLesserThanAgeFrom(minVal);
	}
}

function hideAllLesserThanAgeFrom(minVal) {
	$('#ageTo > option').each(function() {
		if ($(this).val() === "" || parseInt($(this).val()) < parseInt(minVal)) {
			$(this).hide();
		} else {
			$(this).show();
		}
	});
}

function hideAllExpectAnyAgeTo() {
	$('#ageTo > option').each(function() {
		if ($(this).val() !== "") {
			$(this).hide();
		} else {
			$(this).show();
		}
	});
}

$(document).ready(function (){
	$('#ageTo').val("");
	$('#ageFrom').val("");
	hideAllExpectAnyAgeTo();
});