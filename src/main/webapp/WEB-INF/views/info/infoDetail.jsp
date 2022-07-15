<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/hosun/main.css" rel="stylesheet"/>
</head>
<body>
<table class="table table-hover">
    <thead>
    <tr>
        <th scope="col">글 번호</th>
        <th scope="col">제목</th>
        <th scope="col">내용</th>
        <th scope="col">작성자</th>
        <th scope="col">작성일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="content" items="${content }">
        <tr>
            <td>${content.no }</td>
            <td>${content.title }</td>
            <td>${content.content }</td>
            <td>${content.name }</td>
            <td>${content.regdate }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


    <nav aria-label="Page navigation example">
        <ul class="pagination">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item"><a class="page-link" href="#">Next</a></li>
        </ul>
    </nav>

</body>
</html>
