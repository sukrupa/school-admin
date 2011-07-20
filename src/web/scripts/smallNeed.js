var editing = false;

$(function() {
    $(".edit-smallneed-button").click(makeRowEditable);
    $(".save-row-button").click(saveRow);
    $(".add-smallneed-button").click(addSmallNeed);
    $(".delete-smallneed-button").click(deleteSmallNeed);
});

function addSmallNeed() {
    submitForm($('#smallNeedsForm')[0], $(this), "/smallneeds/create");
}

function saveRow() {
    editing = false;
    submitForm($('#smallNeedsForm')[0], $(this), "/smallneeds/saveeditedneed");
}

function deleteSmallNeed(itemID) {
    if(confirm("Are you sure you want to "+$(this)[0].value +"?"))
        submitForm($('#smallNeedsForm')[0], $(this), "/smallneeds/delete");
}

function isNumber(string){
    //var number = parseFloat(string);
    return (!isNaN(parseFloat(string))) &&  /^[0-9]+(\.([0-9]+)([E]([0-9]+))?)?$/.test(string);
}


function isPriority(string){
    //var number = parseFloat(string);
    return (string != 0) &&  /^[0-9]+?$/.test(string);
}

//function isPositiveInteger(val){
//    if(val==null){return false;}
//    if(val == 0){return false;}
//    if (val.length==0){return false;}
//    for (var i = 0; i < val.length; i++) {
//        var ch = val.charAt(i);
//        if (ch < "0" || ch > "9") {
//            return false;
//        }
//    }
//}


function validateNeedsForm(form) {

    if(!isPriority(form.priority.value)){
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
    if(form.comment == ""){
        $('#error')[0].innertHTML = "Please enter a comment !!!";
    }
    form.itemCost.value = parseFloat(form.itemCost.value).toFixed(2);
    return true;
}


function submitForm(form, $, actionUrl) {
    form.itemName.value = $.parents('tr').find('.item-name').val();
    form.itemCost.value = $.parents('tr').find('.item-cost').val();
    form.priority.value = $.parents('tr').find('.item-priority').val();
    form.itemId.value = $.parents('tr').find('.item-id').val();
    form.comment.value = $.parents('tr').find('.item-comment').val();
    if(!validateNeedsForm(form)) return;
    form.action = actionUrl;
    form.submit();
}


function refreshPage() {
    try {
        location.reload();
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}


function makeRowEditable() {
    if(!editing){
        editing = true;
        $(this).parents("tr").find('.delete-smallneed-button')[0].value = "Cancel";
        $(this).parents("tr").find('.delete-smallneed-button').click(refreshPage);
        $(this).parents("tr").addClass("editable");
    } else{
        alert("Please save the current row, before editing !!!");
    }
}

