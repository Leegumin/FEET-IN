/* eslint-disable */
function home() {

//     // * init Isotope
//     var $grid = $('.collection-list').isotope({
//         // options
//     });
// // filter items on button click
//     $('.filter-button-group').on('click', 'button', function () {
//         var filterValue = $(this).attr('data-filter');
//         resetFilterBtns();
//         $(this).addClass('active-filter-btn');
//         $grid.isotope({filter: filterValue});
//     });
//
//     var filterBtns = $('.filter-button-group').find('button');
//
//     function resetFilterBtns() {
//         filterBtns.each(function () {
//             $(this).removeClass('active-filter-btn');
//         });
//     }

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 2000,
        delay: 20
    })

    sr.reveal(`#carouselExampleCaptions`)
    sr.reveal(`.first-cap`,{delay:100, origin:'bottom'})
}

export default home;
