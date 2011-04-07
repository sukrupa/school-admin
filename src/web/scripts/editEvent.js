$(document).ready(function () {
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

});

