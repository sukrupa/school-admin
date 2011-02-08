var currentPage = 1;
var numberOfPages;


$(window).load( function() {
    numberOfPages = $('#studentListPages div.page').size();
    setup();

    $('#showPage1').click(function() {
        $('#studentListPages div.page').each(function() {
            $(this).hide();
        });
        $('#studentListPages div.page:nth-child(1)').show();
    });

    $('#nextButton').click(function() {
        if(currentPage < numberOfPages) {
            currentPage++;
            showCurrentPage();
        }
    });

    $('#previousButton').click(function() {
        if(currentPage > 1) {
            currentPage--;
            showCurrentPage();
        }
    });
});


function setup() {
    showCurrentPage();
}

function showCurrentPage() {
    $('#studentListPages div.page').each(function() {
        $(this).hide();
    });
    $('#studentListPages div.page:nth-child('+currentPage+')').show();
}
