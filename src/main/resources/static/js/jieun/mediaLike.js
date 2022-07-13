    // var userId = $('#userId').val();
    // var flag = $('#flag').val();
    //
    // window.onload=function(){
    //     var object = {
    //         'contentsNum': $('#contentsNum').val(),
    //         'userId': 'admin',
    //         'code': 0
    //     }
    //     $
    //     ajax({
    //         url: '../../likeCheck',
    //         type: 'post',
    //         data: {
    //             object: JSON.stringify(object),
    //             'flag': flag
    //         },
    //         success:function(data) {
    //             console.log("onload function 실행")
    //         }, error: function (){
    //             console.log("onload function 실패")
    //         }
    //     })
    // }
    //
    // function like() {
    //     if (userId == 'default') {
    //         Swal.fire({
    //             title: ' ',
    //             text: '보관함에 작품을 담으려면 로그인해주세요!',
    //             confirmButtonColor: '#84d7fa',
    //             confirmButtonText: '알겠어요',
    //             width: 400,
    //             padding: '2em'
    //         })
    //         return;
    //     }
    //     if (userId != 'default') {
    //
    //         var object = {
    //             'contentsNum': $('#contentsNum').val(),
    //             'title': $('#title').val(),
    //             'overview': $('#overview').val(),
    //             'posterPath': $('#posterPath').val(),
    //             'userId': 'admin',
    //             'code': 0
    //         }
    //
    //         $
    //             .ajax({
    //                 url: '../../like',
    //                 type: 'post',
    //                 data: {
    //                     object: JSON.stringify(object),
    //                     'flag': flag
    //                 },
    //                 success: function () {
    //                     if (param.result === 'success') {
    //                         flag = true;
    //                         $('#b1')
    //                             .removeClass(
    //                                 'css-1tc9iuk-StylelessButton-ContentActionButton')
    //                             .addClass(
    //                                 'css-15hndx7-StylelessButton-ContentActionButton');
    //                         $('#off').hide();
    //                         $('#on').show();
    //                     } else if (param.result === 'cancel') { //취소
    //                         flag = false;
    //                         $('#like')
    //                             .removeClass(
    //                                 'css-15hndx7-StylelessButton-ContentActionButton')
    //                             .addClass(
    //                                 'css-1tc9iuk-StylelessButton-ContentActionButton');
    //                         $('#on').hide();
    //                         $('#off').show();
    //                     }
    //                 }
    //             });
    //
    //     }
    // }
    //
    // toggle = function() {
    //     if (flag == true) {
    //         $('#on').show();
    //         $('#off').hide();
    //     } else if (flag == false) {
    //         $('#off').show();
    //         $('#on').hide();
    //     }
    // }
    // toggle();


        // if(!flag) { /* 좋아요 버튼 클릭 -> active */
        //     document.getElementById("b1").style.background='red';
        //     flag=true;
        // } else { /* 좋아요 버튼 클릭 -> off */
        //     document.getElementById("b1").style.background='#ffffff';
        //     flag=false;
        // }




/*    /!* 넘길 데이터 => 유저 id, poster_path, title, overview, code(컨텐츠타입)*!/
    $("#b1").click(function(){
        console.log("ddd");

        // var flag=false;
        var object ={
            'contentsNum': $('#contentsNum').val(),
            'title': $('#title').val(),
            'overview': $('#overview').val(),
            'posterPath': $('#posterPath').val(),
            'userId': 'admin',
            'code': 0
        }

        $.ajax({
            url: '../../like',
            type: 'post',
            data: {
                object: JSON.stringify(object),
                'flag':flag
            },
                success:function(data){
                console.log("connect");
                }, error:function(){
                console.log("fail");
            }
        });
    });*/


