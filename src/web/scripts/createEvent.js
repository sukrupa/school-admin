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


});

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