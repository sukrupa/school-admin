$(function() {

    $('input[name=edit]').click(function() {
        $(this).parent().parent().addClass("editable");
    });
    
    $(".save-row-button").click(saveRow);
});

function deleteNeed(itemID){
    $('#editBigNeedsForm').attr("action", "/bigneeds/" +itemID + "/delete")
    $('#editBigNeedsForm').submit();
}

function saveRow(){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var itemName = $(this).parents('tr').find('.item-name').val();
    var itemCost = $(this).parents('tr').find('.item-cost').val();
    var params = {itemName: itemName, itemCost: itemCost, itemId:itemId};

    var url = "/bigneeds/saveeditedneed";

    jQuery.post(url, params);

    //todo mike / tim must fix 
    window.location.href = "http://localhost:8080/bigneeds";
    

}