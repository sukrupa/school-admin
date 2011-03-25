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

dualListBox.box = function(src,des) {
    var allOptions = src.find('option');

    var self = {
        add : function() {
            addTalentToChosen();
            syncHiddenFieldWithChosen();
        },
        remove : function() {
            removeTalentFromChosen();
            syncHiddenFieldWithChosen();
        },
        clear : function() {
            removeAllTalentsFromChosen();
            syncHiddenFieldWithChosen();
        }
    };

    function syncHiddenFieldWithChosen() {
        $("#hiddenChosenOnes").html("");
        var chosenOnes = des.find("option");
        for (var i=0; i<chosenOnes.size(); i++) {
             $("#hiddenChosenOnes").append("<input value=\"" + chosenOnes[i].value + "\" name=\"talents\" type=\"hidden\" />");
        }
    }

    function addTalentToChosen() {
        removeAny();
        unselectChosenTalents();
        moveOptionsFrom(src).to(des);
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
        if(selectedOptionsIn(src).length > 0) {
            des.find('option[value="*"]').remove()
        }
    }

    function unselectChosenTalents() {
        var chosenTalentsOptions = des.find('option');
        for (var index=0 ; index < chosenTalentsOptions.size() ; index++) {
            chosenTalentsOptions[index].selected = false;
        }
    }

    function moveOptionsFrom(oneList) {
        return {
            to : function(otherList) {
                otherList.append(selectedOptionsIn(oneList));
            }
        };
    }

    function removeTalentFromChosen() {
        moveOptionsFrom(des).to(src);

        var orderedTalents = getOrderedOptions();
        src.find('option').remove();
        src.append(orderedTalents);

        addAnyIfChosenIsEmpty();
    }

    function addAnyIfChosenIsEmpty(){
        if(des.find('option').length === 0){
            des.append("<option disabled=disabled>Any</option>")
        }
    }

    function removeAllTalentsFromChosen() {
        des.find('option').remove();
        src.append(allOptions);
        addAnyIfChosenIsEmpty();
    }

    function getOrderedOptions() {
        return src.find('option').sort(function(a, b) {
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