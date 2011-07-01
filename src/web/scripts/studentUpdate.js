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
    var dummyTimeStr = "01:01";
    var valid = true;
    var errorMessage = "";

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
    $('#errorMessages').html(errorMessage);

    return valid;
}

function limitText(limitField, charLimit) {
    if(limitField.value.length > charLimit){
        limitField.value = limitField.value.substring(0, charLimit);
    }
	
}

