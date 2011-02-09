
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

function validateFields() {
     if(validMandatoryFields()){
     	if (validDate()) {
        	$('#eventRecord').submit();
        }
	 }
}

function validMandatoryFields(){
    if ($('#title').val() === ""
            || $('#date').val() === ""
    		|| $('#description').val() === ""
    		|| $('#attendees').val() === "" ) {
    		$('#errorMessages').html('Data should be entered in fields title, date, time, attendees and description');
    		return false;
    }
    return true;
}


function validDate(){
    var validformat= /^\d{1,2}\/\d{1,2}\/\d{4}\ \d{1,2}\:\d{2}$/
		var dateStr = $('#date').val();
		var timeStr = $('#time').val();
		var dateTimeStr = dateStr + " " + timeStr;
        if (dateTimeStr.match(validformat) != dateTimeStr){
			$('#errorMessages').html("Invalid DateTime Format. Please correct and submit again.");
			return false;
		}else{
            var dayfield= dateStr.split("/")[0]
            var monthfield=dateStr.split("/")[1]
            var yearfield=dateStr.split("/")[2]
            var hourfield=timeStr.split(":")[0]
            var minutefield=timeStr.split(":")[1]
            var dayobj = new Date(yearfield, monthfield-1, dayfield,hourfield, minutefield)

		    if ((dayobj.getMonth()+1!=monthfield)
		            ||(dayobj.getDate()!=dayfield)
		            ||(dayobj.getFullYear()!=yearfield)){
			    $('#errorMessages').html("Invalid Day, Month, Year detected. Please correct and submit again.");
			    return false;
			}else if((dayobj.getHours()!=hourfield)
		            ||(dayobj.getMinutes()!=minutefield)){
		        $('#errorMessages').html("Invalid Hour or minutes detected. Please correct and submit again.");
			    return false;
		    }else
	            return true;
	    }
}
