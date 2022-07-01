/* eslint-disable */
function login2() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.login-form`)
    sr.reveal(`.login-title`, {delay: 100, distance: '20px', origin: 'bottom'})
}

export default login2;
