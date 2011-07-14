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
        $('#availableStudents option:selected').appendTo('#attendingStudents');
        return true;
    });

    $('#save').click(function() {
        $('#attendingStudents option').each( function(){
            $(this).attr("selected", "selected")
        })
        $('#editEvent').submit();
        return true;
    });

    $('#removeStudents').click(function() {
        $('#attendingStudents option:selected').appendTo('#availableStudents');
        return true;
    });

    $('#clearStudents').click(function() {
        $('#attendingStudents option').each( function(){
            $(this).attr("selected", "selected")
        })
        $('#attendingStudents option:selected').appendTo('#availableStudents');
        return true;
    });

    addAttendeesToForm();
});

function addAttendeesToForm(){
    $('#attendeesList option').each( function(){
        $('#availableStudents option["*"]').appendTo('#attendingStudents');
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