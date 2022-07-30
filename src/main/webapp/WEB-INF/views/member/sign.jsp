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
    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/jieun/contentList.css" rel="stylesheet"/>
  <link href="/img/main/title.png" rel="shortcut icon" type="image/x-icon">
  <title>CATcha 회원가입</title>
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

      div label{
        font-size: small;
      }

      .filebox ::placeholder{
        font-size:smaller;
        font-style: italic;
      }

      .filebox input[type="file"] {
        position: absolute;
        width: 0;
        height: 0;
        padding: 0;
        overflow: hidden;
        border: 0;
      }

      .filebox .upload-name {
        width: 500px;
        display: inline-block;
        height: 32px;
        padding: 0 10px;
        vertical-align: middle;
        border: 1px solid #dddddd;
        border-radius: 2em;
        color: #999999;
        pointer-events: none;
      }

      .filebox label {
        display: inline-block;
        /*padding: 10px 10px;*/
        padding-left: 7px;
        padding-right: 7px;
        color: #999999;

        /*vertical-align: middle;*/
        background-color: rgb(233, 233, 233);
        cursor: pointer;
        height: 32px;
        margin-left: 5px;
        font-size: smaller;
        font-style: italic;
        border-radius: 2em;
      }

    </style>
</head>
<body>
  <jsp:include page="/WEB-INF/views/templates/navbar.jsp"></jsp:include>


  <section class="css-7klu3x">
    <div class="css-lufi3b">
      <div class="css-lufi3b">
        <div class="row">
          <div class="col-lg-4"></div>
          <div class="col-lg-8">
            <div class="css-pbseb6-StyledHomeListTitleRow" >
              <div style="margin-top: 15px;">
                <p class="css-16qa0p7" style="font-size: x-large;">회원 가입</p>
                <p style="font-size: small;">CATcha 사이트를 계정 가입은 무료이며 쉽습니다. 시작하려면 아래 양식을 작성하세요.<br> 계속하려면 JavaScript가 필요합니다.</p>
                <div clsss="main-form">
                  <form action="/member/insert" method="post"  name="signForm" enctype="multipart/form-data">

                    <div style="margin-top: 10px;">
                      <label for="id">아이디</label>
                      <input class="custom-input" type="text" id="id" name="id" placeholder="아이디를 입력해주세요." required oninput = "checkId()">
                      <div class="form-text" id="id_check"></div>
                    </div>

                    <div style="margin-top: 10px;">
                      <label for="pass">비밀번호</label>
                      <input class="custom-input" type="password" placeholder="비밀번호를 입력해주세요." name="pw"  required class="pass" id=pass oninput="checkPw()">
                      <div class="eheck_font" id="pw_check"></div>
                    </div>

                    <div style="margin-top: 10px;">
                      <label for="pass">비밀번호 확인</label>
                      <input class="custom-input" type="password" placeholder="비밀번호를 다시 입력해주세요." name="pwcheck" required class="pass" id="repwd" oninput="matchPw()">
                      <div class="eheck_font" id="pw_match"></div>
                    </div>

                    <div style="margin-top: 10px;">
                      <label for="name">이름</label>
                      <input class="custom-input" type="text"  id="name" name="name" placeholder="닉네임을 입력해주세요." required oninput="checkName()">
                      <div class="eheck_font" id="name_check"></div>
                    </div>

                    <div style="margin-top: 10px;">
                      <label for="email">이메일 주소</label>
                      <input class="custom-input" type="email" id="email" name="email" placeholder="이메일을 입력해주세요." required oninput="checkEmail()">
                      <div class="eheck_font" id="email_check"></div>
                    </div>

                    <div style="margin-top: 10px;">
                      <label>프로필</label>
                      <div class="filebox">
                        <input class="upload-name" style="font-size: small;" value="" placeholder="첨부파일">
                        <label for="file" style="font-size: smaller;">파일선택</label>
                        <input type="file" id="file" name="file" accept="image/jpg, image/jpeg, image/png" onchange="fileCheck(this)">
                        <div id="fileCheck"></div>
                      </div>
                    </div>

                    <div style="margin-top: 25px;" class="btn1">
                      <input type="button" class="btn btn-secondary" onclick="allCheck()"
                             value="회원가입"/>
                      <input type="button" class="btn btn-secondary" onclick="history.back()"
                             value="취소"/>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>







  </body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

  $("#file").on('change',function(){
    var fileName = $("#file").val();
    $(".upload-name").val(fileName);
  });

  var id_check = false;
  var pw_check = false;
  var pwReg_check = false;
  var name_check = false;
  var email_check = false;
  var img_check = true;
  function allCheck(){
    var signForm = document.signForm;
    if(id_check == true &&
            pw_check == true &&
            pwReg_check == true &&
            name_check == true &&
            email_check == true &&
            img_check == true){
      console.log("서밋")
      signForm.submit();
    }else {
      checkId();
      checkPw();
      checkEmail();
      matchPw();
      checkName();
    }

  }

  function checkId(){
    var idReg = /^[0-9a-zA-Z][0-9a-zA-Z]{5,11}$/;
    var id = $('#id').val();//id값이 "id"인 입력란의 값을 저장
    $("#id_check").css("font-size", "12px");
    $.ajax({
      url:'/member/idCheck', //Controller에서 인식할 주소
      type:'post', //POST 방식으로 전달
      data:{id:id},
      success:function(data){
        if(!idReg.test(id) || id == null){
          $("#id_check").prop("disabled", true);
          $("#id_check").text("영소문자와 숫자를 조합해 6~12자리로 이루어져야합니다.");
          $("#id_check").css("color", "red");
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
    var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&~^])[A-Za-z\d@$!%*#?&~^]{7,13}$/;
    $("#pw_check").css("font-size", "12px");

    if(passReg.test(inputed)){
      $("#pw_check").prop("disabled", true);
      $("#pw_check").text("사용가능한 비밀번호 입니다.");
      $("#pw_check").css("color", "green");
      pw_check = true;

    }else if(!passReg.test(inputed)){
      $("#pw_check").prop("disabled", true);
      $("#pw_check").text("영소문자와 숫자,특수문자(@,$,!,%,*,#,?,&,~,<,^)를 조합해 8~14자리로 이루어져야 합니다.");
      $("#pw_check").css("color", "red");
    }
  }

  function matchPw() {
    var passReg = /^^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{7,13}$/;
    var inputed = $('#pass').val();
    var reinputed = $('#repwd').val();
    $("#pw_match").css("font-size", "12px");

    if(inputed == reinputed && reinputed != ""){
      $("#pw_match").prop("disabled", true);
      $("#pw_match").text("비밀번호 맞습니다");
      $("#pw_match").css("color", "green");
      pwReg_check = true;
    }else if(inputed != reinputed || passReg.test(reinputed) || reinputed == ""){
      $("#pw_match").prop("disabled", true);
      $("#pw_match").text("비밀번호 틀립니다");
      $("#pw_match").css("color", "red");
    }
  }

  function checkName(){
    var nameReg = /^(?=.*[A-Za-z0-9가-힣])[A-Za-z0-9가-힣]{2,10}$/;
    var name = $('#name').val(); //id값이 "id"인 입력란의 값을 저장
    $("#name_check").css("font-size", "12px");

    $.ajax({
      url:'/member/nameCheck', //Controller에서 인식할 주소
      type:'post', //POST 방식으로 전달
      data:{name:name},
      success:function(data){
        if(!nameReg.test(name)){
          $("#name_check").prop("disabled", true);
          $("#name_check").text("영어 또는 숫자 또는 한글을 조합해 2~10자리로 이루어져야 합니다.");
          $("#name_check").css("color", "red");
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
    $("#fileCheck").css("font-size", "12px");
    var file_kind = obj.value.lastIndexOf('.');
    var file_name = obj.value.substring(file_kind+1,obj.length);
    var file_type = file_name.toLowerCase();
    var check_file_type=new Array();
    check_file_type=['jpg','png','jpeg'];

    if(obj.value == ""){
      $("#fileCheck").css("display", "none");
      img_check = true;
    }else if(check_file_type.indexOf(file_type)==-1){
      $("#fileCheck").css("display", "block");
      $("#fileCheck").text("jpg, png, jpeg 확장자만 가능합니다.");
      $("#fileCheck").css("color", "red");
      img_check = false;
    }else{
      $("#fileCheck").css("display", "block");
      $("#fileCheck").text("정상적인 확장자입니다.");
      $("#fileCheck").css("color", "green");
      img_check = true;
    }
  }

</script>
</html>