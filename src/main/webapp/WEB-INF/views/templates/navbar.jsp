<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="/css/hosun/nav.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <style>
        * {
            font-family: Cambria;
        }
    </style>
</head>

<header class="css-6k8tqb" id="header">
    <nav>
        <div class="css-1gkas1x-Grid e1689zdh0">
            <div class="css-1djzg97">
                <ul class="css-6v7b8v-VisualUl">
                    <li>
                        <a href="${pageContext.request.contextPath }/main">
                            <img src="/img/main/logo.jpg" height="42px;">
                        </a>

                    </li>

                    <li class="css-bj71cw" style="font-size: large; color: black;">CATcha</li>

                    <li class="categories css-bj71cw">
                        <a class="nav-link active customLi" href="${pageContext.request.contextPath }/main">Home</a>
                    </li>

                    <li class="categories">
                        <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Movie</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/movie/list?category=popularity.desc">인기</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/movie/list?category=vote_average.desc">높은 평점</a></li>
                        </ul>
                    </li>


                    <li class="categories">
                        <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Tv</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/tv/list?category=popularity.desc">인기</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/tv/list?category=vote_average.desc">높은 평점</a></li>
                        </ul>
                    </li>

                    <li class="categories">
                        <a class="nav-link active customLi" href="${pageContext.request.contextPath }/board">Board</a>
                    </li>

                    <li class="css-1c3wwgb">
                        <div class="css-1xm32e0">
                            <div class="css-1okeg7p">
                                <form action="<c:url value="/search"/>" method="get" onsubmit="return submit()">
                                    <label class="css-kyr608" id="label">
                                        <div class="search">
                                            <input type="text" autocomplete="off" placeholder="다양한 콘텐츠를 검색해보세요." name="query" class="css-13i5xe6" id="keyword_header">
                                        </div>
                                        <div value="false" class="css-ikj5sk">
                                            <span aria-label="clear" role="button" class="css-14vwb0g"></span>
                                        </div>
                                    </label>
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
                                    </script>
                                </form>
                            </div>
                        </div>
                    </li>

                    <sec:authorize access="!isAuthenticated()">
                        <li class="css-bj71cw">
                            <a href="${pageContext.request.contextPath }/member/login" >
                                <button id="login-btn" type="button" class="css-fn0ezc-StylelessButton">로그인</button>
                            </a>
                        </li>

                        <li class="css-bj71cw">
                            <a class="css-memberButton" href="${pageContext.request.contextPath }/member/sign" >
                                <button id="login-btn" type="button" class="css-fn0ezc-StylelessButton">회원가입</button>
                            </a>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="alert" data-bs-toggle="dropdown" aria-expanded="true">
                                <img src="https://cdn-icons-png.flaticon.com/512/3602/3602145.png" height="36px;">
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <div style="width: auto; height: auto;  text-align: center;">
                                    <div>확인하지 않은 알림</div>
                                    <div style="background-color: antiquewhite">이 부분에 알림 목록이 들어갈 예정</div>
                                    <button type="button" class="btn btn-primary">확인</button>
                                </div>
                            </ul>
                        </li>


                        <li>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="/img/profile/${auth.imgs}" class="rounded-circle" height="45px;" width="45px;">
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
                    </sec:authorize>

                </ul>
            </div>
        </div>
    </nav>
</header>
