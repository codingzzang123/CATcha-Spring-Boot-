    var userId = $('userId').val();
    var flag = false;
    function like() {
        if(userId=='default') {
            Swal.fire({
                title:' ',
                text: '보관함에 작품을 담으려면 로그인해주세요!',
                confirmButtonColor:'#84d7fa',
                confirmButtonText: '알겠어요',
                width: 400,
                padding:'2em'
            })
            return;
        }


        if(!flag) { /* 좋아요 버튼 클릭 -> active */
            document.getElementById("b1").style.background='red';
            flag=true;
        } else { /* 좋아요 버튼 클릭 -> off */
            document.getElementById("b1").style.background='#ffffff';
            flag=false;
        }
    }


    /* 넘길 데이터 => 유저 id, poster_path, title, overview, code(컨텐츠타입)*/
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
    });


