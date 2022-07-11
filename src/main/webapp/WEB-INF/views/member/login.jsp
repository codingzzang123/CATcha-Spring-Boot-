<%--
  Created by IntelliJ IDEA.
  User: jiseo
  Date: 2022-07-10
  Time: 오후 4:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">



</head>
<body>
<jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
<div class="container-fluid py-5">
    <form method="post" action="/auth" >
        <div class="container mt-5 mb-5" style="width: 30%; font-weight: bold; font-size: 20px;">
            <h1><b>로그인 📑</b></h1>

            <div class="mb-3 mt-5 form-group">
                <label for="id">아이디</label>
                <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력해주세요." required oninput = "checkId()">
                <div class="eheck_font" id="id_check"></div>
            </div>

            <div class="mb-3 form-group">
                <label for="pass">비밀번호</label>
                <input class="form-control" style="border-radius: 10px;" type="password" placeholder="비밀번호를 입력해주세요." name="pw"  required class="pass" id=pass oninput="checkPw()">
                <div class="eheck_font" id="pw_check"></div>
            </div>

            <div class="mb-3 form-group text-end">
                <input type="submit" class="btn btn-dark" style="font-weight: bold; float: left; background-color:black; border-radius: 12px;" value="로그인"/>
            </div>
            <div class="mb-3 form-group text-end">
                <input type="button" class="btn btn-secondary" onclick="location.href='/member/sign';"
                       style="font-weight:bold;float:left;margin-left:8px;background-color:black; border-radius: 12px;" value="회원가입"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>