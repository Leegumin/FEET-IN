/* eslint-disable */
function mypage() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.mypage-container`)
    sr.reveal(`.profile-content`, {delay: 100, distance: '20px', origin: 'bottom'})
    sr.reveal(`.profile-icon`, {delay: 100, distance: '20px', origin: 'bottom'})

}
export default mypage;
