<%--
  Created by IntelliJ IDEA.
  User: jiseo
  Date: 2022-07-14
  Time: 오전 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@300&display=swap" rel="stylesheet">
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>
    <div class="modal-body">
        <form method="post" id="searchForm" >
                <div class="mb-3">
                <b style="font-family: Cambria; font-size: larger;">비밀번호 찾기</b>

                </div>
                <div class="form-group mt-2">
                    <label for="idModal">아이디</label>
                    <input type="text"class="form-control"style="border-radius: 10px;" id="idModal" name="idModal" placeholder="아이디를 입력해주세요." required >
                </div>

                <div class="form-group mt-2">
                    <label for="emailModal">이메일</label>
                    <input class="form-control" style="border-radius: 10px;" type="email" placeholder="이메일를 입력해주세요." name="email"  required class="emailModal" id=emailModal >
                </div>

                <div class="form-group">
                    <div class="eheck_font" id="check"></div>
                </div>

                <div class="form-group text-end mt-2">
                    <input type="button" id="search" class="btn btn-dark" style="font-family: Consolas; display: inline;" value="확인"/>
                    <button type="button" id="ModalCloseId" class="btn btn-danger" data-bs-dismiss="modal" style= "font-family: Consolas; display: inline">Close</button>
                </div>
        </form>
    </div>
</body>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">
    function checkId(){
        var id = $('#idModal').val();//id값이 "id"인 입력란의 값을 저장
        $("#check").css("font-size", "12px");
        $.ajax({
            url:'/member/idCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{id:id},
            success:function(data){
                if(data == 0){
                    $("#check").prop("disabled", true);
                    $("#check").text("아이디, 이메일을 잘못 입력하였습니다. 다시 확인해주세요.");
                    $("#check").css("color", "red");
                }else if(data == 1){
                    checkEmail();
                }
            },
        });
    }

    function checkEmail(){
        var email = $('#emailModal').val();
        var id = $('#idModal').val();
        $("#check").css("font-size", "12px");
        $.ajax({
            url:'/member/emailCheck', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{id, email : id, email},
            success:function(data){
                if(data == 0){
                    $("#check").prop("disabled", true);
                    $("#check").text("아이디, 이메일을 잘못 입력하였습니다. 다시 확인해주세요.");
                    $("#check").css("color", "red");
                }else if(data == 1){
                    sendEmailPw();
                    $("#check").prop("disabled", true);
                    $("#check").text("변경된 비밀번호가 이메일로 전송되었습니다. 이메일을 확인해주세요.");
                    $("#check").css("color", "green");
                }
            },
        });
    }

    function sendEmailPw(){
        var email = $('#emailModal').val();
        var id = $('#idModal').val();
        $("#check").css("font-size", "12px");
        $.ajax({
            url:'/member/sendEmailPw', //Controller에서 인식할 주소
            type:'post', //POST 방식으로 전달
            data:{id, email : id, email},
        });
    }


    $("#search").click(function(){
        checkId();
    })
    $("#email").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            checkId();
        }
    })
    $("#id").keydown(function(keyNum){
        if(keyNum.keyCode == 13){
            checkId();
        }
    })
</script>
</html>
