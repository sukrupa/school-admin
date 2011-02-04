function changeAgeRange() {
	var minVal = $('#ageFrom').val();
	$('#ageTo').val(minVal);
	$('#ageTo > option').each(function() {
		if (minVal !== "" && $(this).val() === "") {
			$(this).hide();
		} else if (parseInt($(this).val()) < parseInt(minVal)) {
			$(this).hide();
		}
		else {
			$(this).show();
		}
	});
}