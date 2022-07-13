/* eslint-disable */
function sale() {

    // * init Isotope1(category)
    var $grid = $('.collection-list').isotope({
        // options
    });
// filter items on button click
    $('.filter-button-group').on('click', 'button', function () {
        var filterValue = $(this).attr('data-filter');
        resetFilterBtns();
        $(this).addClass('active-filter-btn');
        $grid.isotope({filter: filterValue});
    });

    var filterBtns = $('.filter-button-group').find('button');

    function resetFilterBtns() {
        filterBtns.each(function () {
            $(this).removeClass('active-filter-btn');
        });
    }

    // * init Isotope2(color)
    var $grid = $('.collection-list').isotope({
        // options
    });
// filter items on button click
    $('.filter-button-group2').on('click', 'button', function () {
        var filterValue = $(this).attr('data-filter');
        resetFilterBtns2();
        $grid.isotope({filter: filterValue});
    });

    var filterBtns2 = $('.filter-button-group').find('button');
    var filterBtns3 = $('.filter-button-group').find('#all');

    function resetFilterBtns2() {
        filterBtns2.each(function () {
            $(this).removeClass('active-filter-btn');
        });
        filterBtns3.addClass('active-filter-btn');
    }

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.sale-title`)
    sr.reveal(`.sale-container`, {delay: 100, distance: '20px', origin: 'bottom'})
}

export default sale;
