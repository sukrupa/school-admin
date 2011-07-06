$(function() {

    $('input[name=edit]').click(function() {
        $(this).parent().parent().addClass("editable");
    });

});

function deleteNeed(itemID){
    $('#editBigNeedsForm').attr("action", "/bigneeds/" +itemID + "/delete")
    $('#editBigNeedsForm').submit();
};

function saveEditedNeed(itemID){
    $('#editBigNeedsForm').attr("action", "/bigneeds/" +itemID + "/saveeditedneed")
    $('#editBigNeedsForm').submit();
};