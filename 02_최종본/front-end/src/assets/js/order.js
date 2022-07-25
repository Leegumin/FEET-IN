/* eslint-disable */
function order() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.order-container`, {distance: '20px', origin: 'bottom'})

}

export default order;
