<%--
  Created by IntelliJ IDEA.
  User: jiseo
  Date: 2022-07-10
  Time: 오후 5:17
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
  <form action="/member/insert" method="post"  name="signForm" enctype="multipart/form-data">
    <div class="container mt-5 mb-5" style="width: 50%; font-weight: bold; font-size: 20px;">
      <h1><b>회원 가입 📑</b></h1>

      <div class="mb-3 form-group">
        <label for="id">아이디</label>
        <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력해주세요." required oninput = "checkId()">
        <div class="form-text" id="id_check"></div>
      </div>

      <div class="mb-3 form-group">
        <label for="pass">비밀번호</label>
        <input class="form-control" type="password" placeholder="비밀번호를 입력해주세요." name="pw"  required class="pass" id=pass oninput="checkPw()">
        <div class="eheck_font" id="pw_check"></div>
      </div>

      <div class="mb-3 form-group">
        <label for="pass">비밀번호 확인</label>
        <input  class="form-control"  type="password" placeholder="비밀번호를 다시 입력해주세요." name="pwcheck" required class="pass" id="repwd" oninput="matchPw()">
        <div class="eheck_font" id="pw_match"></div>
      </div>

      <div class="mb-3 form-group">
        <label for="name">이름</label>
        <input type="text" class="form-control" id="name" name="name" placeholder="닉네임을 입력해주세요." required oninput="checkName()">
        <div class="eheck_font" id="name_check"></div>
      </div>

      <div class="mb-3 form-group">
        <label for="email">이메일 주소</label>
        <input type="email" class="form-control" id="email" name="email" placeholder="이메일을 입력해주세요." required oninput="checkEmail()">
        <div class="eheck_font" id="email_check"></div>
      </div>

      <div class="mb-3 form-group">
        <label for="file">프로필</label>
        <input type="file" class="form-control" id="file" name="file" accept="image/jpg, image/jpeg,image/png" onchange="fileCheck(file)">
        <div class="eheck_font" id="file_check"></div>
      </div>

      <div class="mb-3 form-group text-end">
        <input type="button" class="btn btn-secondary" onclick="allCheck()"
               style="font-weight: bold; float: left; background-color:black; border-radius: 12px;" value="회원가입"/>
      </div>
      <div class="mb-3 form-group text-end">
        <input type="button" class="btn btn-secondary" onclick="history.back()"
               style="font-weight:bold;float:left;margin-left:8px;background-color:black; border-radius: 12px;" value="취소"/>
      </div>
    </div>
  </form>
</div>


