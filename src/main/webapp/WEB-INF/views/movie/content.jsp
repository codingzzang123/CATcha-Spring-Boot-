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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/jieun/contentList.css" rel="stylesheet"/>
<%--    <link href="/css/jieun/content.css" rel="stylesheet"/>--%>

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
            border-radius: 2em;
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

        .review-items{
            margin-top: 12px;
            height: 4em;
            width: 1250px;
            display: inline-block;
            border-radius: 2em;
            border: 1px dotted rgb(27, 26, 26);
            margin-left: 20px;
            padding-left: 20px;
            padding-top: 5px;
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
                <div class="col-lg-4 css-leftImg">
                    <c:choose>
                        <c:when test="${contents.posterPath eq 'default'}">
                            <img src="/img/media/cinema_default.png" width="300">
                        </c:when>
                        <c:otherwise>
                            <img src="https://image.tmdb.org/t/p/w300${contents.posterPath}">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-lg-8 css-right-text">
                    <div class="contentsTitle">

                        <span class="css-16qa0p7" style="font-size: xx-large;"> ${contents.title}</span>
                        <c:choose>
                            <c:when test="${auth eq null}"></c:when>
                            <c:otherwise>
                                <button type="button" id="b1" onclick="like()"><img src="" style="width: 50px; height: 50px; cursor:pointer; border:0px;" onclick="" id="ex"></button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="contentsOthers">
                        Release Date :
                        <fmt:formatDate value="${contents.releaseDate}" pattern="yyyy-MM-dd"/><br>
                        장르 :
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
                        상영시간 :
                        <c:choose>
                            <c:when test="${contents.runtime eq '0'}">정보가 없습니다.</c:when>
                            <c:otherwise>${contents.hour}h ${contents.minute}m</c:otherwise>
                        </c:choose>
                    </div>
                    <div style="margin-top: 70px;">
                        <br> Overview <br>
                        <div class="contentsOverview" style="border: 1px solid black; border-radius: 15px; padding:15px">
                            ${contents.overview}
                        </div>
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

        <div class="css-lufi3b">
            <div class="css-1qq59e8">
                <div class="css-pbseb6-StyledHomeListTitleRow">
                    <p class="css-16qa0p7" style="font-size: xx-large;">Review</p>
                </div>

                <div id="result" scroll=auto style="overflow-x:hidden; margin-bottom: 30px;" class="review-body">
                    <c:choose>
                        <c:when test="${contentReply eq '[]'}">
                            <div id="emptyReview">
                                <span style="font-family: Cambria; font-size: larger;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;등록된 리뷰가 없습니다!</span>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="contentReply" items="${contentReply}">
                                <div class='review-items'>
                                        ${contentReply.content}<br>
                                        ${contentReply.writer}<br>
                                </div><br>
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
                                <a href="#">로그인</a>을 하시면 리뷰를 등록할 수 있습니다.
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="card">
                            <div class="card-header bi bi-chat-right-dots">
                                <b style="font-family: Cambria">Write a Comment</b>
                            </div>
                            <form>
                                <div class="row" id="row">
                                    <form>
                                        <div class="col-lg-11">
                                            <textarea id="comment" placeholder="리뷰를 추가해주세요" style="resize: none; font-size: small"></textarea>
                                        </div>
                                        <div class="col-lg-1" id="divbtn">
                                            <button type="button" id="btn-comment-save"class="btn btn-outline-primary bi bi-pencil-square"> 등록</button>
                                        </div>
                                    </form>
                                </div>
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>







    <!-- 아래 부터는 script -->
    <input type="hidden" id="contentsNum" value="${contents.contentsNum}">
    <%--<input type="hidden" id="userId2" name="userId" value="<sec:authentication property="principal.username">">--%>
    <%--<input type="hidden" id="userId" value="${auth.id}">--%>
    <input type="hidden" id="userName" value="${auth.name }">
    <input type="hidden" id="userId" value="${auth.id}">
    <input type="hidden" id="title" value="${contents.title}">
    <input type="hidden" id="overview" value="${contents.overview}">
    <input type="hidden" id="posterPath" value="${contents.posterPath}">
    <input type="hidden" id="flag" value="">

    <script src="${pageContext.request.contextPath}/js/hosun/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/main.js"></script>
    <script src="${pageContext.request.contextPath}/js/hosun/scroll.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        var flag=false;

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
                            document.getElementById("b1").style.background="grey"
                            document.getElementById("b1").style.border="#ffffff";
                            document.querySelector("#ex").src='https://img.icons8.com/ios/100/add-bookmark.png';
                        }
                        if (data == 'false') {
                            document.getElementById("b1").style.background="#ffffff";
                            document.getElementById("b1").style.border="#ffffff";
                            document.querySelector("#ex").src='https://img.icons8.com/ios/100/add-bookmark.png';
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
                                document.getElementById("b1").style.background='grey';
                                document.getElementById("b1").style.border="#ffffff";
                                document.querySelector("#ex").src='https://img.icons8.com/ios/100/add-bookmark.png';
                            } else {
                                document.getElementById("b1").style.background="#ffffff";
                                document.getElementById("b1").style.border="#ffffff";
                                document.querySelector("#ex").src='https://img.icons8.com/ios/100/add-bookmark.png';
                            }
                        }, error: function(request,error){
                            console.log("likeController 동작 fail");
                            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                        }
                    });
            }
        }
        var resultHTML="";
        $(".btn").click(function () {
            addReview();
            console.log("click");
        });

        function addReview(){
            /* insertReview(); <- db와 통신 할 ajax 함수 */
            let userName = $('#userName').val();
            let contentsNum = $('#contentsNum').val();
            let content = $('#comment').val();
            let code = 0;
            let title = $('#title').val();
            var object3 = {
                'userName':userName,
                'title':title,
                'contentsNum':contentsNum,
                'content':content,
                'code':code
            }
            $
                .ajax({
                    url: '../../addReply',
                    type: 'post',
                    data: {
                        object: JSON.stringify(object3)
                    },
                    dataType: "json",
                    success: function(data) {
                        if(!flag)
                            hideDiv();
                        console.log("addReply 컨트롤러 동작 성공: " + data);

                        d = document.getElementById('result');
                        n = document.createElement('div');
                        n.setAttribute('class', 'review-items');
                        n.innerHTML = data.content+"<br>"+data.writer +"<br></div>";
                        d.appendChild(n);

                        console.log("eee")
                    }, error : function(request, error) {
                        console.log("addReply 컨트롤러 동작 fail");
                        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
                });
        }

        function hideDiv(){
            const notLogin = document.getElementById('emptyReview');
            notLogin.style.display = 'none';
        }
    </script>
</body>
</html>
