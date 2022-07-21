<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-19
  Time: PM 7:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body class="text-center">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <br><br><br>
    <button id="test">전송</button>
    <script>
        // $("#test").click(function(){
        //     if(socket){
        //         let socketMsg = "like,"+"mouse,"+"wendy"; //mouse => 댓글,좋아요 그런거 한 사람 auth.name으로 접근
        //         console.log(socketMsg);                  //wendy => 글 작성자 == board.writer 으로 접근
        //         socket.send(socketMsg);
        //     }
        // });

        /* 1. 게시글 에(comment) 대한 script */
        function insertCommentAlert(){ //댓글( 게시글에 대한 댓글 ) => target은 그러면 게시글 주인이여야함.
            var targetUser = '${board.name }'; //targetUser = sub_name

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

        function deleteCommentAlert(){
            var targetUser = '${board.name }'

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
         /* ========================================= */
        /* 2. 자식댓글 (reply) 에 대한 script */
        function insertReplyAlert(){ //답글( 댓글에 대한 답글 ) => target은 그러면 댓글 주인이여야함.
            var targetUser = '${comment.name }'; /* target은 댓글 주인이 되야함 아직 모름 ㅋ 만들어지고 생각*/

            let insertObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':2
            }
            $.ajax({
                type: "put",
                data: {
                    object : JSON.stringify(insertObject)
                },
                url: "${pageContext.request.contextPath}/alert/reply/insert",
                success: function (data) {
                    console.log("Success update(Comment Insert)");
                    let socketMsg = "reply,"+'${auth.name },'+targetUser+","+ '${comment.title }'; // " ..댓글에 auth.name유저가 댓글을 달았습니다"
                    console.log(socketMsg);
                    socket.send(socketMsg);
                }
            });

        }

        function deleteCommentAlert(){
            var targetUser = '${board.name }' /* target은 댓글 주인이 되야함 아직 모름 ㅋ 만들어지고 생각*/

            let deleteObject ={
                'subName':targetUser,
                'pubName':'${auth.name }',
                'bno':'${board.no }',
                'code':2
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




















    </script>
</body>
</html>