</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
  var id_check = false;
  var pw_check = false;
  var pwReg_check = false;
  var name_check = false;
  var email_check = false;
  var img_check = true;
  function allCheck(){
    var signForm = document.signForm;
    var inputed = $('#pass').val();
    var reinputed = $('#repwd').val();
    if(inputed != reinputed){
      $("#pw_match").prop("disabled", true);
      $("#pw_match").text("비밀번호 틀립니다");
      $("#pw_match").css("color", "red");
    }else if(id_check == true &&
            pw_check == true &&
            pwReg_check == true &&
            name_check == true &&
            email_check == true &&
            img_check == true){
      console.log("서밋")
      signForm.submit();
    }

  }

  function checkId(){
    var idReg = /^[0-9a-zA-Z][0-9a-zA-Z]{6,12}$/;
    var id = $('#id').val();//id값이 "id"인 입력란의 값을 저장
    $("#id_check").css("font-size", "12px");
    $.ajax({
      url:'/member/idCheck', //Controller에서 인식할 주소
      type:'post', //POST 방식으로 전달
      data:{id:id},
      success:function(data){
        if(!idReg.test(id)){
          $("#id_check").prop("disabled", true);
          $("#id_check").text("영소문자와 숫자를 조합해 6~12자리로 이루어져야합니다.");
          $("#id_check").css("color", "grey");
        }else if(data == 1){
          $("#id_check").prop("disabled", true);
          $("#id_check").text("사용중인 아이디 입니다.");
          $("#id_check").css("color", "red");
        }else if(data == 0){
          $("#id_check").prop("disabled", true);
          $("#id_check").text("사용가능한 아이디 입니다.");
          $("#id_check").css("color", "green");
          id_check = true;
        }
      },
    });
  }


  function checkPw(){
    var inputed = $('#pass').val();
    var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,14}$/;
    $("#pw_check").css("font-size", "12px");

    if(passReg.test(inputed)){
      $("#pw_check").prop("disabled", true);
      $("#pw_check").text("사용가능한 비밀번호 입니다.");
      $("#pw_check").css("color", "green");
      pw_check = true;

    }else if(!passReg.test(inputed)){
      $("#pw_check").prop("disabled", true);
      $("#pw_check").text("영소문자와 숫자,특수문자를 조합해 8~14자리로 이루어져야 합니다.");
      $("#pw_check").css("color", "red");
    }
  }

  function matchPw() {
    var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,14}$/;
    var inputed = $('#pass').val();
    var reinputed = $('#repwd').val();
    $("#pw_match").css("font-size", "12px");

    if(inputed == reinputed){
      $("#pw_match").prop("disabled", true);
      $("#pw_match").text("비밀번호 맞습니다");
      $("#pw_match").css("color", "green");
      pwReg_check = true;
    }else if(inputed != reinputed || passReg.test(reinputed)){
      $("#pw_match").prop("disabled", true);
      $("#pw_match").text("비밀번호 틀립니다");
      $("#pw_match").css("color", "red");
    }
  }

  function checkName(){
    var nameReg = /^(?=.*[A-Za-z0-9가-힣])[A-Za-z0-9가-힣]{2,16}$/;
    var name = $('#name').val(); //id값이 "id"인 입력란의 값을 저장
    $("#name_check").css("font-size", "12px");

    $.ajax({
      url:'/member/nameCheck', //Controller에서 인식할 주소
      type:'post', //POST 방식으로 전달
      data:{name:name},
      success:function(data){
        if(!nameReg.test(name)){
          $("#name_check").prop("disabled", true);
          $("#name_check").text("영어 또는 숫자 또는 한글을 조합해 2~16자리로 이루어져야 합니다.");
          $("#name_check").css("color", "grey");
        }else if(data == 1){
          $("#name_check").prop("disabled", true);
          $("#name_check").text("사용불가능한 아이디 입니다.");
          $("#name_check").css("color", "red");
        }else if(data == 0){
          $("#name_check").prop("disabled", true);
          $("#name_check").text("사용가능한 아이디 입니다.");
          $("#name_check").css("color", "green");
          name_check = true;
        }
      },
    });
  }

  function checkEmail(){
    var email = $('#email').val();
    var emailReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
    $("#email_check").css("font-size", "12px");

    if(emailReg.test(email)){
      $("#email_check").prop("disabled", true);
      $("#email_check").text("정상적인 이메일입니다.");
      $("#email_check").css("color", "green");
      email_check = true;
    }else if(!emailReg.test(email)){
      $("#email_check").prop("disabled", true);
      $("#email_check").text("맞지 않은 이메일 입니다.");
      $("#email_check").css("color", "red");
    }
  }

  function fileCheck(obj) {
    $("#file_check").css("font-size", "12px");
    var file_kind = obj.value.lastIndexOf('.');
    var file_name = obj.value.substring(file_kind+1,obj.length);
    var file_type = file_name.toLowerCase();
    var check_file_type=new Array();
    check_file_type=['jpg','png','jpeg'];

    if(check_file_type.indexOf(file_type)==-1){
      $("#file_check").prop("disabled", true);
      $("#file_check").text("맞지 않은 확장자 입니다.");
      $("#file_check").css("color", "red");
      img_check = false;
    }else{
      $("#file_check").prop("disabled", true);
      $("#file_check").text("정상적인 확장자입니다.");
      $("#file_check").css("color", "green");
      img_check = true;
    }
  }

</script>
</html>