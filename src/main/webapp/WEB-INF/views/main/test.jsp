<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-12
  Time: PM 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <style>
    </style>
</head>
<body class="text-center">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <br><br><br>

    <button type="button" class="like" id="b1" onclick="like()">하트</button>

    <br><br><br>
    <c:if test="${flag eq false}">
        flag = false
    </c:if>

    <c:if test="${flag eq true}"> <!-- 이렇게 안에 변수로 안되네 -->
        flag = true
    </c:if>

    <script>
        var flag = false;

        function like(){
            if(!flag){ /* 좋아요 버튼 클릭 -> active */
                document.getElementById("b1").style.background='red';
                flag = true;

            }else{ /* 좋아요 버튼 클릭 -> off */
                document.getElementById("b1").style.background='#ffffff';
                flag = false;
            }
        }
        /* 넘겨야할 데이터 => 유저 닉네임, poster_path, title, overview 총 4개.*/
        $("#b1").click(function (){
            var object ={
                name:'cherry',
                num:'12345',
                poster:'https://test.com',
                title:'harry poter',
                overview:'description of media',
                type:'0'
            }
            $.ajax({
                url:'./test',
                type:'post',
                data:{
                    object: JSON.stringify(object),
                    flag:flag
                },
                     success:function (){
                        console.log("connect");
                     }, error : function (){
                        console.log("fail");
                }
            });
        });
    </script>

</body>
</html>
