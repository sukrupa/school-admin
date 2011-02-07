var myOptions = new Array();

function changeAgeRange() {
	var minVal = $('#ageFrom').val();
	removeAll();
	if (minVal === "") {
		addAnyToAgeTo();
	} else {
		showAllAgeToOptionsFrom(minVal);
	}
	$('#ageTo').val(minVal);
}

function addAnyToAgeTo() {
	var ageTo = getAgeToOptions();
	ageTo[ageTo.length] = new Option("Any", "", true, true);
}

function getAgeToOptions() {
	return $('#ageTo').attr('options');
}

function showAllAgeToOptionsFrom(minVal) {
	var maxVal = myOptions[myOptions.length - 1];
	var ageTo = getAgeToOptions();
	for (var i = minVal-1; i < maxVal; i++) {
     	ageTo[ageTo.length] = new Option(myOptions[i], myOptions[i], true, true);
	}
}

function removeAll() {
	$('#ageTo option').each(function() {
		$(this).remove();
	});
}

function initDropDowns() {
	$('#ageTo').val("");
	$('#ageFrom').val("");
	removeAll();
	addAnyToAgeTo();
}

function saveOptions() {
    $('#ageTo option').each(function() {
		myOptions[myOptions.length] = $(this).val();
	});
}

$(document).ready(function (){
	saveOptions();
	initDropDowns();
});