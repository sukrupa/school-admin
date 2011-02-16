DateValidator = function (){};

DateValidator.prototype.validate = function validate(dateStr, timeStr){
    var validformat= /^\d{1,2}-\d{1,2}-\d{4}\ \d{1,2}\:\d{2}$/

	var dateTimeStr = dateStr + " " + timeStr;
	if (!dateTimeStr.match(validformat)){
		return false;
	}else{
		var dayfield= dateStr.split("-")[0]
		var monthfield=dateStr.split("-")[1]
		var yearfield=dateStr.split("-")[2]
		var hourfield=timeStr.split(":")[0]
		var minutefield=timeStr.split(":")[1]
		var dayobj = new Date(yearfield, monthfield-1, dayfield,hourfield-1, minutefield-1)

		if ((dayobj.getMonth()+1!=monthfield)
				||(dayobj.getDate()!=dayfield)
				||(dayobj.getFullYear()!=yearfield)
				||(dayobj.getHours()!=hourfield-1)
				||(dayobj.getMinutes()!=minutefield-1)){
			return false;
		}
		else
			return true;
	}
}