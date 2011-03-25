var dualListBox = dualListBox || {};
var myOptions = new Array();
var anyValue = "*";
var allTalents;

function changeAgeRange() {
	var minVal = $('#ageFrom').val();
	removeAll();
	if (minVal === anyValue) {
		addAnyToAgeTo();
	} else {
		showAllAgeToOptionsFrom(minVal);
	}
	$('#ageTo').val(minVal);
}

function addAnyToAgeTo() {
	var ageTo = getAgeToOptions();
	ageTo[ageTo.length] = new Option("Any", anyValue, true, true);
}

function getAgeToOptions() {
	return $('#ageTo').attr('options');
}

function showAllAgeToOptionsFrom(minVal) {
	var maxVal = myOptions[myOptions.length - 1];
	var ageTo = getAgeToOptions();
	for (var i = minVal-1; i < maxVal; i++) {
     	ageTo[ageTo.length] = new Option(myOptions[i], myOptions[i], true, true);
	}
}

function removeAll() {
	$('#ageTo option').each(function() {
		$(this).remove();
	});
}

function initDropDowns() {
	$('#ageTo').val(anyValue);
	$('#ageFrom').val(anyValue);
	removeAll();
	addAnyToAgeTo();
}

function saveOptions() {
    $('#ageTo option').each(function() {
		myOptions[myOptions.length] = $(this).val();
	});
}

$(document).ready(function (){
	saveOptions();
	initDropDowns();
    var dualBox = dualListBox.box($('#availableTalents'),$('#chosenTalents'));
	$('#addTalent').click(dualBox.add);
	$('#removeTalent').click(dualBox.remove);
	$('#clearTalents').click(dualBox.clear);
	$('#ageFrom').change(changeAgeRange);

});

dualListBox.box = function(available,chosen) {
    var allOptions = available.find('option');
    unselectSelectedOptions(chosen);
    unselectSelectedOptions(available);

    var self = {
        add : function() {
            removeAny();
            addTalentToChosen();
            syncHiddenFieldWithChosen();

        },
        remove : function() {
            removeAny();
            unselectSelectedOptions(available);
            moveOptionsFrom(chosen).to(available);
            sortOptions(available);
            syncHiddenFieldWithChosen();
            addAnyIfChosenIsEmpty();
        },
        clear : function() {
            removeAllTalentsFromChosen();
            removeAny();
            syncHiddenFieldWithChosen();
            addAnyIfChosenIsEmpty();
        }
    };

    function syncHiddenFieldWithChosen() {
        $(".hiddenTalents").remove();
        var chosenOnes = chosen.find("option");
        for (var i=0; i<chosenOnes.size(); i++) {
             $(chosen[0].form).append("<input value=\"" + chosenOnes[i].value + "\" class=\"hiddenTalents\" name=\"talents\" type=\"hidden\" />");
        }
    }

    function addTalentToChosen() {
        unselectSelectedOptions(chosen);
        moveOptionsFrom(available).to(chosen);
        sortOptions(chosen);
    }

    function selectedOptionsIn(dropDown) {
        var selectedOptions = new Array();
        var listOptions = dropDown.find('option');
        for (var index=0;index < listOptions.size();index++) {
            var option = listOptions[index];
            if (option.selected) {
                selectedOptions.push(option);
            }
        }
        return selectedOptions;
    }

    function removeAny() {
        if(selectedOptionsIn(available).length > 0) {
            chosen.find('option[value="*"]').remove()
        }
    }

    function unselectSelectedOptions(selectBox) {
        var selectBoxOptions = selectBox.find('option');
        for (var index=0 ; index < selectBoxOptions.size() ; index++) {
            selectBoxOptions[index].selected = false;
        }
    }

    function moveOptionsFrom(oneList) {
        return {
            to : function(otherList) {
                otherList.append(selectedOptionsIn(oneList));
            }
        };
    }


    function sortOptions(selectBox) {
        var orderedTalents = getOrderedOptions(selectBox);
        selectBox.find('option').remove();
        selectBox.append(orderedTalents);
    }

    function addAnyIfChosenIsEmpty(){
        if(chosen.find('option').length === 0){
            chosen.append('<option value="*" disabled=disabled>Any</option>')
        }
    }

    function removeAllTalentsFromChosen() {
        chosen.find('option').remove();
        available.append(allOptions);

    }

    function getOrderedOptions(selectBox) {
        return selectBox.find('option').sort(function(a, b) {
            if ($(a).val()>$(b).val()) {
                return 1;
            } else if ($(a).val()==$(b).val()) {
                return 0;
            } else{
                return -1;
            }
         });
    }

    return self;
}