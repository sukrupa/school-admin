DateValidator = function (){};

DateValidator.prototype.validate = function validate(dateStr, timeStr){
	return this.isBefore(dateStr, timeStr);
}

DateValidator.prototype.isBefore = function isBefore(dateStr, timeStr, comparisonDate){
    var validformat= /^\d{1,2}-\d{1,2}-\d{4}\ \d{1,2}\:\d{2}$/

	var dateTimeStr = dateStr + " " + timeStr;
	if (!dateTimeStr.match(validformat)){
		return false;
	}else{
		var dayfield= dateStr.split("-")[0];
		var monthfield=dateStr.split("-")[1] - 1;
		var yearfield=dateStr.split("-")[2];
		var hourfield=timeStr.split(":")[0];
		var minutefield=timeStr.split(":")[1];

		var dayobj = new Date(yearfield, monthfield, dayfield, hourfield, minutefield);

		if ((dayobj.getMonth() != monthfield)
				||(dayobj.getDate() != dayfield)
				||(dayobj.getFullYear() != yearfield)
				||(dayobj.getHours() != hourfield)
				||(dayobj.getMinutes() != minutefield)){
			return false;
		} else if (comparisonDate) {
			return dayobj <= comparisonDate;
		} else {
			return true;
		}
	}
}
