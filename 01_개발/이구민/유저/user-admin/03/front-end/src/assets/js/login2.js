/* eslint-disable */
function login() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.login-form`)
    sr.reveal(`.login-title`, {delay: 100, distance: '20px', origin: 'bottom'})


    $(document).ready(function(){
        $('#birth-date').mask('00/00/0000');
        $('#phone-number').mask('0000-0000');
    })
}

export default login;
