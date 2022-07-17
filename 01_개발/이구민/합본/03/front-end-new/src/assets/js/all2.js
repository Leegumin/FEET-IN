/* eslint-disable */
function all2() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.all-title`)
    sr.reveal(`.all-container`, {delay: 100, distance: '20px', origin: 'bottom'})
}

export default all2;
