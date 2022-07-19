<%--
  Created by IntelliJ IDEA.
  User: yaele
  Date: 2022-07-15
  Time: 오전 11:48
  To change this template use File | Settings | File Templates.
--%><%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="container mx-auto">
    <h1 class="text-2xl mb-10 text-center">Title of Board</h1>
    <form method="GET" action=/board/write">

        <div class="tables">
            <table class="board">
                <tr>
                    <th>번호</th>
                    <th>카테고리</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성일</th>
                    <th>조회</th>
                    <th>추천</th>
                </tr>
        <c:forEach var="board" items="${list }" varStatus="no">
            <tr>
                <td>${no.count}</td>
                <td>${board.cate }</td>
                <td><a href="<c:url value="/board/${board.no}"/>">${board.title }</a></td>
                <td>${board.name }</td>
                <td><fmt:formatDate value="${board.regdate }" pattern="yyyy-MM-dd"/></td>
                <td>${board.views }
                <td>${board.likes }</td>
            </tr>
        </c:forEach>
            </table>
        </div>


        <div class="board_footer container mx-auto ">
            <div class="mt-6 flex justify-end">
                <a class="inline-block border border-gray-500 bg-gray-500 text-white rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline" href="/board/write">게시물 작성</a>
            </div>
        </div>
    </form>
</body>

</html>

<style>
    * {
        font-family: 'Noto Sans KR', sans-serif;
        color:#333;
    }
    .board {
        width:100%;
    }
    .board tr td:nth-child(1){
        width: 100px
    }
    .board tr td:nth-child(2){
        width: 200px
    }
    .board tr td:nth-child(3){
        /* width: 100px */
    }
    .board tr td:nth-child(4){
        width: 150px
    }
    .board tr td:nth-child(5){
        width: 150px
    }
    .board tr td:nth-child(6){
        width: 80px
    }
    .board tr td:nth-child(7){
        width: 80px
    }
    .board tr th {
        padding:1rem;
        background: #eee;
        border:1px solid #ddd
    }
    .board tr td {
        border:1px solid #ddd;
        padding:1rem;
        text-align: center;
    }
    .page_navigation {
        display: flex;
    }
    .page_navigation a {
        background: #eee;
        padding:1.5rem;
        color:#111;
        margin:0 0.5rem;
        display: flex;;
        justify-content: center;
        align-items: center;
        width:1.5rem;
        height: 1.5rem;

    }
    .page_navigation a.active {
        background:#2563eb;
        color:white
    }
</style>