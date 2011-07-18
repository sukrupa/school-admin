$(document).ready(function () {
    $('#save').click(function () {
        if (validateFields()) {
            $('#updateStudent').submit();
        }
    });

    $('#cancel').click(function() {
        location.href = '.';
    });
    
    $('#clearNote').click(function () {
        $('#new-note').val('');
    });

    $('#addNoteForm').submit(function () {
        var charLimit = 1000;
        var currentCharNumber = $('#new-note').val().length;

        if (currentCharNumber > charLimit) {
            $('#noteTooLongMessage').html("Character limit exceeded by " + eval(currentCharNumber - charLimit) + ".");
            return false;
        }

        else if ($('#new-note').val().trim().length == 0) {
            return false;
        }

        return true;
    });
});

function validateFields() {
    var dateStr = $('#dateOfBirth').val();
    var sponsorDateStr = $('#SponsorStartDate').val();
    var dummyTimeStr = "01:01";
    var valid = true;
    var errorMessage = "";
    var email = $('#SponsorEmail').val();
    var atpos = email.indexOf("@");
    var dotpos = email.lastIndexOf(".");


    if (dateStr === "") {
        errorMessage += "Please insert a date<br />";
        valid = false;
    } else if (!new DateValidator().validate(dateStr, dummyTimeStr, new Date())) {
        errorMessage += "Please insert a valid date of birth.<br/>";
        valid = false;
    }

    if ($('#name').val() === "") {
        errorMessage += "Please insert a valid name.";
        valid = false;
    }
    if($('#gender').val()===""){
        errorMessage += "Please select a gender.";
        valid = false;
    }
    if($("#status").val() === ""){
        errorMessage += "Please select a status.";
        valid = false;
    }
    if($("#sponsored").val() != "" || $("#SponsorEmail").val() != "" || $("#SponsorStartDate").val() != ""){
        if (!($("#sponsored").val() != "" && $("#SponsorEmail").val() != "" && $("#SponsorStartDate").val() != "")){
            errorMessage += "Please enter all three sponsor fields. <br/>";
            valid = false;
        }
        if(!new DateValidator().validate(sponsorDateStr, dummyTimeStr, new Date())) {
            errorMessage += "Please enter a valid sponsor start date.<br/>";
            valid = false;
        }
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
            errorMessage += "Please enter a valid sponsor email address.<br/>";
            valid = false;
        }
    }

    $('#errorMessages').html(errorMessage);

    return valid;
}

function limitText(limitField, charLimit) {
    if(limitField.value.length > charLimit){
        limitField.value = limitField.value.substring(0, charLimit);
    }
	
}

