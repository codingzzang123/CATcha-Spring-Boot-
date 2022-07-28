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
    <link href="/img/main/title.png" rel="shortcut icon" type="image/x-icon">
    <title>CATcha 로그인</title>
    <style>
        input:focus{outline:2px solid #000000;}
        .custom-input{
            display: block;
            width: 500px;
            height: 32px;
            font-size: 15px;
            border: 0;
            border-radius: 15px;
            outline: none;
            padding-left: 10px;
            background-color: rgb(233, 233, 233);
        }

        #padding *{
            padding-left: 50px;
         }
        ::placeholder{
            font-size:smaller;
            font-style: italic;
        }
        .btn1 input{
            font-size: smaller;
            border-radius: 2em;
        }
        .api_Login div {
            display: inline;
        }
    </style>
    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/jieun/contentList.css" rel="stylesheet"/>
</head>
<body>
    <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>

    <section class="css-7klu3x">
        <div class="css-lufi3b">
            <div class="css-lufi3b">
                <div class="row">
                    <div class="col-lg-1"></div>
                    <div class="col-lg-11">
                        <div class="css-pbseb6-StyledHomeListTitleRow" >
                            <div style="margin-top: 15px;">
                                <p class="css-16qa0p7" style="font-size: x-large;">로그인</p>
                                <p style="font-size: small;">CATcha 사이트를 이용하기 위해서는 회원님의 계정에 로그인 해야합니다.<br>만약 계정이 없다면
                                    <b><a href="${pageContext.request.contextPath }/member/sign" style="color: dodgerblue;">여기</a></b>를 눌러 가입하실 수 있습니다.</p>
                            </div>
                            <div clsss="main-form">
                                <form method="post" action="/member/loginProc" name="loginForm" id="loginForm">
                                    <div>
                                        <div class="eheck_font" id="auth_check"></div>
                                        <div>
                                            <label for="id">아이디</label>
                                            <input class="custom-input" type="text" style="border-radius: 10px;" id="id" name="id" placeholder="아이디를 입력해주세요." required >
                                            <div class="eheck_font" id="id_check"></div>
                                        </div>

                                        <div style="margin-top: 20px;">
                                            <label for="pw">비밀번호</label>
                                            <input class="custom-input" style="border-radius: 10px;" type="password" placeholder="비밀번호를 입력해주세요." name="pw"  required class="pass" id=pw >
                                            <div class="eheck_font" id="pw_check"></div>
                                        </div>

                                        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                                    <span style="color: red; font-size:12px;"><p>아이디, 비밀번호가 잘못되었습니다.</p>
                                        <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
                                    </span>
                                        </c:if>


                                        <div style="margin-top: 20px;" class="btn1">
                                            <input type="button" id="login" class="btn btn-secondary"  value="로그인"/>
                                            <input type="button"  class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#searchModal"
                                                    value="비밀번호 찾기"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div id="icons" style="margin-top:30px;" class="api_Login">
                                <div>
                                    <p>다른 서비스 계정으로 로그인</p>
                                </div>
                                <div>
                                    <div><a href="${kakao}"><img src="/img/icon/kakao_icon.png" width="45" height="45"></a></div>
                                    <div style="margin-left: 15px;"><a href="${naver}"><img src="/img/icon/naver_icon.png" width="45" height="45"></a></div>
                                    <div style="margin-left: 15px;"><a href="#" onclick="service()"><img src="/img/icon/google_icon.png" width="45" height="45"></a></div>
                                </div>
                            </div>
                            <div>
                                <jsp:include page="/WEB-INF/views/templates/footer.jsp"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>



<%--    <div class="container-fluid py-5">--%>


<%--    </div>--%>

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

    function service(){
        alert("서비스 준비중 입니다.");
    }
</script>
</html>