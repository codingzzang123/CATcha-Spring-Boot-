<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-12
  Time: PM 1:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="js/hosun/jquery-3.6.0.min.js"></script>
    <script src="js/hosun/main.js"></script>
    <script src="js/hosun/scroll.js"></script>

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <style>
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
<body class="text-center">
    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <br><br><br>

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
            <div id="result"><!-- 리뷰 들이 들어갈 공간 --></div>
        </div>

        <br><br><br><br>

        <!-- 1. 로그인 했을 때 -->
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

        <br><br><br><br>

        <!-- 2. 로그인 안 했을 때 -->
        <div class="card">
            <div class="card-header bi bi-chat-right-dots">
                <b style="font-family: Cambria">Write a Comment</b>
            </div>
            <div class="card-body" style="font-size: small">
                <a href="#">로그인</a>을 하시면 리뷰를 등록할 수 있습니다.
            </div>
        </div>
    </section>

    <br><br><br><br>

    <script>
        var resultHTML="";

        $(".btn").click(function () {
           addReview();
           console.log("click");
        });

        function addReview(){
            resultHTML += "<div class='review-items'>1</div>"
            /* insertReview(); <- db와 통신 할 ajax 함수 */
            document.querySelector('#result').innerHTML = resultHTML;
        }
    </script>
</body>
</html>
