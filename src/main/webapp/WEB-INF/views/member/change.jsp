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
        <form method="post" action="/member/change" name="changeForm" id="changeForm">
            <div class="container mt-5 mb-5" style="width: 30%; font-weight: bold; font-size: 20px;">
                <h1><b>비밀번호 변경</b></h1>
                <br>
                <div class="eheck_font" id="auth_check"></div>
                <div class="mb-3 mt-2 form-group">
                    <label for="oldPw">현재 비밀번호</label>
                    <input type="password"class="form-control"style="border-radius: 10px;" id="oldPw" name="oldPw" placeholder="현재 비밀번호를 입력해주세요." required >
                    <div class="eheck_font" id="oldPw_check"></div>
                </div>

                <div class="mb-3 mt-5 form-group">
                    <label for="newPw">변경할 비밀번호</label>
                    <input type="password"class="form-control"style="border-radius: 10px;" id="newPw" name="newPw" placeholder="현재 비밀번호를 입력해주세요." required >
                    <div class="eheck_font" id="newPw_check"></div>
                </div>

                <div class="mb-3 mt-2 form-group">
                    <label for="regPw">변경할 비밀번호 확인</label>
                    <input type="password"class="form-control"style="border-radius: 10px;" id="regPw" name="regPw" placeholder="현재 비밀번호를 입력해주세요." required >
                    <div class="eheck_font" id="regPw_check"></div>
                </div>

                <div class="mb-3 form-group text-end">
                    <input type="button" id="change" class="btn btn-dark" style="font-weight: bold; float:right; background-color:black; border-radius: 12px;"
                           value="확인"/>
                </div>
            </div>
                <input type="hidden" id="id" name="id" value="${auth.id}">
        </form>
    </div>
</body>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var id = $('#id').val();
        var oldPw = $('#oldPw').val();
        $("#oldPw_check").css("font-size", "12px");
        $.ajax({
            url:'/member/pwCheck',
            type:'post', //POST 방식으로 전달
            data:{id,oldPw:id,oldPw},
            success:function(data){
                console.log(data);
                if(data == 0){
                    $("#oldPw_check").prop("disabled", true);
                    $("#oldPw_check").text("현재 비밀번호가 틀립니다.");
                    $("#oldPw_check").css("color", "red");
                }else if (data == 1){
                    checkPw();
                }
            },
        });
    }

    function checkPw(){
        var newPw = $('#newPw').val();
        var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,14}$/;
        $("#newPw_check").css("font-size", "12px");

        if(passReg.test(newPw)){
            $("#newPw_check").prop("disabled", true);
            $("#newPw_check").text("사용가능한 비밀번호 입니다.");
            $("#newPw_check").css("color", "green");
            matchPw();

        }else if(!passReg.test(newPw)){
            $("#newPw_check").prop("disabled", true);
            $("#newPw_check").text("영소문자와 숫자,특수문자를 조합해 8~14자리로 이루어져야 합니다.");
            $("#newPw_check").css("color", "red");
        }
    }

    function matchPw() {
        var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,14}$/;
        var newPw_check = $('#newPw_check').val();
        var regPw_check = $('#regPw_check').val();
        $("#regPw_check").css("font-size", "12px");

        if(newPw_check == regPw_check){
            changeForm.submit();
        }else if(newPw_check != regPw_check || passReg.test(regPw_check)){
            $("#regPw_check").prop("disabled", true);
            $("#regPw_check").text("비밀번호 틀립니다");
            $("#regPw_check").css("color", "red");
        }
    }

    $("#change").click(function(){
        checkId();
    })
    $("#oldPw").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            checkId();
        }
    })
    $("#newPw").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            checkId();
        }
    })
    $("#regPw").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            checkId();
        }
    })
</script>
</html>