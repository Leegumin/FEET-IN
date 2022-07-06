/* eslint-disable */
function detail() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.detail-container`)
    sr.reveal(`.review-set`, {distance:'50px', duration:1100})
    sr.reveal(`.other-set`, {distance:'50px', duration:1100})
    sr.reveal(`.detail-title`, {delay: 100, distance: '80px', origin: 'bottom'})


    // * image
    window.onload = function() {
        // 사진이 여러개이기 때문에 querySelector가 아닌 querySelectorAll 사용
        let smallPics = document.querySelectorAll('.small');

        for ( let i=0; i<smallPics.length; i++ ) {
            smallPics[i].onclick = changePic;
        }
    }

    function changePic() {
        // 큰 사진 이미지 위치
        let cup = document.querySelector('#pic');
        // 작은 사진 이미지 위치
        // 여기서 this는 smallPics(changePic이라는 function을 실행시키는)
        let newPic = this.src;

        // 큰 이미지 위치 = 작은 이미지 위치
        cup.src = newPic;
    }

    // * toggle content
    $(document).ready( function() {
        $( '.review-content-title' ).click( function() {
            $(this).siblings( '.review-content' ).toggle();
        });
    });
}

export default detail;
