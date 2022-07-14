/* eslint-disable */
function casual() {

    // * scroll
    const sr = ScrollReveal({
        origin: 'top',
        distance: '60px',
        duration: 1500,
        delay: 20
    })

    sr.reveal(`.casual-title`)
    sr.reveal(`.swiper-container`)
    sr.reveal(`#rec-title`)
    sr.reveal(`.casual-container`, {delay: 100, distance: '20px', origin: 'bottom'})
    // sr.reveal(`#rec-title`, {delay: 100, distance: '20px'})

    // * init Isotope1(category)
    var $grid = $('.collection-list').isotope({
        // options
    });
// filter items on button click
    $('.filter-button-group').on('click', 'button', function () {
        var filterValue = $(this).attr('data-filter');
        resetFilterBtns();
        $(this).addClass('active-filter-btn');
        $grid.isotope({filter: filterValue});
    });

    var filterBtns = $('.filter-button-group').find('button');

    function resetFilterBtns() {
        filterBtns.each(function () {
            $(this).removeClass('active-filter-btn');
        });
    }

    // * init Isotope2(color)
    var $grid = $('.collection-list').isotope({
        // options
    });
// filter items on button click
    $('.filter-button-group2').on('click', 'button', function () {
        var filterValue = $(this).attr('data-filter');
        resetFilterBtns2();
        $grid.isotope({filter: filterValue});
    });

    var filterBtns2 = $('.filter-button-group').find('button');
    var filterBtns3 = $('.filter-button-group').find('#all');

    function resetFilterBtns2() {
        filterBtns2.each(function () {
            $(this).removeClass('active-filter-btn');
        });
        filterBtns3.addClass('active-filter-btn');
    }

    // * swiper
    const swiper = new Swiper('.swiper-container', {
        //기본 셋팅
        //방향 셋팅 vertical 수직, horizontal 수평 설정이 없으면 수평
        direction: 'horizontal',
        //한번에 보여지는 페이지 숫자
        slidesPerView: 5,
        //페이지와 페이지 사이의 간격
        spaceBetween: 0,
        //드레그 기능 true 사용가능 false 사용불가
        debugger: true,
        //마우스 휠기능 true 사용가능 false 사용불가
        mousewheel: false,
        //반복 기능 true 사용가능 false 사용불가
        loop: true,
        //선택된 슬라이드를 중심으로 true 사용가능 false 사용불가 djqt
        centeredSlides: true,
        // 페이지 전환효과 slidesPerView효과와 같이 사용 불가
        // effect: 'fade',


        //자동 스크를링
        autoplay: {
            //시간 1000 이 1초
            delay: 2500,
            disableOnInteraction: false,
        },
        //
        // //페이징
        // pagination: {
        //     //페이지 기능
        //     el: '.swiper-pagination',
        //     //클릭 가능여부
        //     clickable: true,
        // },
        //
        // //방향표
        // navigation: {
        //     //다음페이지 설정
        //     nextEl: '.swiper-button-next',
        //     //이전페이지 설정
        //     prevEl: '.swiper-button-prev',
        // },

    });
}

export default casual;
