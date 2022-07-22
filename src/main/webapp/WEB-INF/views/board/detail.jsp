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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <title>상세보기</title>
    <style>
        /** {*/
        /*    font-family: 'Noto Sans KR', sans-serif;*/
        /*    color:#333;*/
        /*}*/
        /*.board_wrap {*/
        /*    background: #ddd;*/
        /*    padding:1rem; ;*/
        /*}*/
        /*.board_write::placeholder{*/
        /*    display: flex; justify-content: center; align-items: center;*/
        /*    text-align: center;*/
        /*    vertical-align: center;*/
        /*    padding-top:100px;*/
        /*}*/
        .comment-items{
            margin-top: 12px;
            height: 3.5em;
            width: auto;
            /*display: inline-block;*/
            border-radius: 1em;
            border: 1px dotted rgb(27, 26, 26);
            /*margin-left: 20px;*/
            padding-left: 20px;
            padding-top: 5px;
            align-items:center; display: flex;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <section class="css-7klu3x" id="wrapper">
        <div class="container mt-5 mb-5">
            <div style="margin-top:50px;">
                <span style="font-style: italic; font-family: Inter; border: 1px dotted #d3d3d3;
                    font-size: xxx-large; padding-left: 10px; padding-right: 15px; border-radius: 2em; ">CATcha Community</span>
            </div>
            <hr>

            <h2><b>[${board.cate}]&nbsp;${board.title }</b></h2>

            <div class="row" style="margin-top:20px; margin-bottom: 25px;">
                <div class="col-lg-5">
                    written by <b>${board.name }</b>&nbsp;&nbsp;&nbsp;<span style="color: #d3d3d3">|</span>&nbsp;&nbsp;&nbsp;<b><fmt:formatDate value="${board.regdate }" type="both"/></b>
                </div>
                <div class="col-lg-1"></div>
                <div class="col-lg-2"></div>
                <div class="col-lg-4 text-end">
                    조회&nbsp;&nbsp;${board.views }&nbsp;&nbsp;<span style="color: #d3d3d3">|</span>&nbsp;&nbsp;
                    추천&nbsp;&nbsp;${board.likes }&nbsp;&nbsp;<span style="color: #d3d3d3">|</span>&nbsp;&nbsp;
                    댓글${board.replys }
                </div>
            </div>

            <div>
                <textarea class="form-control" style="height: 400px; resize:none;" readonly>${board.content }</textarea>
            </div>

            <div class="row">
                <div class="col-lg-3"></div>
                <div class="col-lg-3">
                </div>
                <div class="col-lg-6 text-end" style="margin-top: 15px;">
                    <div style="display: inline-block;margin-right: 5px;">
                        <button type="button" id="b1" <c:if test="${board.name ne auth.name }"> onclick="like()"</c:if> class="btn btn-default btn-xs" >
                            <img src="" style="width: 50px; height: 50px; cursor:pointer; border:0px;" id="ex">
                        </button>
                    </div>

                    <div style="display: inline-block;">
                        <a href='/board'>
                            <button type="button" class="btn btn-outline-info">
                                뒤로가기
                            </button>
                        </a>
                        <c:choose>
                            <c:when test="${auth.name eq board.name}">
                                <a href='/board/edit/${board.no}'>
                                    <button type="button" class="btn btn-outline-info">
                                        수정
                                    </button>
                                </a>
<%--                                <a href='/board/delete/${board.no}'>--%>
                                <button type="button" data-bs-toggle="modal" data-bs-target="#DelBoard" data-test="${board.no}" class="btn btn-outline-danger">삭제</button>

                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>


                    </div>
                </div>

            </div>




            <div class="css-lufi3b" style="margin-top: 25px;">
                <div class="css-1qq59e8">
                    <span class="css-16qa0p7" style="font-size: xx-large;">Comment</span>
                    <div id="result" scroll=auto style="overflow-x:hidden; margin-bottom: 30px;" class="review-body">

                        <c:choose>
                            <c:when test="${empty comment}">
                                <div class='comment-items'>
                                    <span style="font-family: Cambria; font-size: medium;" >아직 등록된 댓글이 없습니다!</span>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="comment" items="${comment}">
                                    <div class='comment-items' id="${comment.no}" >
                                        <div style="display: inline-block; width: 950px; height: 40px;">
                                            <span style="margin-left: 20px; font-family: Cambria; font-size: medium; cursor: pointer" onclick="showReply(${comment.no})"><b>${comment.content}</b></span>
                                        </div>
                                        <div style="display: inline-block; width: 100px; height: 40px; text-align: end; margin-bottom: 12px; ">
                                                <span style="font-size: small; margin-bottom: 3px;">
                                                        <fmt:formatDate value="${comment.regdate }" pattern="MMM dd HH:mm:ss" /><br>by ${comment.writer}
                                                </span>
                                        </div>
                                        <div style="display: inline-block; width: 40px; height: 40px;">
                                            <c:if test="${auth.name eq comment.writer }">
                                                <button type="button" data-bs-toggle="modal" data-bs-target="#DelComment" data-test="${comment.no }"
                                                        class="btn btn-default btn-xs">&times;</button>
                                            </c:if>
                                        </div>
                                        <input type="hidden" id="${comment.no}" name="${comment.no}" value="${comment.no}">
                                    </div>
                                    <div id="reply${comment.no}" name="${comment.no}" style="display: none">
                                        <sec:authorize access="!isAuthenticated()">
                                            <div class="card" style="margin-top: 10px;">
                                                <div class="card-body" style="font-size: small">
                                                    <a href="${pageContext.request.contextPath}/member/login"><b>로그인</b></a>을 하시면 댓글를 등록할 수 있습니다.
                                                </div>
                                            </div>
                                        </sec:authorize>
                                        <sec:authorize access="isAuthenticated()">
                                            <div class="card" style="margin-top: 10px;">
                                                <form action="/board/reply" method="post">
                                                    <div class="card-header bi bi-chat-right-dots">
                                                        <b style="font-family: Cambria;">Write a Reply</b>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col-lg-11">
                                                            <textarea id="reply" name="content" placeholder="답글을 작성해주세요" style="resize: none; font-size: small; height : 100px; width: 1030px; border : none; margin-top : 5px; margin-left: 5px;"></textarea>
                                                        </div>
                                                        <div class="col-lg-1">
                                                            <input type="submit" value="등록"  class="btn btn-outline-primary bi bi-pencil-square" style="margin-top: 65px;">
                                                        </div>
                                                    </div>
                                                    <input type="hidden" value="${auth.name}" name="writer">
                                                    <input type="hidden" value="${board.no}" name="boardNo">
                                                    <input type="hidden" value="${comment.no}" name="step">
                                                </form>
                                            </div>
                                        </sec:authorize>
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <sec:authorize access="!isAuthenticated()">
                <div class="card" style="margin-top: 50px;">
                    <div class="card-header bi bi-chat-right-dots">
                        <b style="font-family: Cambria">Write a Comment</b>
                    </div>
                    <div class="card-body" style="font-size: small">
                        <a href="${pageContext.request.contextPath}/member/login"><b>로그인</b></a>을 하시면 댓글를 등록할 수 있습니다.
                    </div>
                </div>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="card" style="margin-top: 50px;">
                    <form action="/board/comment" method="post" onsubmit="return submitComment();">
                        <div class="card-header bi bi-chat-right-dots">
                            <b style="font-family: Cambria;">Write a Comment</b>
                        </div>
                        <div class="row">
                            <div class="col-lg-11">
                                <textarea id="content" name="content" placeholder="댓글을 작성해주세요" style="resize: none; font-size: small; height : 100px; width: 1030px; border : none; margin-top : 5px; margin-left: 5px;"></textarea>
                            </div>
                            <div class="col-lg-1">
                                <input type="submit" id="insertComment" value="등록"  class="btn btn-outline-primary bi bi-pencil-square" style="margin-top: 65px;">
                            </div>
                        </div>
                        <input type="hidden" value="${auth.name}" name="writer">
                        <input type="hidden" value="${board.no}" name="boardNo">
                    </form>
                </div>
            </sec:authorize>
        </div>
    </section>

    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>



    <input type="hidden" id="userId" value="${auth.id }">
    <input type="hidden" id="contentsNum" value="${board.no }">
    <input type="hidden" id="title" value="${board.title }">
    <input type="hidden" id="overview" value="${board.content}">
    <input type="hidden" id="posterPath" value="${board.imgs }">

    <input type="hidden" id="userName" value="${auth.name }">
    <input type="hidden" id="flag" value="">

    <!-- 삭제 확인 모달 -->
    <div class="modal" id="DelComment" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="font-family: Georgia"><b>댓글을 삭제 하시겠습니까?</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId" class="btn btn-danger" data-bs-dismiss="modal" style= "font-family: Consolas">Close</button>
                    <input type="hidden" id="deleteModal" value="">
                    <button type="button" class="btn btn-warning" data-bs-dismiss="modal" style= "font-family: Consolas" id="delete">Delete</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal" id="DelBoard" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="font-family: Georgia"><b>이 글을 삭제 하시겠습니까?</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId" class="btn btn-danger" data-bs-dismiss="modal" style= "font-family: Consolas">Close</button>
                    <input type="hidden" id="deletelModal2" value="">
                    <button type="button" class="btn btn-warning" data-bs-dismiss="modal" style= "font-family: Consolas" id="deleteBoard">Delete</button>
                </div>
            </div>
        </div>
    </div>

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

    /* 댓글 생성,삭제 -> 알림 */
    function insertCommentAlert(){ //댓글( 게시글에 대한 댓글 ) => target은 그러면 게시글 주인이여야함. + 자기 글에 대한 자신의 댓글은 알림x
        var targetUser = '${board.name }'; //targetUser = sub_name
        if(targetUser != '${auth.name}'){
            let insertObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':1
            }
            $.ajax({
                type: "put",
                data: {
                    object : JSON.stringify(insertObject)
                },
                url: "${pageContext.request.contextPath}/alert/reply/insert",
                success: function (data) {
                    console.log("Success update(Comment Insert)");
                    let socketMsg = "comment,"+'${auth.name },'+targetUser+","+ '${board.title }'; // " ..게시글에 auth.name유저가 댓글을 달았습니다"
                    console.log(socketMsg);
                    socket.send(socketMsg);
                }
            });
        }
    }

    function deleteCommentAlert(){
        var targetUser = '${board.name }'

        if(targetUser != '${auth.name}'){
            let deleteObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':1
            }
            $.ajax({
                type: "delete",
                data: {
                    object : JSON.stringify(deleteObject)
                },
                url: "${pageContext.request.contextPath}/alert/reply/delete",
                success: function (data) {
                    console.log("Success update(delete)");
                }
            });
        }
    }
    /* 댓글은 한 글자 이상, 조건맞으면 알림 테이블과 비동기 통신 시작 */
    function submitComment(){
        var check = $("textarea#comment").val().length;
        if(check < 1){
            return false;
        }else{
            insertCommentAlert();
            return true;
        }
    }


    //Reply
    var beforeNo = null;

    function showReply(no){
        console.log(no);
        console.log(beforeNo);
        if($('#reply'+no).css("display") == "none"){
            $('#reply'+no).css("display", "block")
            if($('#reply'+beforeNo).css("display") == "block" && no != beforeNo){
                console.log("실행")
                $('#reply'+beforeNo).css("display", "none")
            }
            beforeNo = no;
        }else if($('#reply'+no).css("display") == "block"){
            $('#reply'+no).css("display", "none")
        }
    }

    $(document).ready(function(){
        $('#DelComment').on('show.bs.modal', function (e) {
            var data = $(e.relatedTarget).data('test');
            $('#deleteModal').val(data);
            console.log(data);
        });
        $('#DelBoard').on('show.bs.modal', function (f) {
            var data = $(f.relatedTarget).data('test');
            $('#deletelModal2').val(data);
            console.log(data);
        });
    });

    $("#delete").click(function(){
        var d = $('#deleteModal').val()
        console.log("d : " +d);
        deleteReply(d);
    });

    function deleteReply(i){
        console.log("i : "+ i)
        $.ajax({
            url: '${pageContext.request.contextPath}/board/deleteComment',
            type: 'post',
            data:{
                'no':i
            },success:function (data){
                console.log("Delete Success");
                let deleteDiv = document.getElementById(i);
                deleteDiv.remove();
            },error:function (){
                console.log("fail");
            }
        });
    }

    //글삭제
    $("#deleteBoard").click(function(){
        var e = $('#deletelModal2').val()
        console.log(e);
        deleteBoard(e);
    })

    function deleteBoard(i){
        console.log("i: "+i);
        $.ajax({
            url:'${pageContext.request.contextPath}/board/delete',
            type: 'post',
            data:{
                'no' : i
            }, success:function(data) {
                console.log("Delete Success");
            }, error:function(){
                console.log("fail");
            }
        });
    }
</script>

</body>
</html>



