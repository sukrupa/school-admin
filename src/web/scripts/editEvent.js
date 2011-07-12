$(document).ready(function () {
    $("form[name=createEventForm]").submit(function () {
        if (validateFields()) {
            return true;
        } else {
            return false;
        }
    });

    $('#clear').click(function () {
        resetFields();
    });

    $('#editEvent').submit(function () {
        if (validateFields()) {
            return true;
        } else {
            return false;
        }
    });

    $('#cancel').click(function() {
        location.href = '.';
    });

    $('#addStudents').click(function() {
        $('#availableStudents option:selected').appendTo('#chosenStudents');
        return false;
    });

    $('#removeStudents').click(function() {
        $('#chosenStudents option:selected').appendTo('#availableStudents');
        return false;
    });

    $('#clearStudents').click(function() {
        $('#chosenStudents option').each( function(){
            $(this).attr("selected", "selected")
        })
        $('#chosenStudents option:selected').appendTo('#availableStudents');
        return true;
    });

    addAttendees();

});

function addAttendees(){
    $('#attendeesList option').each( function(){
        $('#availableStudents option["*"]').appendTo('#chosenStudents');
    })
    return true;
}

function resetFields() {
    $('#title').val("");
    $('#date').val("");
    $('#startTime').val("");
    $('#endTime').val("");
    $('#startTimeAm:radio').attr('checked', true);
    $('#endTimeAm:radio').attr('checked', true);
    $('#venue').val("");
    $('#description').val("");
    $('#coordinator').val("");
    $('#attendees').val("");
    $('#notes').val("");
    $('#errorMessages').text('');
}