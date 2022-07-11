<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-10
  Time: PM 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            text-align:center;
        }
        .test{width: 60px; height: 100px;}
        tr{vertical-align: middle;}
    </style>

    <link href="/css/jieun/paging.css" rel="stylesheet"/>
</head>
<body class="text-center">
<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"></jsp:include>
<c:set var="Image_URL" value="https://image.tmdb.org/t/p/original"/>
<br><br><br>
<span>🔎 Search by ${query }, SearchType : ${type }</span>


    <span>
		<a href="<c:url value='/search/movie?query=${query }&page=1'/>">
			<button>영화 : ${scd.movie }개</button>
		</a>
	</span>

    <span>
		<a href="<c:url value='/search/tv?query=${query }&page=1'/>">
			<button>TV : ${scd.tv }개</button>
		</a>
	</span>

    <span>
		<a href="<c:url value='/search/board?query=${query }&page=1'/>">
			<button>게시판 : ${scd.board }개</button>
		</a>
	</span>

<br><br>
<div class="container mt-5" style="width:100%">
    <c:choose>
        <c:when test="${type eq 'board' }"> <!-- 게시판 -->
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
        </c:when>


        <c:otherwise>
            <table class="table table-hover mt-4">
                <thead>
                <tr>
                    <th scope="col" class="center">포스터</th>
                    <th scope="col">제목</th>
                    <th scope="col">평점</th>
                    <th scope="col" style="width: 20%">개봉일 </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="content" items="${contents }">
                    <tr>
                        <td>
                            <a href="<c:url value='/movie/content/${content.contentsNum }'/>">
                                <img class="test" src="${Image_URL }${content.posterPath }">
                            </a>
                        </td>
                        <td><a href="<c:url value='/movie/content/${content.contentsNum }'/>">${content.title }</a></td>
                        <td>${Math.ceil((content.voteAverage)/2*10)/10}</td>
                        <td>${content.releaseDate }</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>

<nav aria-label="Page navigation example" class="css-paging">
    <ul class="pagination">

        <!-- 페이징 맨앞으로 버튼
	  	<li class="page-item"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${paging.curPage-1 }'/>">START</a></li> -->

        <!-- 페이징 << 버튼 -->
        <c:if test="${paging.curPage ne 1 }">
            <c:choose>
                <c:when test="${type eq 'movie' }">
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${paging.curPage-1 }'/>">&laquo;</a></li>
                </c:when>
                <c:when test="${type eq 'tv' }">
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/tv?query=${query }&page=${paging.curPage-1 }'/>">&laquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/board?query=${query }&page=${paging.curPage-1 }'/>">&laquo;</a></li>
                </c:otherwise>
            </c:choose>
        </c:if>


        <!-- 페이징 블럭 번호  -->
        <c:forEach begin="${paging.blockStartNum }" end="${paging.blockLastNum }" var="i">
            <c:choose>
                <c:when test="${type eq 'movie' }">
                    <c:choose>
                        <c:when test="${i eq paging.curPage }">
                            <li class="page-item active"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:when test="${type eq 'tv' }">
                    <c:choose>
                        <c:when test="${i eq paging.curPage }">
                            <li class="page-item active"><a class="page-link" href="<c:url value='/search/tv?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="<c:url value='/search/tv?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:when>

                <c:otherwise>
                    <c:choose>
                        <c:when test="${i eq paging.curPage }">
                            <li class="page-item active"><a class="page-link" href="<c:url value='/search/board?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item"><a class="page-link" href="<c:url value='/search/board?query=${query }&page=${i }'/>">${i }</a></li>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </c:forEach>


        <!-- 페이징 >> 버튼 -->
        <c:if test="${paging.curPage ne paging.lastPageNum }">
            <c:choose>
                <c:when test="${type eq 'movie' }">
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${paging.curPage +1 }'/>">&raquo;</a></li>
                </c:when>
                <c:when test="${type eq 'tv' }">
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/tv?query=${query }&page=${paging.curPage +1 }'/>">&raquo;</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="<c:url value='/search/board?query=${query }&page=${paging.curPage +1 }'/>">&raquo;</a></li>
                </c:otherwise>
            </c:choose>
        </c:if>

        <!-- 페이징 맨뒤로 버튼
	  	<li class="page-item"><a class="page-link" href="<c:url value='/search/movie?query=${query }&page=${now-1 }'/>">END</a></li>-->

    </ul>
</nav>


</body>
</html>
