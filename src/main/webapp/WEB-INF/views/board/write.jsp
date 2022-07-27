<%--
  Created by IntelliJ IDEA.
  User: yaele
  Date: 2022-07-15
  Time: 오후 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet">
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/img/main/title.png" rel="shortcut icon" type="image/x-icon"><title>CATcha</title>

</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include> <br><br><br>
    <div class="container mx-auto">
<%--        <h1 class="text-2xl mb-10 text-left">Title of Board</h1>--%>
        <div style="margin-top:50px;">
                <span style="font-style: italic; font-family: Inter; border: 1px dotted #d3d3d3;
                    font-size: xxx-large; padding-left: 10px; padding-right: 15px; border-radius: 2em; ">CATcha Community</span>
        </div>
        <hr>
    <div class="row" style="margin-top:20px; margin-bottom: 25px;">
        <form modelAttribute="writeForm" method="POST" action="/board/write" enctype="multipart/form-data"
        onsubmit="return writeCheck();">
            <div class="board_wrap" style="margin-top:20px; margin-bottom: 25px;">
                <div class="flex justify-start items-center gap-5">
                    카테고리
                    <div class="input-group mb-3">

                        <select name="cate" id="cate" class="p-1 w-48" aria-label="Default select example" style="width: 10%">
                            <option value="일반">일반</option>
                            <option value="영화">영화</option>
                            <option value="TV">TV</option>
                        </select>
                    </div>
                </div>
                <input type="hidden" value="${auth.name }" name="name">
<%--                <div class="flex w-2/3 justify-start mt-5">--%>
<%--                    <input type="text"  class="p-2 w-full" id="title" name="title" placeholder="제목을 입력해주세요" minlength="5" style="width: 60%;">--%>
<%--                </div>--%>

                <div class="input-group mb-3">
                    <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력해주세요" minlength="5" >
                </div>

<%--                <div class="mt-5 flex justify-center items-center">--%>
<%--                    <textarea class="w-full board_write p-5" name="content" id="exampleFormControlTextarea1" cols="100" rows="10" minlength="10"></textarea>--%>
<%--                </div>--%>
                <div>
                    <textarea class="form-control" name="content" id="exampleFormControlTextarea1" placeholder="내용을 입력해주세요"
                              cols="100" rows="10" minlength="10" style="height: 400px; resize:none; "></textarea>
                </div>

<%--                <div class="flex w-2/3 justify-start mt-5">--%>
<%--                    <input type="file" name="file">--%>
<%--                </div>--%>
            </div>
<%--                <div class="flex justify-end mt-5">--%>
<%--                    <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-black rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center"--%>
<%--                       href="${pageContext.request.contextPath}/board">취소</a>--%>
<%--&lt;%&ndash;                    <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-black rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center"&ndash;%&gt;--%>
<%--&lt;%&ndash;                       href='${pageContext.request.contextPath}/board/detail/${board.no}'>등록</a>&ndash;%&gt;--%>
<%--                    <button type="submit" class="btn btn-primary">등록</button>--%>
<%--                </div>--%>
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <a class="btn btn-outline-secondary" type="button" href="${pageContext.request.contextPath}/board">취소</a>
                <button class="btn btn-outline-primary" type="submit">등록</button>
            </div>
        </form>
    </div>
    </div>

</body>
</html>

<style>
    * {
        font-family: 'Noto Sans KR', sans-serif;
        color:#333;
    }
    .board_wrap {
        background: #f3f3f1;
        padding:1rem; ;
    }
    .board_write::placeholder{
        display: flex; justify-content: center; align-items: center;
        text-align: center;
        vertical-align: center;
        padding-top:100px;
    }

</style>

<script>
    function writeCheck(){
        let title = $("#title").val();
        let content = $("#content").val();

        if(!title || title=="" || title.length < 5) {
            alert("제목을 5글자 이상 입력해주세요");
            return false;
        }

        if(content=="") {
            alert("내용을 입력해주세요");
            return false;
        }

        if(title.length > 25) {
            alert("제목은 25글자 이내로 작성해주세요");
            return false;
        }

        if(content.length > 200) {
            alert("내용은 200글자 이내로 작성해주세요");
            return false;
        }

        return true;

    }
</script>