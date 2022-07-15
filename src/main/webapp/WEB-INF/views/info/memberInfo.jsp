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
    <style>
        .modal-body {
            max-height: calc(100vh - 420px);
            overflow-y: auto;
        }
    </style>
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
                    <button class="b1" data-bs-toggle="modal" data-bs-target="#boardModal" style="margin-top: 5px;">${count.board }</button>
                </div>

                <div>
                    <span>작성한 댓글</span><br>
                    <button class="b1" data-bs-toggle="modal" data-bs-target="#searchModal" style="margin-top: 5px;">${count.board }</button>
                </div>

                <div>
                    <span>작성한 리뷰</span><br>
                    <button class="b1" style="margin-top: 5px;">${count.board }</button>
                </div>
            </div>



        </div>


    </section>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>





    <div id="boardModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-center" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel2"><b style="font-family: 'Apple Color Emoji'">게시글 (${count.board})</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <c:forEach var="board" items="${boards }">

                        <span style="font-size: large;"><b>${board.title }</b></span><br>
                        <span>${board.cate }</span>
                        <span style="margin-left: 15px;"><fmt:formatDate value="${board.regdate }" pattern="yyyy.MM.dd" /></span><br> <hr>

                    </c:forEach>
                </div>

                <div class="modal-footer">
                    <button type="button" id="ModalCloseId2" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>



</body>
</html>
