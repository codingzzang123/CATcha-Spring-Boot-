<%--
  Created by IntelliJ IDEA.
  User: Jieun Kim
  Date: 2022-07-10
  Time: 오후 5:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/jieun/contentList.css" rel="stylesheet"/>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/jieun/star-rating.css" media="all" rel="stylesheet" type="text/css" />
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        <script src="js/jieun/star-rating.js" type="text/javascript">

    <script src="https://kit.fontawesome.com/325cf61a47.js" crossorigin="anonymous"></script>
    <title>컨텐츠 디테일 화면</title>

    <style>
        .css-leftImg{
            width: 409px;
            height: 450px;
        }

        .css-right-text{
            width: 818px;
            height: 450px;
        }

        .contentsOverview{
            width: 821px;
            height: 200px;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .review-body{
            max-height: 380px;
            /*border-radius: 2em;*/
            /*border: 0.5px dotted red;*/
        }
        textarea {
            width: 100%;
            height: 3em;
            border: none;
            resize: none;
            padding-left: 16px;
        }
        .likeImg{
            display: inline;
        }

        .card{
            width: 1250px;
            text-align: left;
        }

        .review-items{
            margin-top: 12px;
            height: 3.5em;
            width: 1250px;
            /*display: inline-block;*/
            border-radius: 1em;
            border: 1px dotted rgb(27, 26, 26);
            /*margin-left: 20px;*/
            padding-left: 20px;
            padding-top: 5px;
            align-items:center; display: flex;
        }

        /*.css-7klu3x .card{*/
        /*    margin: auto;*/
        /*}*/

        #divbtn .btn{
            margin: auto;
        }

        review-items button{
            text-align: end;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include><br><br><br>

    <section class="css-7klu3x">
        <section>
            <div class="css-lufi3b">
                <div class="css-1qq59e8" style="display: flex;">
                    <div class="row">
                        <div class="col-lg-4 css-leftImg" >
                            <c:choose>
                                <c:when test="${contents.posterPath eq 'default'}">
                                    <img src="/img/media/cinema_default.png" width="300">
                                </c:when>
                                <c:otherwise>
                                    <img src="${contents.posterPath}" style="object-fit: cover;">
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="col-lg-8 css-right-text">
                            <div class="contentsTitle">
                                <span class="css-16qa0p7" style="font-size: xx-large;"> ${contents.title}</span>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${auth eq null}"></c:when>--%>
<%--                                    <c:otherwise>--%>
<%--                                        <button type="button" id="b1" onclick="like()" class="btn btn-default btn-xs" ><img src="" style="width: 50px; height: 50px; cursor:pointer; border:0px;" id="ex"></button>--%>
<%--                                    </c:otherwise>--%>
<%--                                </c:choose>--%>
                                <button type="button" id="b1" <c:if test="${auth ne null}"> onclick="like()"</c:if> class="btn btn-default btn-xs" ><img src="" style="width: 50px; height: 50px; cursor:pointer; border:0px;" id="ex"></button>
                            </div>
                            <div class="contentsOthers">
                                <strong>Release Date</strong> :<fmt:formatDate value="${contents.releaseDate}" pattern="yyyy-MM-dd"/><br>
                                <strong>장르</strong> :
                                <c:choose>
                                    <c:when test="${empty contents.ls}">
                                        정보가 없습니다.
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="genres" items="${contents.ls}">
                                            ${genres.genreName}
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                                <br>
                                <strong>상영시간</strong> :
                                <c:choose>
                                    <c:when test="${contents.runtime eq '0'}">정보가 없습니다.</c:when>
                                    <c:otherwise>${contents.hour}h ${contents.minute}m</c:otherwise>
                                </c:choose>
                            </div>
                            <div style="width:200px; height:50px; display:inline-block; margin-top: 10px; margin-bottom: 5px;">
                            <c:choose>
                                <c:when test="${ott eq null}">
                                    <strong>이용 가능한 플랫폼</strong> : 준비중
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="otts" items="${ott}">
                                        <a href="${otts.ottAdd}">
                                            <img src="https://image.tmdb.org/t/p/original${otts.logoPath}" style="width: 70px; height: 70px; border-radius: 1em;">
                                        </a>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                            </div>
                            <c:choose>
                                <c:when test="${videoUrl eq null}"></c:when>
                                <c:otherwise>
                            <div style="text-align: right; margin-right:20px;"><a href="https://www.youtube.com/watch?v=${videoUrl}"><b>트레일러 보기</b></a></div>
                                </c:otherwise>
                            </c:choose>
                            <div>
                                <br><strong>Overview</strong><br>
<%--                                <div class="contentsOverview" style="border: 1px solid black; border-radius: 15px; padding:15px; ">--%>
<%--                                    ${contents.overview}--%>
<%--                                </div>--%>
                                <textarea disabled style="resize: none; width: 820px; height: 200px; border: 1px solid black; border-radius: 15px; ">${contents.overview}
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>



        <c:choose>
            <c:when test="${empty cast}">
                출연진 정보가 없습니다.
            </c:when>
            <c:otherwise>
                <div class="css-lufi3b">
                    <div class="css-pbseb6-StyledHomeListTitleRow">
                        <p class="css-16qa0p7">주요 출연진</p>
                    </div>
                    <div class="css-1qq59e8">
                        <div class="css-1kd6k5d">
                            <div class="css-9dnzub scroll">
                                <div class="css-174lxc3">
                                    <div class="css-119xxd7">
                                        <ul class="css-1ya1z7z-VisualUl">
                                            <li class="css-8y23cj">
                                                <c:forEach var="cast" begin="0" end="19" step="1" items="${cast}">
                                                    <a>
                                                        <div class="css-1qmeemv">
                                                            <div class="css-1rdb949-StyledLazyLoadingImage ezcopuc0">
                                                                <c:choose>
                                                                    <c:when test="${cast.profilePath eq 'default'}">
                                                                        <img src="/img/profile/default.png" style="width: 270px; height: 400px; object-fit:cover;">
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <img src="https://image.tmdb.org/t/p/w200${cast.profilePath}"  class="css-qhzw1o-StyledImg ezcopuc1">
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </div>
                                                        </div>
                                                        <div class="css-ixy093">
                                                            <div class="css-5yuqaa">${cast.name }</div>
                                                            <div class="average css-xgmur2"> </div>
                                                            <div class="css-u4moi6"></div>
                                                        </div>
                                                    </a>
                                                </c:forEach>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="css-148ng4f left" style="display: none;">
                                <button type="button" class="css-vp7uyl" style="margin-bottom: 60px;">
                                    <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMiIgaGVpZ2h0PSIxNiIgdmlld0JveD0iMCAwIDEyIDE2Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMEgxMlYxNkgweiIgdHJhbnNmb3JtPSJyb3RhdGUoMTgwIDYgOCkiLz4KICAgICAgICA8cGF0aCBmaWxsPSIjMjkyQTMyIiBzdHJva2U9IiMyOTJBMzIiIHN0cm9rZS13aWR0aD0iLjM1IiBkPSJNMy40MjkgMTMuNDA5TDQuMzU0IDE0LjI1OCAxMC42OCA4LjQ2IDExLjE0MyA4LjAzNiA0LjM1NCAxLjgxMyAzLjQyOSAyLjY2MiA5LjI5MSA4LjAzNnoiIHRyYW5zZm9ybT0icm90YXRlKDE4MCA2IDgpIi8+CiAgICA8L2c+Cjwvc3ZnPgo=" alt="forward">
                                </button>
                            </div>
                            <div class="css-147ng4f right">
                                <button type="button" class="css-vp7uyl" style="margin-bottom: 60px;">
                                    <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMiIgaGVpZ2h0PSIxNiIgdmlld0JveD0iMCAwIDEyIDE2Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMEgxMlYxNkgweiIvPgogICAgICAgIDxwYXRoIGZpbGw9IiMyOTJBMzIiIHN0cm9rZT0iIzI5MkEzMiIgc3Ryb2tlLXdpZHRoPSIuMzUiIGQ9Ik0zLjQyOSAxMy40MDlMNC4zNTQgMTQuMjU4IDEwLjY4IDguNDYgMTEuMTQzIDguMDM2IDQuMzU0IDEuODEzIDMuNDI5IDIuNjYyIDkuMjkxIDguMDM2eiIvPgogICAgPC9nPgo8L3N2Zz4K" alt="forward">
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>


        <section>
            <div class="css-lufi3b">
                <div class="css-pbseb6-StyledHomeListTitleRow">
                        <p class="css-16qa0p7">비슷한 작품</p>
                </div>
                <div class="css-1qq59e8">
                    <div class="css-1kd6k5d">
                        <div class="css-9dnzub scroll">
                            <div class="css-174lxc3">
                                <div class="css-119xxd7">
                                    <ul class="css-1ya1z7z-VisualUl">
                                        <li class="css-8y23cj">
                                            <c:forEach var="reco"  begin="0" end="19" step="1" items="${reco}">
                                            <a href="<c:url value='/movie/content/${reco.contentsNum}'/>">
                                                <div class="css-1qmeemv">
                                                    <div class=" css-1rdb949-StyledLazyLoadingImage ezcopuc0">
                                                        <img src="https://image.tmdb.org/t/p/w200${reco.posterPath}" class="css-qhzw1o-StyledImg ezcopuc1">
                                                    </div>
                                                </div>
                                                <div class="css-ixy093">
                                                    <div class="css-5yuqaa">${reco.title }</div>
                                                    <div class="average css-xgmur2"> </div>
                                                    <div class="css-u4moi6"> </div>
                                                </div>
                                            </a>
                                            </c:forEach>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="css-148ng4f left" style="display: none;">
                            <button type="button" class="css-vp7uyl" style="margin-bottom: 60px;">
                                <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMiIgaGVpZ2h0PSIxNiIgdmlld0JveD0iMCAwIDEyIDE2Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMEgxMlYxNkgweiIgdHJhbnNmb3JtPSJyb3RhdGUoMTgwIDYgOCkiLz4KICAgICAgICA8cGF0aCBmaWxsPSIjMjkyQTMyIiBzdHJva2U9IiMyOTJBMzIiIHN0cm9rZS13aWR0aD0iLjM1IiBkPSJNMy40MjkgMTMuNDA5TDQuMzU0IDE0LjI1OCAxMC42OCA4LjQ2IDExLjE0MyA4LjAzNiA0LjM1NCAxLjgxMyAzLjQyOSAyLjY2MiA5LjI5MSA4LjAzNnoiIHRyYW5zZm9ybT0icm90YXRlKDE4MCA2IDgpIi8+CiAgICA8L2c+Cjwvc3ZnPgo=" alt="forward">
                            </button>
                        </div>
                        <div class="css-147ng4f right">
                            <button type="button" class="css-vp7uyl" style="margin-bottom: 60px;">
                                <img src="data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHdpZHRoPSIxMiIgaGVpZ2h0PSIxNiIgdmlld0JveD0iMCAwIDEyIDE2Ij4KICAgIDxnIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+CiAgICAgICAgPHBhdGggZD0iTTAgMEgxMlYxNkgweiIvPgogICAgICAgIDxwYXRoIGZpbGw9IiMyOTJBMzIiIHN0cm9rZT0iIzI5MkEzMiIgc3Ryb2tlLXdpZHRoPSIuMzUiIGQ9Ik0zLjQyOSAxMy40MDlMNC4zNTQgMTQuMjU4IDEwLjY4IDguNDYgMTEuMTQzIDguMDM2IDQuMzU0IDEuODEzIDMuNDI5IDIuNjYyIDkuMjkxIDguMDM2eiIvPgogICAgPC9nPgo8L3N2Zz4K" alt="forward">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <div class="css-lufi3b">
            <div class="css-1qq59e8">
<%--                <div class="css-pbseb6-StyledHomeListTitleRow">--%>
                    <span class="css-16qa0p7" style="font-size: xx-large;">Review</span>
                    <div style="text-align: right; margin-right: 100px;">
                    <c:choose>
                        <c:when test="${ratingAvg == 0}"></c:when>
                        <c:otherwise>회원들이 평가한 점수 : <b>${ratingAvg}<b></c:otherwise>
                    </c:choose>
                    </div>

<%--                </div>--%>

                <div id="result" scroll=auto style="overflow-x:hidden; margin-bottom: 30px;" class="review-body">
                    <c:choose>
                        <c:when test="${empty reviews}">
                            <div class='review-items'>
                                <span style="font-family: Cambria; font-size: medium;" >아직 등록된 리뷰가 없습니다!</span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="review" items="${reviews}">
                                <div class='review-items' id="${review.no }">
                                    <img src="/img/profile/${review.img }" class="rounded-circle css-memImg" style="width: 3em; height:3em; margin-bottom: 3px;">
                                    <div style="display: inline-block; width: 1000px; height: 40px;">
                                        <span style="margin-left: 20px; font-family: Cambria; font-size: medium;"><b>${review.content}</b></span>
                                    </div>
                                    <div style="width:105px;">
                                        <c:choose>
                                            <c:when test="${review.rating eq '0'}">
                                                ☆☆☆☆☆
                                            </c:when>
                                            <c:when test="${review.rating eq '1'}">
                                                ★☆☆☆☆
                                            </c:when>
                                            <c:when test="${review.rating eq '2'}">
                                                ★★☆☆☆
                                            </c:when>
                                            <c:when test="${review.rating eq '3'}">
                                                ★★★☆☆
                                            </c:when>
                                            <c:when test="${review.rating eq '4'}">
                                                ★★★★☆
                                            </c:when>
                                            <c:when test="${review.rating eq '5'}">
                                                ★★★★★
                                            </c:when>
                                        </c:choose>
                                    </div>
                                    <div style="display: inline-block; width: 100px; height: 40px; text-align: end; margin-bottom: 12px; ">
                                        <span style="font-size: small; margin-bottom: 3px;">
                                                <fmt:formatDate value="${review.regdate }" pattern="MMM dd HH:mm:ss" /><br>by ${review.writer}
                                        </span>
                                    </div>
                                    <div style="display: inline-block; width: 40px; height: 40px;">
                                        <c:if test="${auth.name eq review.writer }">
                                            <button type="button" data-bs-toggle="modal" data-bs-target="#DelModal" data-test=${review.no }
                                                    class="btn btn-default btn-xs">&times;</button>
                                        </c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>



                </div>
                <c:choose>
                    <c:when test="${auth eq null}">
                        <div class="card">
                            <div class="card-header bi bi-chat-right-dots">
                                <b style="font-family: Cambria">Write a Comment</b>
                            </div>
                            <div class="card-body" style="font-size: small">
                                <a href="${pageContext.request.contextPath}/member/login">로그인</a>을 하시면 리뷰를 등록할 수 있습니다.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card">
                            <div class="card-header bi bi-chat-right-dots">
                                <b style="font-family: Cambria">Write a Comment</b>
                            </div>
                            <form:form action="${pageContext.request.contextPath}/addReply" modelAttribute="review" id="reviewForm" name="reviewForm"
                                onsubmit="return reviewFunction();">
                                <div class="row" id="row">
                                    <div class="col-lg-11">
                                        <textarea id="comment" name="content" placeholder="리뷰를 추가해주세요" style="resize: none; font-size: small;"></textarea>
                                        <div style="margin-left:20px; resize: none; font-size: small;">
                                            <form:label path="rating">평점: </form:label>
                                            <form:select path="rating" id="rating" name="rating" style="width: 105px;" itemValue="">
                                                <form:options items="${ratingOptions}" />
                                            </form:select>
                                        </div>
                                    </div>
                                    <div class="col-lg-1" id="divbtn">
                                        <button type="submit" id="btn-comment-save" class="btn btn-outline-primary bi bi-pencil-square">등록</button>
                                    </div>

                                    <input type="hidden" name="contentsNum" value="${contents.contentsNum}">
                                    <input type="hidden" name="writer" value="${auth.name }">
                                    <input type="hidden" name="title" value="${contents.title}">
                                    <input type="hidden" name="code" value="${code }">
                                    <input type="hidden" name="img" value="${auth.imgs }">
                                </div>
                            </form:form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>





    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>




    <!-- 삭제 확인 모달 -->
    <div class="modal" id="DelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="font-family: Georgia"><b>리뷰를 삭제 하시겠습니까?</b></h5>
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

    <%--<input type="hidden" id="userId2" name="userId" value="<sec:authentication property="principal.username">">--%>
    <%--<input type="hidden" id="userId" value="${auth.id}">--%>

    <!-- 아래 부터는 script -->
    <input type="hidden" id="contentsNum" value="${contents.contentsNum}">
    <input type="hidden" id="userName" value="${auth.name }">
    <input type="hidden" id="title" value="${contents.title}">
    <input type="hidden" id="userId" value="${auth.id}">
    <input type="hidden" id="overview" value="${contents.overview}">
    <input type="hidden" id="posterPath" value="${contents.posterPath}">
    <input type="hidden" id="flag" value="">

    <script src="${pageContext.request.contextPath}/js/hosun/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/scroll.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(document).ready(function(){
            $('#DelModal').on('show.bs.modal', function (e) {
                var data = $(e.relatedTarget).data('test');
                $('#deleteModal').val(data);
                console.log(data);
            });
        });

        window.onload=function(){
            let hdiv = document.getElementById('emptyReview');
            if(hdiv == null)
                flag = true;

            let userId = $('#userId').val();
            let contentsNum = $('#contentsNum').val();
            let object1 = {
                'contentsNum': contentsNum,
                'userId': userId,
                'code': '0'
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
                            document.querySelector("#ex").src='https://img.icons8.com/color/452/hearts.png';
                        }
                        if (data == 'false') {
                            document.querySelector("#ex").src='https://img.icons8.com/ios/500/hearts--v1.png';
                        }
                        // return flag;
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
                    'posterPath': posterPath,
                    'userId': userId,
                    'code': '0'
                }
                $
                    .ajax({
                        url: '../../like',
                        type: 'post',
                        // dataType:"json",
                        data: {
                            object: JSON.stringify(object2),
                            'flag': flag
                        },
                        success: function(data){
                            console.log("likeController 동작 성공: "+data);
                            //버튼 누르면 컬러 바꾸기..
                            if(data == 'true') {
                                document.querySelector("#ex").src='https://img.icons8.com/color/452/hearts.png';
                            } else {
                                document.querySelector("#ex").src='https://img.icons8.com/ios/500/hearts--v1.png';
                            }
                        }, error: function(request,error){
                            console.log("likeController 동작 fail");
                            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
            }
        }
        $("#delete").click(function(){
            var d = $('#deleteModal').val()
            console.log(d);
            deleteReply(d);
        });

        function deleteReply(i){
            $.ajax({
                url: '../../../deleteReply',
                type: 'delete',
                data:{
                    'pk':i
                },success:function (data){
                    console.log("Delete Success");
                    let deleteDiv = document.getElementById(i);
                    deleteDiv.remove();
                },error:function (){
                    console.log("fail");
                }
            });
        }
        function reviewFunction(){
            let star = $("#rating").val();
            let comment = $("#comment").val();

            if(!star || star=="" || !comment || comment==""){
                alert("리뷰를 작성해주세요.");
                return false;
            }else{
                return true;
            }
        }

        // var resultHTML="";
        // $(".btn").click(function () {
        //     addReview();
        //     console.log("click");
        // });
        //
        // function addReview(){
        //     /* insertReview(); <- db와 통신 할 ajax 함수 */
        //     let userName = $('#userName').val();
        //     let contentsNum = $('#contentsNum').val();
        //     let content = $('#comment').val();
        //     let code = 0;
        //     let title = $('#title').val();
        //     var object3 = {
        //         'userName':userName,
        //         'title':title,
        //         'contentsNum':contentsNum,
        //         'content':content,
        //         'code':code
        //     }
        //     $
        //         .ajax({
        //             url: '../../addReply',
        //             type: 'post',
        //             data: {
        //                 object: JSON.stringify(object3)
        //             },
        //             dataType: "json",
        //             success: function(data) {
        //                 if(!flag)
        //                     hideDiv();
        //                 console.log("addReply 컨트롤러 동작 성공: " + data);
        //
        //                 d = document.getElementById('result');
        //                 n = document.createElement('div');
        //                 n.setAttribute('class', 'review-items');
        //                 n.innerHTML = data.content+"<br>"+data.writer +"<br></div>";
        //                 d.appendChild(n);
        //
        //                 console.log("eee")
        //             }, error : function(request, error) {
        //                 console.log("addReply 컨트롤러 동작 fail");
        //                 alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        //             }
        //         });
        // }
        //
        // function hideDiv(){
        //     const notLogin = document.getElementById('emptyReview');
        //     notLogin.style.display = 'none';
        // }
    </script>

</body>
</html>
