<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <link href="/css/hosun/info.css" rel="stylesheet"/>
</head>

<section>
    <div class="css-Like-header css-lufi3b" style="align-items:center; display: flex; justify-content:left;">
        <a href="${pageContext.request.contextPath }/member">
            <img src="/img/profile/${auth.imgs}" class="rounded-circle css-memImg" style="margin-left: 30px;">
        </a>

        <div style="margin-left: 30px;">
            <div>
                <span class="css-16qa0p7" style="font-size: xx-large">${auth.name}</span>
            </div>
            <div style="margin-top: 30px;">
                    <span class="css-16qa0p7">
                        <fmt:formatDate value="${auth.regdate }" pattern="yyyy년 MM월 dd일" /> 부터 회원
                    </span>
            </div>
        </div>
    </div>
</section>