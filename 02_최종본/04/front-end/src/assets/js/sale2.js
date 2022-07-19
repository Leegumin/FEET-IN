/* eslint-disable */
function sale2() {

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

export default sale2;
