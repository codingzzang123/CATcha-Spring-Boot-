<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="/img/main/title.png" rel="shortcut icon" type="image/x-icon">
    <title>관심 목록 - ${auth.name }</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link href="/css/hosun/main.css" rel="stylesheet"/>
    <%--    <link href="/css/hosun/info.css" rel="stylesheet"/>--%>
    <style>
        #test{
            min-height: 250px;
            max-height: 250px;
            height: 250px;
            position: relative;
        }
        #testbtn{
            vertical-align: bottom;
            width: 60px;
            height: 40px;
            margin-left: 850px;
        }
    </style>
</head>
<body>

<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/navbar.jsp"/>
<br>

<section class="css-7klu3x" id="wrapper">

    <jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/templates/infoHeader.jsp"/>

    <section>
        <div class="css-Like-Body" scroll=auto style="overflow-x:hidden; height: auto; max-height: 790px; min-height: 250px;">
            <div style="text-align: center; margin-top: 7px;" ><strong>Movies&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
                <div style="display: inline-block">
                <span class="badge rounded-pill bg-warning text-dark btn btn-mini" style="min-width: 30px; max-width: 60px; height: 30px; text-align: center; vertical-align: middle;
                                                        font-size: large;" id="movie"></span></div>
                <div style="display: inline-block"><strong>&nbsp;&nbsp;TV shows&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong>
                <span class="badge rounded-pill bg-warning text-dark btn btn-mini" style="min-width: 30px; max-width: 60px; height: 30px; text-align: center; vertical-align: middle;
                                                        font-size: large;" id="tv"></span></div>
                <div style="display: inline-block"><strong>&nbsp;&nbsp;&nbsp;Boards&nbsp;&nbsp;&nbsp;&nbsp;</strong>
                <span class="badge rounded-pill bg-warning text-dark btn btn-mini" style="min-width: 30px; max-width: 60px; height: 30px; text-align: center; vertical-align: middle;
                                                        font-size: large;" id="board"></span></div>
            </div>

            <div id="result"><!-- datas --></div>
        </div>
    </section>

    <input type="hidden" id="memberId" name="memberId" value="${auth.id }">

        <!-- 삭제 대한 모달 -->
        <div class="modal" id="MemberLikeModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                        <button type="button" id="ModalCloseId" class="btn btn-danger" data-bs-dismiss="modal" id="close" style= "font-family: Consolas">Close</button>
                        <input type="hidden" id="deleteLikeModal" value="">
                        <button type="button" class="btn btn-warning" data-bs-dismiss="modal" style= "font-family: Consolas" id="deleteMemberLike">Delete</button>
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
            $('#MemberLikeModal').on('show.bs.modal', function (e) {
                var data = $(e.relatedTarget).data('test');
                $('#deleteLikeModal').val(data);
                console.log('data = '+data);
                var data2 = $('#deleteLikeModal').val(data);
                console.log('data22 = '+data2);
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
                    imgPath='';
                    console.log(data);
                    if(data.length != 0){
                        ckcode = data[0].code;
                        if(ckcode == 0){
                            path = "/movie/content/";
                        }else if(ckcode == 1){
                            path = "/tv/content/";
                        }else{
                            path = "/board/";
                        }
                    }

                    if(data.length == 0){
                        resultHTML = "<div style='text-align: center; margin-top: 4em;'>"+
                            "<img style='height: 150px; width: auto;' src='https://cdn-icons-png.flaticon.com/512/4826/4826311.png'></div>"
                    }else{

                        for(let i=0; i<data.length; i++){

                            resultHTML += ("<div class='css-infoItem' id='item"+data[i].contentsNum+"' >"+
                                "<div class='row mt-1'>"+
                                "<div class='col-lg-3 item-img' style='text-align:center;'>"+
                                "<img class='rounded-circle' style='width:220px; height:220px;' src= "+ data[i].posterPath +">"+
                                "</div>"+
                                "<div id='test' class='col-lg-9'>"+
                                "<div id='con'>"+
                                "<p id='title' style='font: italic bold 1.5em/1em Georgia ;'>"+data[i].title+"</p>"+
                                "<a href=\" <c:url value=' " + path+data[i].contentsNum +"'/> \">"+"<div id='con2'>"+
                                data[i].overview+
                                "</div></a>"+
                                "</div>"+
                                "<div id='testbtn'><button type='button' class='btn btn-danger' data-bs-toggle='modal' data-bs-target='#MemberLikeModal' data-test='"+ data[i].contentsNum +"'>삭제</button></div>"+
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

        $("#deleteMemberLike").click(function(){
            console.log("click");
            var d = $('#deleteLikeModal').val();
            console.log("d : "+ d);
            deleteLike(d);
        });

        $("#close").click(function(){
            console.log("click");

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
                    getList();
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
                    $('#movie').text(""+data.movie+"");
                    $('#tv').text(""+data.tv+"");
                    $('#board').text(""+data.board+"");
                },error:function (){
                    console.log("fail");
                }
            });
        }
    </script>
    <jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
</body>
</html>
