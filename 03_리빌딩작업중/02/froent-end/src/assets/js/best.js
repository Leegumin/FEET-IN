/* eslint-disable */
function sale() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.best-title`)
    sr.reveal(`.best-container`, {delay: 100, distance: '20px', origin: 'bottom'})
}

export default sale;
