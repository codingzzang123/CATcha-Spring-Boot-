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
    <link href="/css/jieun/content.css" rel="stylesheet"/>

    <title>컨텐츠 디테일 화면</title>

    <style>
        * {
            margin: 0;
            padding:0;
        }
        .container {
            /*너비 고정 and 중앙 정렬*/
            width: 960px;
            margin: 0 auto;
            padding-top: 10px;

            /*그리드 구성하기*/
            display: grid;
            grid-template: 5px 1fr 10px / 400px 1fr;
            grid-gap: 3px;
        }
        [class *= cell] {
            box-sizing: border-box;
            border: none;
            padding: 10px;
            border-radius: 10px;
        }
        .cell-header {
            grid-column: 1/4;
        }
        /*  리뷰  CSS  */
        .card{
            width: 1250px;
            text-align: left;
        }
        .css-7klu3x .card{
            margin: auto;
        }

        #divbtn .btn{
            margin: auto;
        }

        textarea {
            width: 100%;
            height: 3em;
            border: none;
            resize: none;
            padding-left: 16px;
        }

        .review-body{
            margin: auto;
            margin-top: 10px;
            width: 1300px;
            background:url('https://png.pngtree.com/thumb_back/fh260/back_our/20190625/ourmid/pngtree-gray-green-fresh-solid-background-image_258276.jpg');
            height: 400px;
            border-radius: 2em;
            border: 1px dotted red;
        }

        .review-items{
            margin-top: 12px;
            height: 4em;
            width: 1250px;
            display: inline-block;
            border-radius: 2em;
            border: 1px dotted rgb(27, 26, 26);
            margin-left: 12px;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <br><br>
    <div class="container" style="margin:auto">
        <div class="cell-header">
        </div>
        <div class="cell-aside" style="padding:5px">
            <c:choose>
                <c:when test="${contents.posterPath eq 'default'}">
                    <img src="/img/media/cinema_default.png" width="300">
                </c:when>
                <c:otherwise>
                    <img src="https://image.tmdb.org/t/p/w300${contents.posterPath}">
                </c:otherwise>
            </c:choose>

        </div>
        <br>
        <div class="cell-content" style="padding:40px">
            <div class="contentsTitle">
                <p class="css-16qa0p7">
                    ${contents.title}</p>
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
            <br> Overview <br><br>
            <div class="contentsOverview" style="border: 1px solid black; border-radius: 15px; padding:15px">
                ${contents.overview}
            </div>
        </div>
        <br>
    </div>

    <c:choose>
        <c:when test="${empty cast}">
                     출연진 정보가 없습니다.
        </c:when>
        <c:otherwise>



    <section class="css-7klu3x">
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
                                                                <img src="https://image.tmdb.org/t/p/w200${cast.profilePath}" class="css-qhzw1o-StyledImg ezcopuc1">
                                                            </c:otherwise>
                                                            </c:choose>

                                                    </div>
                                                </div>
                                                <div class="css-ixy093">
                                                    <div class="css-5yuqaa">${cast.name }</div>
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

        </c:otherwise>
    </c:choose>


<br>

    <section class="css-7klu3x">
        <div class="css-lufi3b">
            <div class="css-pbseb6-StyledHomeListTitleRow">
                <p class="css-16qa0p7">test</p>
            </div>
            <div class="css-1qq59e8">
                <div class="css-1kd6k5d">
                    <div class="css-9dnzub scroll">
                        <div class="css-174lxc3">
                            <div class="css-119xxd7">
                                <ul class="css-1ya1z7z-VisualUl">
                                    <li class="css-8y23cj">
                                        <1>
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

        <div class="review-body">


            <div id="result">

                <c:choose>
                    <c:when test="${contentReply eq null}">
                        <div class='review-items'>등록된 리뷰가 없습니다!</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="contentReply" items="${contentReply}">
                            <div class='review-items'>
                                    ${contentReply.content}<br>
                                    ${contentReply.writer}<br>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

        <br><br>
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
                            <div class="col-lg-11">
                                <textarea id="comment" placeholder="리뷰를 추가해주세요" style="resize: none; font-size: small"></textarea>
                            </div>
                            <div class="col-lg-1" id="divbtn">
                                <button type="button" id="btn-comment-save"class="btn btn-outline-primary bi bi-pencil-square"> 등록</button>
                            </div>
                        </div>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </section>





<input type="hidden" id="contentsNum" value="${contents.contentsNum}">
<%--<input type="hidden" id="userId2" name="userId" value="<sec:authentication property="principal.username">">--%>
<%--<input type="hidden" id="userId" value="${auth.id}">--%>
<input type="hidden" id="userId" value="${auth.id }">
<input type="hidden" id="title" value="${contents.title}">
<input type="hidden" id="overview" value="${contents.overview}">
<input type="hidden" id="posterPath" value="${contents.posterPath}">
<input type="hidden" id="flag" value="">

<script src="${pageContext.request.contextPath}/js/hosun/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/hosun/main.js"></script>
<script src="${pageContext.request.contextPath}/js/hosun/scroll.js"></script>
<%--<script src="${pageContext.request.contextPath}/js/jieun/mediaLike.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

    window.onload=function(){

        let userId = $('#userId').val();
        let contentsNum = $('#contentsNum').val();
        // let flag = $('#flag').val();

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
        // let flag = $('#flag').val();
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
        resultHTML += "<div class='review-items'>${contentReply.content}<br>${contentReply.writer}<br></div>"

        /* insertReview(); <- db와 통신 할 ajax 함수 */
        let writer = $('#userId').val();
        let contentsNum = $('#contentsNum').val();
        let content = $('#comment').val();
        let code = 0;

        var object3 = {
            'writer':writer,
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
                success: function(data) {
                    console.log("addReply 컨트롤러 동작 성공: " + data);

                    for (let i=0; i<data.length; i++) {
                        resultHTML += "<div class='review-items'>" +
                            data[i].content + "<br>" +
                            data[i].writer + "<br>" +
                            "</div>";
                    }


                    document.querySelector('#result').innerHTML = resultHTML;
                }, error : function(request, error) {
                    console.log("addReply 컨트롤러 동작 fail");
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            });
    }


    </script>
</body>
</html>
