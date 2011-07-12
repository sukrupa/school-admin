$(function() {
    $(".edit-bigneed-button").click(makeRowEditable);
    $(".save-row-button").click(saveRow);
    $(".add-bigneed-button").click(addBigNeed);
    $(".delete-bigneed-button").click(deleteNeed);
    $(".delete-smallneed-button").click(deleteSmallNeed);

});

//Needs Refactoring
function deleteSmallNeed(){
    var itemId = $(this).parents('tr').find('.item-id').val();
    var params = {itemId:itemId};
    var url = "/smallneeds/delete";
    jQuery.post(url, params, function(data){
        refreshPage();
    });
}

function refreshPage() {
    try {
        location.reload();
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}
//End of "Needs Refactoring"

function deleteNeed(itemID) {
    submitForm($('#bigNeedsForm')[0], $(this), "/bigneeds/delete");
}

function saveRow() {
    submitForm($('#bigNeedsForm')[0], $(this), "/bigneeds/saveeditedneed");
}

function addBigNeed() {
    submitForm($('#bigNeedsForm')[0], $(this), "/bigneeds/create");
}

function submitForm(form, $, actionUrl) {
    form.itemName.value = $.parents('tr').find('.item-name').val();
    form.itemCost.value = $.parents('tr').find('.item-cost').val();
    form.priority.value = $.parents('tr').find('.item-priority').val();
    form.itemId.value = $.parents('tr').find('.item-id').val();
    form.action = actionUrl;
    form.submit();
}

function makeRowEditable() {
    $(this).parents("tr").addClass("editable");
}


