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
        $("#test").click(function(){
            if(socket){
                let socketMsg = "like,"+"mouse,"+"wendy"; //mouse => 댓글,좋아요 그런거 한 사람 auth.name으로 접근
                console.log(socketMsg);                  //wendy => 글 작성자 == board.writer 으로 접근
                socket.send(socketMsg);
            }
        });
    </script>
</body>
</html>
