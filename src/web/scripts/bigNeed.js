var editing = false;
var itemFulfilled = false;

$(function() {
    $(".edit-bigneed-button").click(makeRowEditable);
    $(".save-row-button").click(saveRow);
    $(".add-bigneed-button").click(addBigNeed);
    $(".delete-bigneed-button").click(deleteNeed);
});

//Needs Refactoring
//function deleteSmallNeed(){
//    var itemId = $(this).parents('tr').find('.item-id').val();
//    var params = {itemId:itemId};
//    var url = "/smallneeds/delete";
//    jQuery.post(url, params, function(data){
//        refreshPage();
//    });
//}
//
function refreshPage() {
    try {
        location.reload();
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}
//End of "Needs Refactoring"


function deleteNeed(itemID) {
    if(confirm("Are you sure you want to "+$(this)[0].value +"?"))
        submitForm($('#bigNeedsForm')[0], $(this), "/bigneeds/delete");
}

function saveRow() {
    editing = false;
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
    form.donatedAmount.value = $.parents('tr').find('.item-donatedAmount').val();
    if(!validateNeedsForm(form)) return;
    form.action = actionUrl;
    form.submit();
}

function isNumber(string){
    return (!isNaN(parseFloat(string))) &&  /^[0-9]+(\.([0-9]+)([E]([0-9]+))?)?$/.test(string);
}


function isPriority(string){
    //var number = parseFloat(string);
    return (string != 0) &&  /^[0-9]+?$/.test(string);
}


function validateNeedsForm(form) {
    if(!isPriority(form.priority.value)){
    if(!isNumber(form.priority.value)){
        $('#error')[0].innerHTML = "Please enter a valid priority  !!!";
        return false;
    }
    if(form.itemName.value == ""){
        $('#error')[0].innerHTML = "Please enter an Item Name  !!!";
        return false;
    }
    if(!isNumber(form.itemCost.value)){
        $('#error')[0].innerHTML = "Please enter a valid Cost !!!";
        return false;
    }
    if(!isNumber(form.donatedAmount.value)){
        $('#error')[0].innerHTML = "Please enter a valid Donated Amount !!!";
        return false;
    }
    if(parseFloat(form.donatedAmount.value) > parseFloat(form.itemCost.value)){
        $('#error')[0].innerHTML = "Donated Amount cannot exceed Cost !!!";
        return false;
    }
    form.itemCost.value = parseFloat(form.itemCost.value).toFixed(2);
    form.donatedAmount.value = parseFloat(form.donatedAmount.value).toFixed(2);
    return true;
}

function makeRowEditable() {
    if(!editing){
        editing = true;
        $(this).parents("tr").find('.delete-bigneed-button')[0].value = "Cancel";
        $(this).parents("tr").find('.delete-bigneed-button').click(refreshPage);
        $(this).parents("tr").addClass("editable");
    } else{
        alert("Please save the current row, before editing !!!");
    }
}





