$(function() {
    $(".edit-bigneed-button").click(makeRowEditable)
    $(".save-row-button").click(saveRow);
    $(".add-bigneed-button").click(addBigNeed);
    $(".delete-bigneed-button").click(deleteNeed);
});

function deleteNeed(itemID){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var params = {itemId:itemId};
    var url = "/bigneeds/delete";
    jQuery.post(url, params);
    refreshPage();
}

function saveRow(){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var itemName = $(this).parents('tr').find('.item-name').val();
    var itemCost = $(this).parents('tr').find('.item-cost').val();
    var priority = $(this).parents('tr').find('.item-priority').val();
    var params = {itemName: itemName, itemCost: itemCost, itemId:itemId, priority: priority};
    var url = "/bigneeds/saveeditedneed";
    jQuery.post(url, params);
    refreshPage();
}

function addBigNeed(){
    var priority = $(this).parents('tr').find('.item-priority').val();
    var itemName = $(this).parents('tr').find('.item-name').val();
    var itemCost = $(this).parents('tr').find('.item-cost').val();
    var params ={itemName: itemName, itemCost: itemCost, priority: priority};
    var url = "/bigneeds/create";
    jQuery.post(url,params);
    refreshPage();
}

function makeRowEditable(){
    $(this).parents("tr").addClass("editable");
}

function refreshPage() {
    try {
        window.location.reload(true);
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}

