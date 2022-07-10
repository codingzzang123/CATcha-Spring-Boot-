$(function() {
    /* 스크롤 생기는 영역의 최종 가로 길이 구하기*/
    var width = $('.scroll').width();

    /* > 버튼 누르면 현재 보여지는 화면의 크기 만큼 오른쪽으로 스크롤 이동*/
    $(document).on('click', '.right', function() {
        /* 부모 태그 안에 버튼과 스크롤 돼는 부분이 형제 노드로서 존재하기 때문에 siblings로 찾아준 뒤, 스크롤 이동 */
        var scrollX = $(this).siblings('.scroll').scrollLeft();
        $(this).siblings('.scroll').scrollLeft(scrollX + width);
    });

    $(document).on('click', '.left', function() {
        var scrollX = $(this).siblings('.scroll').scrollLeft();
        $(this).siblings('.scroll').scrollLeft(scrollX - width);
    });

    $('.scroll').scroll(
        function() {
            /* 스크롤이 오른쪽 끝에 위치할 경우*/
            if ($(this).scrollLeft() + $(this).innerWidth() >= $(this)
                .prop('scrollWidth')) {
                /* > 버튼을 숨기고*/
                $(this).parent().find('.right').hide();
            } else {
                /* < 버튼을 보이게한다*/
                $(this).parent().find('.right').show();
            }
        });
    $('.scroll').scroll(function() {
        if ($(this).scrollLeft() == 0) {
            $(this).parent().find('.left').hide();
        } else if ($(this).scrollLeft() != 0) {
            $(this).parent().find('.left').show();
        }
    });
});