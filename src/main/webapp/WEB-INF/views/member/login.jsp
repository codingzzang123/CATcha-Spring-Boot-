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

    <style>
        #icons {
            text-align: center;
        }
    </style>

</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>
    <div class="container-fluid py-5">
        <form method="post" action="/member/loginProc" name="loginForm" id="loginForm">
            <div class="container mt-5 mb-5" style="width: 30%; font-weight: bold; font-size: 20px;">
                <h1><b>로그인</b></h1>
                <div class="eheck_font" id="auth_check"></div>
                <div class="mb-3 mt-2 form-group">
                    <label for="id">아이디</label>
                    <input type="text"class="form-control"style="border-radius: 10px;" id="id" name="id" placeholder="아이디를 입력해주세요." required >
                    <div class="eheck_font" id="id_check"></div>
                </div>

                <div class="mb-3 form-group">
                    <label for="pw">비밀번호</label>
                    <input class="form-control" style="border-radius: 10px;" type="password" placeholder="비밀번호를 입력해주세요." name="pw"  required class="pass" id=pw >
                    <div class="eheck_font" id="pw_check"></div>
                </div>

                <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                    <span style="color: red; font-size:12px;">
                        <p>아이디, 비밀번호가 잘못되었습니다.</p>
                        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
                    </span>
                </c:if>


                <div class="mb-3 form-group text-end">
                    <input type="button" id="login" class="btn btn-dark" style="font-weight: bold; float:left; background-color:black; border-radius: 12px;" value="로그인"/>
                </div>

                <div class="mb-3 form-group text-end">
                    <input type="button" class="btn btn-secondary"
                           data-bs-toggle="modal" data-bs-target="#searchModal"
                           style="font-weight:bold;float:left;margin-left:8px;background-color:black; border-radius: 12px;" value="비밀번호 찾기"/>
                </div>

            </div>
        </form>
        <br><br>
        <div class="mb-3" id="icons"><!-- ${kakao} -->
            <a href="#"><img src="/img/icon/kakao_icon.png" width="45" height="45"></a>
            <a href="#"><img src="/img/icon/naver_icon.png" width="45" height="45"></a>
            <a href="#"><img src="/img/icon/google_icon.png" width="45" height="45"></a>
        </div>
    </div>
<div id="searchModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-login">
        <div class="modal-content">
            <jsp:include page="/WEB-INF/views/member/search.jsp" />
        </div>
    </div>
</div>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

    function authCheck(){
        var loginForm = document.loginForm;
        var id = $('#id').val();
        $("#auth_check").css("font-size", "14px");
        $.ajax({
            url:'/member/authCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{id:id},
            success:function(data){
                if(data == 0){
                    console.log("data == 0");
                    $("#auth_check").prop("disabled", true);
                    $("#auth_check").css("color", "black");
                    $("#auth_check").html("이메일 인증 후 로그인 해주세요.<br>만약 이메일이 안왔다면 전송을 눌러주세요.<br><span id='sendEmail' onclick='sendEmail()'>[전송]</span>");
                    $("#sendEmail").css("cursor", "pointer");
                    $("#sendEmail").css("color", "blue");
                    $("#sendEmail").css("font-size", "14px");
                }else if(data == 1){
                    loginForm.submit();
                }
            },
        });
    }

    function sendEmail(){
        var id = $('#id').val();
        $.ajax({
            url:'/member/sendEmail', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{id:id},
        });
    }
    $("#login").click(function(){
        //button 클릭이 발생했을 경우 이벤트발생
        authCheck();
    })
    $("#pw").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            authCheck();
        }
    })
    $("#id").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            authCheck();
        }
    })
</script>
</html>