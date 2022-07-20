<%--
  Created by IntelliJ IDEA.
  User: yaele
  Date: 2022-07-15
  Time: 오후 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상세보기</title>
    <style>
        * {
            font-family: 'Noto Sans KR', sans-serif;
            color:#333;
        }
        .board_wrap {
            background: #ddd;
            padding:1rem; ;
        }
        .board_write::placeholder{
            display: flex; justify-content: center; align-items: center;
            text-align: center;
            vertical-align: center;
            padding-top:100px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include> <br><br><br>
<div class="container mx-auto">
    <h1 class="text-2xl mb-10 text-left">Name of Board</h1>
    <form method="POST" action="/board/detail">
        <div class="board_wrap">
            <div class="flex justify-start items-center gap-5">
                <span style="font-size: large"><strong>카테고리 </strong></span>
                <span style="font-size:medium">${board.cate}</span>
            </div>

            <div class="flex w-2/3 justify-start mt-5" style="margin-top: 20px; margin-bottom: 20px;">
               <span style="font-size: large">제목 </span>
                <span style="font-size:medium">${board.title}</span>
            </div>

            <!-- 조회수, 추천수, 댓글 수 , 작성시간, 작성자 나와야 함 -->

            <div class="mt-5 flex justify-center items-center">
                <textarea class="w-full board_write p-5" name="content" id="" cols="100" rows="10" id ="{board.content}"
                          style="resize: none; overflow-y: hidden;" disabled>${board.content}</textarea>
            </div>
        </div>

        <div>
            <button type="button" id="b1" <c:if test="${board.name ne auth.name }"> onclick="like()"</c:if> class="btn btn-default btn-xs" >
                <img src="" style="width: 50px; height: 50px; cursor:pointer; border:0px;" id="ex">
            </button>
        </div>

        <div class="flex justify-end mt-5" style="margin-top: 20px;">
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-black rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center"
               href='/board'>뒤로가기</a>
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-black rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center"
               href='/board/edit/${board.no}'>수정</a>
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-black rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center"
               href='/board/delete/${board.no}'>삭제</a>
            <button id="testa" type="button">test</button>
        </div>
    </form>

</div>
    <input type="hidden" id="userId" value="${auth.id }">
    <input type="hidden" id="contentsNum" value="${board.no }">
    <input type="hidden" id="title" value="${board.title }">
    <input type="hidden" id="overview" value="${board.content}">
    <input type="hidden" id="posterPath" value="${board.imgs }">

    <input type="hidden" id="userName" value="${auth.name }">
    <input type="hidden" id="flag" value="">

    <script src="${pageContext.request.contextPath}/js/hosun/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/scroll.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        window.onload=function(){

            let hdiv = document.getElementById('emptyReview');
            if(hdiv == null)
                flag = true;

            let userId = $('#userId').val();
            let contentsNum = $('#contentsNum').val();
            let object1 = {
                'contentsNum': contentsNum,
                'userId': userId,
                'code': '2'
            }
            $
                .ajax({
                    url: '../../likeCheck',
                    type: 'post',
                    data: {
                        object: JSON.stringify(object1),
                        // 'flag': flag
                    },
                    success:function(data) {
                        console.log("onload function 실행 - flag:" + data);
                        $('#flag').val(data);
                        if(data == 'true') {
                            document.querySelector("#ex").src='/img/icon/media/Heart.png';
                        }
                        if (data == 'false') {
                            document.querySelector("#ex").src='/img/icon/media/Emptyheart.png';
                        }
                    }, error: function (){
                        console.log("onload function 실패")
                    }
                })
        }
        function like() {
            console.log("script - like() 실행")
            let userId = $('#userId').val();
            let flag = document.getElementById("flag").value;
            let contentsNum = $('#contentsNum').val();
            let title = $('#title').val();
            let overview = $('#overview').val();
            let posterPath = $('#posterPath').val();
            if (userId != 'default') {
                var object2 = {
                    'contentsNum': contentsNum,
                    'title': title,
                    'overview': overview,
                    'posterPath': "/img/profile/"+posterPath,
                    'userId': userId,
                    'code': '2'
                }
                $
                    .ajax({
                        url: '../../like',
                        type: 'post',
                        data: {
                            object: JSON.stringify(object2),
                            'flag': flag
                        },
                        success: function(data){
                            console.log("likeController 동작 성공: "+data);
                            //버튼 누르면 컬러 바꾸기..
                            if(data == 'true') {
                                insertLikeAlert();
                                document.querySelector("#ex").src='/img/icon/media/Heart.png';
                            } else {
                                deleteLikeAlert();
                                document.querySelector("#ex").src='/img/icon/media/Emptyheart.png';
                            }
                        }, error: function(request,error){
                            console.log("likeController 동작 fail");
                            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
            }
        }
        var name = '${auth.name }'
        /* Alert 스크립트 */


        /* 1.좋아요(하트) 눌렀을때 db 저장 -> 메세지 toast + 알림 카운트 업데이트 */
        function insertLikeAlert(){
            var targetUser = '${board.name }';

            let insertObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':0
            }
            $.ajax({
                type: "put",
                data: {
                   object : JSON.stringify(insertObject)
                },
                url: "${pageContext.request.contextPath}/alert/like/insert",
                success: function (data) {
                    console.log("Success update(insert)");
                    let socketMsg = "like,"+'${auth.name },'+targetUser+","+ '${board.title }';
                    console.log(socketMsg);
                    socket.send(socketMsg);
                }
            });

            <%--if(socket){--%>
            <%--    let socketMsg = "like"+'${auth.name }',targetUser;--%>
            <%--    console.log(socketMsg);--%>
            <%--    socket.send(socketMsg);--%>
            <%--}--%>
        }

        function deleteLikeAlert(){
            var targetUser = '${board.name }'

            let deleteObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':0
            }
            $.ajax({
                type: "delete",
                data: {
                    object : JSON.stringify(deleteObject)
                },
                url: "${pageContext.request.contextPath}/alert/like/delete",
                success: function (data) {
                    console.log("Success update(delete)");
                }
            });
        }

        // $("#testa").click(function(){
        //     insertLikeAlert();
        // });
    </script>

</body>
</html>



