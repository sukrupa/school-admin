$(function() {
    $(".edit-bigneed-button").click(makeRowEditable)
    $(".save-row-button").click(saveRow);
    $(".add-bigneed-button").click(addBigNeed);
    $(".delete-bigneed-button").click(deleteBigNeed);
    $(".delete-smallneed-button").click(deleteSmallNeed);

});

function deleteBigNeed(itemID){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var params = {itemId:itemId};
    var url = "/bigneeds/delete";
    jQuery.post(url, params, function(data){
        refreshPage();
    });
}

function deleteSmallNeed(){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var params = {itemId:itemId};
    var url = "/smallneeds/delete";
    jQuery.post(url, params, function(data){
        refreshPage();
    });
}

function saveRow(){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var itemName = $(this).parents('tr').find('.item-name').val();
    var itemCost = $(this).parents('tr').find('.item-cost').val();
    var priority = $(this).parents('tr').find('.item-priority').val();
    var params = {itemName: itemName, itemCost: itemCost, itemId:itemId, priority: priority};
    var url = "/bigneeds/saveeditedneed";
    jQuery.post(url, params, function(data){
        refreshPage();
    });
}

function addBigNeed(){
    var priority = $(this).parents('tr').find('.item-priority').val();
    var itemName = $(this).parents('tr').find('.item-name').val();
    var itemCost = $(this).parents('tr').find('.item-cost').val();
    var params ={itemName: itemName, itemCost: itemCost, priority: priority};
    var url = "/bigneeds/create";
    jQuery.post(url,params, function(data){
        refreshPage();
    });
}

function makeRowEditable(){
    $(this).parents("tr").addClass("editable");
}

function refreshPage() {
    try {
        location.reload();
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}

