<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <link href="/css/hosun/main.css" rel="stylesheet"/>

</head>
<body>
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"/>
    <br>
    <section class="css-7klu3x" id="wrapper">

        <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/infoHeader.jsp"/>


        <div class="css-Info-Body">
            <div class="one">
                <p class="css-16qa0p7" style="margin-left: 30px;">통계</p>
                <a style="margin-left: 1100px;" href="<c:url value="/memberlike/${auth.id }" />">관심목록으로 이동</a>
            </div>

            <div class="d1" style="display: flex;">
                <div>
                    <span>작성한 게시물</span><br>
                    <button class="b1" style="margin-top: 5px;">${count.board }</button>
                </div>

                <div>
                    <span>작성한 댓글</span><br>
                    <button class="b1" style="margin-top: 5px;">${count.board }</button>
                </div>

                <div>
                    <span>작성한 리뷰</span><br>
                    <button class="b1" style="margin-top: 5px;">${count.board }</button>
                </div>
            </div>



        </div>


    </section>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
</body>
</html>
