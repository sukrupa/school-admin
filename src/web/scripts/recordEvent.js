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
    var validformat= /^\d{1,2}\/\d{1,2}\/\d{4}\ \d{1,2}\:\d{2}$/
		var dateStr = $('#date').val();
		var timeStr = $('#time').val();
		var dateTimeStr = dateStr + " " + timeStr;
        if (dateTimeStr.match(validformat) != dateTimeStr){
			$('#errorMessages').html("Invalid DateTime Format. Please correct and submit again.");
			return false;
		}else{
            var dayfield= $('#date').val().split("/")[0]
            var monthfield=$('#date').val().split("/")[1]
            var yearfield=$('#date').val().split("/")[2]
            var hourfield=$('#time').val().split(":")[0]
            var minutefield=$('#time').val().split(":")[1]
            var dayobj = new Date(yearfield, monthfield-1, dayfield,hourfield-1, minutefield-1)

		    if ((dayobj.getMonth()+1!=monthfield)
		            ||(dayobj.getDate()!=dayfield)
		            ||(dayobj.getFullYear()!=yearfield)
		            ||(dayobj.getHours()!=hourfield-1)
		            ||(dayobj.getMinutes()!=minutefield-1)){
			    $('#errorMessages').html("Invalid Day, Month, or Year range detected. Please correct and submit again.");
			    return false;
			}
    		else
	            return true;
	    }
}