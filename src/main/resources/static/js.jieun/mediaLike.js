var userNum = $('#userNum').val();
var check = $('#check').val();
var contentsNum = $('#contentsNum').val();
var contentsType = $('#contentsType').val();
var contentsTitle = $('#contents.title').val();
var contentsOverview = $('#contents.overview').val();
var posterPath = $('#contents.posterPath').val();
var type;

var flag=false;

function like() {
    if (userNum == 0) {
        Swal.fire({
            title: ' ',
            text: '내 보관함에 작품을 담으려면 로그인이 필요해요.',


            confirmButtonColor: '#84d7fa',
            confirmButtonText: '알겠어요',
            width: 400,
            padding: '2em'
        })
        return;
    }
    if (userNum != 0) {
        if(flag) { /* 좋아요 버튼 클릭 -> active */
            document.getElementById("like").style.background='red';
            flag=true;
        } else { /* 좋아요 버튼 클릭 -> off */
            document.getElementById("like").style.background='#ffffff';
            flag=false;
        }
    }
}
    /* 넘길 데이터 => 유저 id, poster_path, title, overview, contentsType*/
    $("#b1").click(function(){
        // var object = {
        //     contentsNum: contentsNum,
        //     contentsType: contentsType,
        //     contentsTitle: contentsTitle,
        //     contentsOverview: contentsOverview,
        //     posterPath: posterPath,
        //     userNum: userNum,
        //     check: check
        // }
        $.ajax({
            url: '/like',
            type: 'post',
            dataType: 'MediaLikeDto',
            data: {
                contentsNum: contentsNum,
                contentsType: contentsType,
                contentsTitle: contentsTitle,
                contentsOverview: contentsOverview,
                posterPath: posterPath,
                userNum: userNum,
                check: check,
                flag:flag
            },
                success:function(){
                console.log("connect");
                }, error:function(){
                console.log("fail");
            }
        });
    });


