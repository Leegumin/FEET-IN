/* eslint-disable */
function signup() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.signup-form`)
    sr.reveal(`.signup-title`, {delay: 100, distance: '20px', origin: 'bottom'})
}

export default signup;
