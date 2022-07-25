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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>목록</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/jieun/contentList.css" rel="stylesheet"/>

</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <section class="css-7klu3x" id="wrapper">
        <div class="container mt-5">
            <span style="font-style: italic; font-family: Inter; border: 1px dotted #d3d3d3;
                    font-size: xxx-large; padding-left: 10px; padding-right: 15px; border-radius: 2em; ">CATcha Community</span>
            <br>
            <div style="padding: 5px;">
            <a class="categ" href="/board?category=none&s_type=&s_search=&page=0" style="background-color: #f3f3f1; border-radius: 2em; padding: 5px; border: 1px solid #cccccc;">전체보기</a>
            <a class="categ" href="/board?category=일반&s_type=&s_search=&page=0" style="background-color: #f3f3f1; border-radius: 2em; padding: 5px; border: 1px solid #cccccc;">일반</a>
            <a class="categ" href="/board?category=영화&s_type=&s_search=&page=0" style="background-color: #f3f3f1; border-radius: 2em; padding: 5px; border: 1px solid #cccccc;">영화</a>
            <a class="categ" href="/board?category=TV&s_type=&s_search=&page=0" style="background-color: #f3f3f1; border-radius: 2em; padding: 5px; border: 1px solid #cccccc;">TV</a>
            <input type="hidden" name="category" value="${param.category}">
            </div>

            <table class="table table-hover" style="margin-top: 20px;">
                <thead class="text-center">
                    <tr>
                        <th scope="col">번호</th>
                        <th scope="col">말머리</th>
                        <th scope="col">제목</th>
                        <th scope="col">글쓴이</th>
                        <th scope="col">작성일</th>
                        <th scope="col">조회</th>
                        <th scope="col">추천</th>
                    </tr>
                </thead>



                <tbody class="text-center">
                    <c:if test="${!empty boards}">
                        <c:forEach var="board" items="${boards.content}">
                            <tr>
                                <td style="width: 72px;">${board.no}</td>
                                <td style="width: 100px;">${board.cate }</td>
                                <td style="width: 500px;">
                                    <a href="<c:url value="/board/${board.no}"/>">
                                        <c:choose>
                                            <c:when test="${fn:length(board.title) > 15}">
                                                <c:if test="${board.replys gt 0}">
                                                    ${fn:substring(board.title,0,15)}...&nbsp;[${board.replys}]
                                                </c:if>
                                                <c:if test="${board.replys eq 0}">
                                                    ${fn:substring(board.title,0,14)}...
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${board.replys gt 0}">
                                                    ${board.title }&nbsp;[${board.replys}]
                                                </c:if>
                                                <c:if test="${board.replys eq 0}">
                                                    ${board.title }
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </td>
                                <td style="width: 220px;">${board.name }</td>

                                <td style="width: 100px;">
                                    <fmt:parseDate value="${today }" var="now2" pattern="yyyy-MM-dd"/>
                                    <fmt:parseDate value="${board.regdate }" var="regdate2" pattern="yyyy-MM-dd"/>

                                    <fmt:parseNumber value="${now2.time / (1000*60*60*24)}" integerOnly="true" var="nowDate"/>
                                    <fmt:parseNumber value="${regdate2.time / (1000*60*60*24)}" integerOnly="true" var="regDate"/>

<%--                                    <c:out value="${nowDate - regDate}"/>--%>

                                    <c:if test="${(nowDate - regDate) gt 0}">
                                        <fmt:formatDate value="${board.regdate }" pattern="MM/dd"/>
                                    </c:if>
                                    <c:if test="${(nowDate - regDate) eq 0}">
                                        <fmt:formatDate value="${board.regdate }" pattern="hh:mm"/>
                                    </c:if>
                                </td>
                                <td style="width: 72px;">${board.views }
                                <td style="width: 72px;">${board.likes }</td>
                            </tr>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty boards}">
                        <br><br>
                        <img src="https://cdn-icons-png.flaticon.com/512/5058/5058046.png" width="60px;" height="60px;">
                        <br><br>
                        <span style="font-size: medium; font-style: italic;">&nbsp;&nbsp;등록된 게시물이 없습니다.</span><br>
                    </c:if>
                </tbody>
            </table>

            <div class="row mt-2">
                <div class="col-lg-10"></div>

                <c:choose>
                    <c:when test="${auth.name eq null}">

                    </c:when>
                    <c:otherwise>

                <div class="col-lg-2 text-end">
                    <a href="${pageContext.request.contextPath}/board/write">
                        <button type="button" class="btn btn-outline-info">글쓰기</button></a>


                </div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="row">
                <div class="text-center">
                    <form method="get">
                        <select name="s_type">
                            <option value="subject_memo">제목+내용</option>
                            <option value="subject">제목</option>
                            <option value="memo">내용</option>
                            <option value="name">작성자</option>

                            <input name="s_keyword" type="text" size="50" value="">
                            <button>검색</button>
                            <input type="hidden" name="category" value="${param.category}">
                        </select>
                    </form>
                </div>
            </div>
            <input type="hidden" id="s_type" name="s_type" value="${param.field}">
            <input type="hidden" id="s_keyword" name="s_keyword" value="${param.searchKeyword}">

            <div class="row">
                <div class="col-lg-1"></div>
                <div class="col-lg-10 text-center">
                    <div class="css-paging">
                        <nav aria-label="Page navigation example" class="css-paging">
                            <ul class="pagination justify-content-center">
                                <!--이전-->
                                <c:if test="${pageList.previousPage }">
                                    <li class="page-item"><a class="page-link" href="/board?s_type=${param.s_type}&s_keyword=${param.s_keyword}&page=${pageList.number - 1}&category=${param.category}">&laquo;</a></li>
                                </c:if>

                                <!--페이지 그룹-->
                                <c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
                                    <c:choose>
                                        <c:when test="${pageNumber+1 eq i}"> <!-- pageNumber 시작은 0 , i는 1부터 -->
                                            <li class="page-item active"><a class="page-link" href="/board?s_type=${param.s_type}&s_keyword=${param.s_keyword}&page=${i-1 }&category=${param.category}">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link" href="/board?s_type=${param.s_type}&s_keyword=${param.s_keyword}&page=${i-1 }&category=${param.category}">${i}</a></li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <!--다음-->
                                <c:if test="${pageList.nextPage }">
                                    <li class="page-item"><a class="page-link" href="/board?s_type=${param.s_type}&s_keyword=${param.s_keyword}&page=${pageList.number + 1}&category=${param.category}">&raquo;</a></li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </div>
                <div class="col-lg-1"></div>


        </div>
    </section>
</body>

</html>

