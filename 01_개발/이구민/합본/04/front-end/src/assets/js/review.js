/* eslint-disable */
function review() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.review-title`)
    sr.reveal(`.review-container`, {delay: 100, distance: '20px', origin: 'bottom'})


    // * toggle content
    $(document).ready( function() {
        $( '.review-content-title' ).click( function() {
            $(this).siblings( '.review-content' ).toggle();
        });
    });

}

export default review;
