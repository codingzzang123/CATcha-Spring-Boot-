<%--
  Created by IntelliJ IDEA.
  User: hs975
  Date: 2022-07-13
  Time: AM 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <link href="/css/hosun/info.css" rel="stylesheet"/>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"/>
<br>

<section class="css-7klu3x" id="wrapper">
    <section class="new_index">
        <div class="css-Like-header css-lufi3b">
            <div class="container">
                <div class="cell-header">
                    <img src="/img/profile/${auth.imgs}" class="rounded-circle css-memImg">
                </div>
                <div class="cell-content1">
                    <span class="css-16qa0p7">${auth.name}</span><br>
                    <span class="css-16qa0p7">User(Regdate => 구현 예정)</span>
                </div>
            </div>
        </div>
    </section>
    <section>
        <div class="css-Like-Body" scroll=auto style="overflow-x:hidden">
           <div style="text-align: center;"><span>나의 관심 목록</span>
                <span style="margin-left: 20px;">영화 : </span><input type="button" id="movie" value="" class="button css-circle"/>
                <span style="margin-left: 20px;">TV : </span><input type="button" id="tv" value="" class="button css-circle"/>
                <span style="margin-left: 20px;">게시판 :</span><input type="button" id="board" value="" class="button css-circle"/>
            </div>

            <div id="result"><!-- datas --></div>
        </div>
    </section>

    <input type="hidden" id="memberId" name="memberId" value="${auth.id }">

    <!-- 삭제 대한 모달 -->
    <div class="modal" id="Modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel" style="font-family: Georgia"><b>Please check again</b></h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <h5>관심목록에서 제거 하시겠습니까?</h5>
                </div>
                <div class="modal-footer">
                    <button type="button" id="ModalCloseId" class="btn btn-danger" data-bs-dismiss="modal" style= "font-family: Consolas">Close</button>
                    <input type="hidden" id="deleteModal" value="">
                    <button type="button" class="btn btn-warning" data-bs-dismiss="modal" style= "font-family: Consolas" id="delete">Delete</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        const prefix = "https://image.tmdb.org/t/p/original";
        var resultHTML;
        var memberId = $('#memberId').val();
        var code = 0;
        var link = ""
        $(document).ready(function(){
            getCount();
            getList();
            $('#Modal').on('show.bs.modal', function (e) {
                var data = $(e.relatedTarget).data('test');
                $('#deleteModal').val(data);
            });
        });

        function getList(){
            $.ajax({
                url: './like',
                type: 'post',
                data:{
                    'memberId':memberId,
                    'code':code
                },
                success:function (data){
                    resultHTML ="";
                    console.log(data);
                    if(data.length != 0){
                        ckcode = data[0].code;
                        if(code == 0){
                            path = "/movie/content/";
                        }else if(code == 1){
                            path = "/tv/content/";
                        }else{
                            path = "/board/";
                        }
                    }

                    if(data.length == 0){
                        resultHTML = "<div style='text-align: center; margin-top: 7em;'>"+
                            "<h2 style='font: italic bold 2em/1em Georgia, serif;'>관심 목록은 현재 비어있습니다..❗ </h2></div>"
                    }else{

                        for(let i=0; i<data.length; i++){

                            resultHTML += ("<div class='css-infoItem' id='item"+data[i].contentsNum+"' >"+
                                "<div class='row mt-1'>"+
                                "<div class='col-lg-3 item-img' style='text-align:center;'>"+
                                "<img src= "+ prefix + data[i].posterPath+">"+
                                "</div>"+
                                "<div class='col-lg-9'>"+
                                "<div id='con'>"+
                                "<p id='title' style='font: italic bold 1.5em/1em Georgia, serif ;'>"+data[i].title+"</p>"+
                                "<a href=\" <c:url value=' " + path+data[i].contentsNum +"'/> \">"+"<div id='con2'>"+
                                data[i].overview+
                                "</div></a>"+
                                "<button type='button' class='btn btn-danger' style='margin-top: 95px;' data-bs-toggle='modal' data-bs-target='#Modal' data-test='"+ data[i].contentsNum +"'>삭제</button>"+
                                "</div>"+
                                "</div>"+
                                "</div>"+
                                "</div>");
                        }
                    }
                    document.querySelector('#result').innerHTML = resultHTML;
                },error:function (){
                    console.log("fail");
                }
            });
        }

        $("#movie").click(function(){
            code = 0;
            getList();
        });

        $("#tv").click(function(){
            code = 1;
            getList();
        });

        $("#board").click(function(){
            code = 2;
            getList();
        });

        $("#delete").click(function(){
            var d = $('#deleteModal').val()
            deleteLike(d);
        });

        function deleteLike(i){
            console.log(i);
            $.ajax({
                url: './delete',
                type: 'delete',
                data:{
                    'memberId':memberId,
                    'contentsNum':i,
                    'code':code
                },success:function (data){
                    console.log("Delete Success");
                    let deleteDiv = document.getElementById("item"+i);
                    deleteDiv.remove();
                    getCount();
                },error:function (){
                    console.log("fail");
                }
            });
        }
        function getCount(){
            $.ajax({
                url: './count',
                type: 'post',
                data:{
                    'memberId':memberId
                },success:function (data){
                    $('#movie').val(data.movie);
                    $('#tv').val(data.tv);
                    $('#board').val(data.board);
                },error:function (){
                    console.log("fail");
                }
            });
        }
    </script>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
</body>
</html>
