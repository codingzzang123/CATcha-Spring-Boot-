<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-13
  Time: AM 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/hosun/infoHeader.css" rel="stylesheet"/>
</head>
<body>

    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"/>
    <br>

    <section class="css-7klu3x" id="wrapper">
        <section class="new_index">
            <div class="css-Like-header css-lufi3b">
                <div class="container">
                    <div class="cell-header">
                        <img src="/img/profile/39cb7c95.jpg" class="rounded-circle css-memImg">
                    </div>
                    <div class="cell-content1">
                        <span class="css-16qa0p7">User(ID+NAME)</span><br>
                        <span class="css-16qa0p7">User(Regdate)</span>
                    </div>
                </div>
            </div>
        </section>
        <section>

            <span>
                <button onclick="movie()">영화 : ??개</button>
            </span>

            <span>
                <button onclick="tv()">TV : ??개</button>
            </span>

            <span>
                <button onclick="board()">게시판 : ??개</button>
            </span>

            <div class="css-Like-Body" id="result" style="overflow: auto">
                <!-- -->
            </div>
        </section>

        <input type="hidden" id="cNum" name="cNum" value="${cNum }">
        <input type="hidden" id="code" name="code" value="${code }">

        <script>
            const element = document.getElementById('result');

            $(document).ready(function(){
                var cNum = $('#cNum').val();
                var code = $('#code').val();
                $.ajax({
                    url: '../memberlike/movie',
                    type: 'post',
                    data:{
                        'cNum':cNum,
                        'code':code
                    },
                    success:function (data){
                        resultHTML ="";
                        for(let i=0; i<data.length; i++){
                            console.log(data[i].posterPath)
                            resultHTML += "<div><img src=\"" + data[i].posterPath + "\" class=\"css-listImg\"></div>";
                        }
                        element.innerHTML= resultHTML;
                        // console.log(data);
                    },error:function (){
                        console.log("fail");
                    }
                });
            });
        </script>


        <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
</body>
</html>
