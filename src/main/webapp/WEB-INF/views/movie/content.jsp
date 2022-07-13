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
            border: 1px solid black;
            padding: 10px;
            border-radius: 10px;
        }
        .cell-header {
            grid-column: 1/4;
        }

        .css-7klu3x {
            grid-column: 1/3;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
<br><br>
<div class="container">
    <div class="cell-header">
    </div>
    <div class="cell-aside">
        <img src="https://image.tmdb.org/t/p/w200${contents.posterPath}"></div>
<br>
<div class="cell-content" style="background-color: #cccccc">
    <div class="contentsTitle" style="background-color: antiquewhite">
        ${contents.title}
        <button type="button" id="b1" onclick="like()">보고싶어요</button>
    </div>
    <div class="contentsOthers" style="background-color: aquamarine">
        <fmt:formatDate value="${contents.releaseDate}" pattern="yyyy-MM-dd"/>
        <c:forEach var="genres" items="${contents.ls}">
            ${genres.genreName}
        </c:forEach>
        ${contents.hour}h${contents.minute}m</div>
    <br>
    <div class="contentsOverview" style="background-color: burlywood">
        ${contents.overview}</div>
</div>
<br>
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

                                    <div class="css-1qmeemv">
                                        <div class="css-1rdb949-StyledLazyLoadingImage ezcopuc0">
                                            <img src="https://image.tmdb.org/t/p/w200${cast.profilePath}" class="css-qhzw1o-StyledImg ezcopuc1">
                                        </div>
                                    </div>
                                    <div class="css-ixy093">
                                        <div class="css-5yuqaa">${cast.name }</div>
                                    </div>

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
</div>



<br>
<input type="hidden" id="contentsNum" value="${contents.contentsNum}">
<%--<input type="hidden" id="userId2" name="userId" value="<sec:authentication property="principal.username">">--%>
<%--<input type="hidden" id="userId" value="${auth.id}">--%>
<input type="hidden" id="userId" value="test1234">
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
                    document.getElementById("b1").style.background='red';
                }
                if (data == 'false') {
                    document.getElementById("b1").style.background="#ffffff";
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
                        document.getElementById("b1").style.background='red';
                    } else {
                        document.getElementById("b1").style.background="#ffffff";
                    }
                }, error: function(request,error){
                    console.log("likeController 동작 fail");
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                }
            });

        }
    }

    </script>
<%--    id : ${auth.id}--%>
<%--&lt;%&ndash;    id2 : <sec:authentication property="principal.username"/>&ndash;%&gt;--%>
<%--    <br>flag : ${flag}--%>

</body>
</html>
