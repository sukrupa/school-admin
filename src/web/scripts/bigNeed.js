var editing = false;
var itemFulfilled = false;

$(function() {
    $(".edit-bigneed-button").click(makeRowEditable);
    $(".save-row-button").click(saveRow);
    $(".add-bigneed-button").click(addBigNeed);
    $(".cancel-row-edit").click(refreshPage);
    $(".delete-bigneed-button").click(deleteNeed);
    $(".delete-fulfilled-bigneed-button").click(deleteFulfilledNeed);
});

function refreshPage() {
    try {
        location.href = "./bigneeds";
    } catch (Exception) {
        alert("Page Refresh Failed")
    }
}

function deleteFulfilledNeed(itemID) {
    if (confirm("Are you sure you want to " + $(this)[0].value + "?")) {
       submitFulfilledForm($('#bigNeedsForm')[0], $(this), "/bigneeds/delete");
    }

}

function submitFulfilledForm(form, $, actionUrl) {
    form.itemName.value = $.parents('tr').find('.item-name').val();
    form.itemCost.value = $.parents('tr').find('.item-cost').val();
    form.priority.value = $.parents('tr').find('.item-priority').val();
    form.itemId.value = $.parents('tr').find('.item-id').val();
    form.donatedAmount.value = $.parents('tr').find('.item-donatedAmount').val();
    form.action = actionUrl;
    form.submit();
}

function deleteNeed(itemID) {
    if (confirm("Are you sure you want to " + $(this)[0].value + "?"))
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
    if (!validateNeedsForm(form)) return;
    form.action = actionUrl;
    form.submit();
}

function isNumber(string) {
    return (!isNaN(parseFloat(string))) && /^[0-9]+(\.([0-9]+)([E]([0-9]+))?)?$/.test(string);
}


function isPriority(string) {
    return (string != 0) && /^[0-9]+?$/.test(string);
}


function validateNeedsForm(form) {
    if (!isPriority(form.priority.value)) {
        $('#error')[0].innerHTML = "Please enter a valid priority  !!!";
        return false;
    }
    if (form.itemName.value == "") {
        $('#error')[0].innerHTML = "Please enter an Item Name  !!!";
        return false;
    }
    if (!isNumber(form.itemCost.value)) {
        $('#error')[0].innerHTML = "Please enter a valid Cost !!!";
        return false;
    }
    if (!isNumber(form.donatedAmount.value)) {
        $('#error')[0].innerHTML = "Please enter a valid Donated Amount !!!";
        return false;
    }
    if (parseFloat(form.donatedAmount.value) > parseFloat(form.itemCost.value)) {
        $('#error')[0].innerHTML = "Donated Amount cannot exceed Cost !!!";
        return false;
    }
    form.itemCost.value = parseFloat(form.itemCost.value).toFixed(2);
    form.donatedAmount.value = parseFloat(form.donatedAmount.value).toFixed(2);
    return true;
}

function makeRowEditable() {
    if (!editing) {
        editing = true;
        $(this).parents("tr").addClass("editable");
    } else {
        alert("Please save the current row, before editing !!!");
    }
}





