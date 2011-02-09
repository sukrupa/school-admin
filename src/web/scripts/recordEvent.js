
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
    var validDateformat= /^\d{1,2}\/\d{1,2}\/\d{4}$/
    var validTimeformat= /^\d{1,2}\:\d{2}$/
	var dateStr = $('#date').val();
	var timeStr = $('#time').val();
	if (dateStr.match(validDateformat) != dateStr){
		$('#errorMessages').html("Invalid Date Format. Please correct and submit again.");
		return false;
	}else{
        var dayfield= dateStr.split("/")[0]
        var monthfield=dateStr.split("/")[1]
        var yearfield=dateStr.split("/")[2]
        var hourfield=timeStr.split(":")[0]
        var minutefield=timeStr.split(":")[1]
        var dayobj;
        var timeSet;

        if (timeStr.length > 3){
            if(hourfield > 23 || minutefield > 59) {
                $('#errorMessages').html("Invalid Hour or minutes detected. Please correct and submit again.");
                return false;
            }
            dayobj = new Date(yearfield, monthfield-1, dayfield, hourfield, minutefield);
            alert(dayobj)
        }else {
            dayobj = new Date(yearfield, monthfield-1, dayfield, 0, 0);
        }
        if ((dayobj.getMonth()+1!=monthfield)
		      ||(dayobj.getDate()!=dayfield)
		      ||(dayobj.getFullYear()!=yearfield)){
		    $('#errorMessages').html("Invalid Day, Month, Year detected. Please correct and submit again.");
		    return false;
		}
		else{
	         return true;
	    }
	}
}
