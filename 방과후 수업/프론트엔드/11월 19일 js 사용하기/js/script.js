// jquery('선택자').함수()
// $('선택자').함수()



jQuery(function () {
    $('.menu>li').mouseover(function () {
        $(this).find('.sub').stop().slideDown(500);
    }).mouseout(function () {
        $(this).find('.sub').stop().slideUp(500);
    })
})
    // 마우스를 올렸을때 메뉴가 나타나게 해줘