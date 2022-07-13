<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-13
  Time: AM 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/hosun/main.css" rel="stylesheet"/>
</head>
<body>

    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"/>
    <br>
    userid = ${id }


</body>
</html>
