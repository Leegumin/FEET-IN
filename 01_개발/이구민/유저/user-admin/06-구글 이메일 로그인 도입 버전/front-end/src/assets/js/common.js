/* eslint-disable */
function menu() {
    // * menu hover
// $(document).ready(function () {
    $(function () {
        // 대메뉴에 마우스커서를 올리면 펑션을 실행
        $('.outer-list').hover(function () {
            // 마우스오버 펑션 = .inner-menu를 찾아서 보이게함
            $(this).find('.dropdown-menu').show(150);
        }, function () {
            // 마우스아웃 펑션 = .inner-menu를 찾아서 숨김
            $(this).find('.dropdown-menu').hide(200);
        });
    });
}

export default menu;
