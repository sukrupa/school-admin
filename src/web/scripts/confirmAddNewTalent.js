$(document).ready(function () {

$("#addNewTalent").click(function(event)
{
    var result = confirm("You are about to add the talent, " + $('#description').val() +", are you sure?");
    if (result === false)
    {
        event.preventDefault()
    }
});
});