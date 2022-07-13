<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-10
  Time: 오후 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="/css/hosun/navbar.css" rel="stylesheet"/>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light customNav">
    <div class="navbar__logo">
        <a href="${pageContext.request.contextPath }/main">
            <img src="/img/main/logo.jpg" height="42px;">
        </a>
        <span style="margin-left: 1em;">CATcha</span>
    </div>

    <ul class="navbar__menu navbar-nav me-auto customUl">
        <li><a class="nav-link active customLi" href="${pageContext.request.contextPath }/main"><b>Home</b></a></li>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Movie</a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item fontKo" href="${pageContext.request.contextPath }/movie/list?category=popularity.desc">인기</a></li>
                <li><a class="dropdown-item fontKo" href="${pageContext.request.contextPath }/movie/list?category=vote_average.desc">높은 평점</a></li>
            </ul>
        </li>

        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Tv</a>
            <ul class="dropdown-menu fontKo" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item fontKo" href="${pageContext.request.contextPath }/tv/list?category=popularity.desc">인기</a></li>
                <li><a class="dropdown-item fontKo" href="${pageContext.request.contextPath }/tv/list?category=vote_average.desc">높은 평점</a></li>
            </ul>
        </li>

        <li><a class="nav-link active customLi" href="${pageContext.request.contextPath }/board"><b>Board</b></a></li>
    </ul>
    <ul class="navbar__menu css-member">
        <li class="nav-link active">

            <form action="<c:url value="/search"/>" method="get" onsubmit="return submit()">
                <div class="search">
                    <input class="css-searchBar" type="text" name="query" placeholder="검색어 입력">
                    <img class="css-searchImg" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
                </div>
            </form>
            <script>
                function submit(){
                    let val = $("input[name='query']").val();
                    searchForm.find("input[name='query']").val(val);

                    if(val != null){
                        return true;
                    }else{
                        return false;
                    }
                }
            </script
        </li>
        <c:choose>
            <c:when test="${auth eq null}">
                <li class="nav-link active">
                    <a class="css-memberButton" href="${pageContext.request.contextPath }/member/login" >로그인</a>
                    <a class="css-memberButton" href="${pageContext.request.contextPath }/member/sign" >회원가입</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="alert" data-bs-toggle="dropdown" aria-expanded="true">
                        <img src="https://cdn-icons-png.flaticon.com/512/3602/3602145.png" class="css-alert">
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <div style="width: auto; height: auto;  text-align: center;">
                                <div>확인하지 않은 알림</div>
                                <div style="background-color: antiquewhite">이 부분에 알림 목록이 들어갈 예정</div>
                                <button type="button" class="btn btn-primary">확인</button>
                            </div>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="/img/profile/${auth.imgs}" class="rounded-circle css-profile">
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="">${auth.name}</a></li>
                        <li><a class="dropdown-item" href="#">프로필 보기</a></li>
                        <hr>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/memberlike/${auth.id}">관심목록</a></li>
                        <li><a class="dropdown-item" href="">설정</a></li>
                        <hr>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
                    </ul>
                </li>
            </c:otherwise>
        </c:choose>


    </ul>
</nav>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
