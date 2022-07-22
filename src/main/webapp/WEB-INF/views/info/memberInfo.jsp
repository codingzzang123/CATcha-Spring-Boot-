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
                <a style="margin-left: 1100px;" href="<c:url value="/memberlike/${auth.id }" />"><span style="text-decoration: underline; text-decoration-color: #74747b;">관심목록으로 이동</span></a>
            </div>

            <div class="d1" style="display: flex;">
                <div>
                    <span>작성한 게시물</span><br>
                    <button class="b1" data-bs-toggle="modal" data-bs-target="#boardModal" style="margin-top: 5px;">${count.board }</button>
                </div>

                <div>
                    <span>작성한 댓글</span><br>
                    <button class="b1" data-bs-toggle="modal" data-bs-target="#replyModal" style="margin-top: 5px;">${count.reply}</button>
                </div>

                <div>
                    <span>작성한 리뷰</span><br>
                    <button class="b1" data-bs-toggle="modal" data-bs-target="#reviewModal" style="margin-top: 5px;">${count.review }</button>
                </div>
            </div>



        </div>


    </section>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>




    <!-- 게시글 -->
    <div id="boardModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-center" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel1"><b style="font-family: 'Apple Color Emoji'">게시글 (${count.board})</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <c:if test="${empty boards}"><span style="font-size: large;"><b>아직 게시글을 작성하지 않았습니다❗</b></span></c:if>

                    <c:forEach var="board" items="${boards }">
                        <a href="<c:url value="/board/${board.no }"/>">
                            <span style="font-size: large;"><b>${board.title }</b></span><br>
                            <span>[${board.cate }]</span>
                            <span style="margin-left: 10px;"><fmt:formatDate value="${board.regdate }" pattern="yyyy.MM.dd" /></span><hr>
                        </a>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId1" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- board 댓글 -->
    <div id="replyModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-center" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel2"><b style="font-family: 'Apple Color Emoji'">댓글 (${count.reply})</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <c:if test="${empty replys}"><span style="font-size: large;"><b>작성하신 댓글이 없습니다❗</b></span><br></c:if>
                    <c:forEach var="reply" items="${replys }">
                        <a href="<c:url value="/board/${reply.no }"/>">
                            <span style="font-size: large;"><b>┗ ${reply.content }</b></span><br>
                            <span style="margin-left: 12px;">[${reply.cate }]</span>
                            <span style="margin-left: 10px;"><fmt:formatDate value="${reply.regdate }" pattern="yyyy.MM.dd" /></span><br>
                            <span style="background-color: #e1f6fa; font-size: small; margin-left: 12px;">${reply.title }</span><hr>
                        </a>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId2" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 리뷰 -->
    <div id="reviewModal" class="modal fade" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-center" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel3"><b style="font-family: 'Apple Color Emoji'">리뷰 (${count.review})</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <c:if test="${empty reviews}"><span style="font-size: large;"><b>작성하신 리뷰가 없습니다❗</b></span><br></c:if>
                    <c:forEach var="review" items="${reviews }">
                        <c:choose>
                            <c:when test="${review.code eq 0}">
                                <a href="<c:url value="/movie/content/${review.contentNum }" />">
                                    <span style="font-size: large;"><b>${review.content }</b></span>
                                <br>
                                <span style="background-color: #e1f6fa; font-size: medium; margin-left: 5px;">Movie(${review.title })</span></a>
                            </c:when>
                            <c:when test="${review.code eq 1}">
                                <a href="<c:url value="/tv/content/${review.contentNum }" />">
                                    <span style="font-size: large;"><b>${review.content }</b></span>
                                <br>
                                <span style="background-color: #e1f6fa; font-size: medium; margin-left: 5px;">Tv(${review.title })</span></a>
                            </c:when>
                        </c:choose>
                        <span style="margin-left: 10px;"><fmt:formatDate value="${review.regdate }" pattern="yyyy.MM.dd" /></span><hr>
                    </c:forEach>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId3" class="btn btn-danger" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
