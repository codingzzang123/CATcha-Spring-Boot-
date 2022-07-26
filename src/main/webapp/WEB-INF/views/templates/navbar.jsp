<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="/css/hosun/nav.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

    <style>
        * {
            font-family: Cambria;
        }

        #msgStack{
            width: 400px;
            right: 0px;
            bottom: 15px;
            position: fixed;
            z-index: 9999;
        }

        .badge{
            font-size: 12px;
            height: auto;
            width: auto;
            margin-left: -14px;

        }

        a{color: black;}
        a:link{color: black;}
        a:visited{color: black;}
        a:hover{color: black;}
        a:active{color: black;}

        ::-webkit-scrollbar {
            display: none;
        }

        .myUl{ width: 400px; height:auto; max-height: 290px; overflow-y: scroll; white-space: nowrap;
            margin-top: 20px; overflow-x: hidden;
        }

        .alertLi{
            margin-bottom: 5px;
        }

        .alertMain{
            width: 382px; height: 86px; max-width: 382px; max-height: 86px; overflow: hidden; display: flex; margin-left: 7px; align-items: center; padding-left: 10px; background-color: #f5f5f5; border-radius: 2em;
        }

        .alertdiv1{
            display: inline-block;
        }

        .alertdiv2{
            display: inline-block; margin-left: 15px;
        }

        .alertdiv1 img{
            width: 3.3em; height: 3.3em;
        }

        .alertSpan1{
            font-size: small;
        }

    </style>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var socket;
        var name = '${auth.name }'

        /* Page roading 후 실행될 function */
        $(document).ready(function(){
            if( ${auth ne null} ){
                connect();
                AlertCount(name);
            }
        });

        /* Socket Connect function */
        function connect() {
            console.log('into connect function');
            var ws = new SockJS("/gs-websocket");
            socket = ws;

            ws.onopen = function (){
                console.log("open");
            };

            ws.onclose = function (){
                console.log("close");
            };

            ws.onmessage = function (evt){
                var data = evt.data;

                let toast = "<div class='toast' id='toast' data-autohide='false'>";
                toast += "<div class='toast-header'><strong class='mr-auto text-primary'>알림</strong>";
                toast += "<small class='text-muted'>just now</small><button type='button' id='test' class='ml-2 mb-1 close' data-bs-dismiss='toast' aria-label='Close'>";
                toast += "&times;</button></div>";
                toast += "<div class='toast-body'>" + data + "</div></div>";
                $("#msgStack").append(toast);   // msgStack div에 생성한 toast 추가
                $(".toast").toast({"animation": true, "autohide": true});
                $('.toast').toast('show');
                // $("#newNoticeCnt").text($("#newNoticeCnt").text()*1+1); //알림 받았을때 카운트 +1증가
                AlertCount();
            };

        }

        /* 알림 Bell Click 시 */
        function callFunction() {
            AlertCount();
            getAlertList();
            return true;
        }

        /*  알림 카운트 가져오기 */
        function AlertCount(){
            var name = '${auth.name }';
            console.log('TEST AlertCount : '+name);
            $.ajax({
                type: "post",
                async: "true",
                dataType: "text",
                data: {
                    'name': name
                },
                url: "${pageContext.request.contextPath}/alert/count",
                success: function (data) {
                    console.log("success Alert Count data : "+data);
                    if(data!='0'){
                        $("#newNoticeCnt").text(data);
                    }else if(data=='0'){
                        $("#newNoticeCnt").text('');
                    }
                }
            });
        }

        /* 알림 Dropdown menu Click시 실행되는 function */
        function getAlertList(){
            var alertHTML="";
            $.ajax({
                type: "get",
                async: "true",
                data: {
                    'name': '${auth.name }'
                },
                url: "${pageContext.request.contextPath}/alert/list",
                success: function (data) {
                    console.log(data);
                    var innerMessage="";
                    var type ="";
                    if(data.length != 0){
                        for(let i=0; i<data.length; i++){
                            var contextPath = '${pageContext.request.contextPath}';
                            var date = new Date(data[i].regdate);
                            var link ="";
                            var aID = '';
                            innerDate = timeForToday(date);
                            title = data[i].title;
                            if(title.length > 8) {
                                title = title.substring(0, 10) + ".."
                            }

                            if(data[i].code == 0){
                                innerMessage = "님이 좋아요를 눌렀습니다.";
                                type ="게시글에";
                                link='#';
                                aID = 0;
                            }else if(data[i].code == 1){
                                innerMessage = "님이 댓글을 달았습니다.";
                                type ="게시글에"
                                link=contextPath+"/board/"+data[i].bno;
                                aID = data[i].no;
                            }else{
                                innerMessage = "님이 댓글을 달았습니다.";
                                type ="댓글에"
                                link=contextPath+"/board/"+data[i].bno;
                                aID = data[i].no;
                            }


                            alertHTML += "<li class='alertLi' id='li"+data[i].no+"'><div class='alertMain'><div class='alertdiv1'><img class='rounded-circle' src='/img/profile/"+data[i].imgs+"'/></div>"+
                                        "<div class='alertdiv2'><div><a id='myA"+aID+"' onclick='removeLi("+ aID +")' href="+link+"><span class='alertSpan1'>회원님의 \"<strong style='font-style: italic;'>"+title+"</strong>\" "+" "+type+"<br>"+
                                "<strong>"+data[i].pubName+"</strong>"+innerMessage+"</span></a></div>"+
                                "<div style='width: 260px; max-width: 280px; margin-top: 5px;'><span style='font-size: small; font-style: italic;'><strong>"+innerDate+"</strong><span>"+
                                "<button class='ml-2 mb-1 close' onclick='removeLi("+ data[i].no +")'>&times;</button></div>"+
                                "</div></div></li>";
                        }

                    }else{
                        alertHTML = "<div style='text-align: center'>"+
                            "<img style='width: 60px; height: 60px;' src='https://as1.ftcdn.net/v2/jpg/01/71/15/98/1000_F_171159851_rbqcuNaXXvNaZSdXzRlDtpADHt3Xtp6a.jpg'/>"+
                            "<span style='font-size: medium; font-style: italic;'>&nbsp;모든 알림을 확인하셨습니다.</span>"+
                        "</div>";
                    }
                    document.querySelector('#alertInner').innerHTML = alertHTML;
                }
            });
        }

        /* 알림 시간 revert function */
        function timeForToday(value) {
            const today = new Date();
            const timeValue = new Date(value);

            const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);
            if (betweenTime < 1) return '방금전';
            if (betweenTime < 60) {
                return betweenTime+"분 전";
            }

            const betweenTimeHour = Math.floor(betweenTime / 60);
            if (betweenTimeHour < 24) {
                return betweenTimeHour+"시간 전";
            }

            const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
            if (betweenTimeDay < 365) {
                return betweenTimeDay+"일 전";
            }

            return `${Math.floor(betweenTimeDay / 365)}년전`;
        }


        <!-- Dropdown Menu (안쪽 Click시 menu 숨겨지지 않도록 -->
        function removeLi(i){
            if(i != '0'){ //좋아요에 대한 알림은 클릭해도 삭제 x(없애려면 x버튼을 눌러야함)
                let id = "li"+i;
                let deleteLi = document.getElementById(id);
                deleteLi.remove();
                deleteAlert(i);
            }else{
                myA = '#myA'+i;
                $(myA).removeAttr("href");
            }
        }

        <!-- 알림리스트 x 버튼 눌렀을때 DB에서 삭제및 화면Div 삭제 -->
        function deleteAlert(i){
            $.ajax({
                type: "delete",
                data: {
                    'no':i
                },
                url: "${pageContext.request.contextPath}/alert/delete",
                success: function (data) {
                    console.log("Success update(delete)");
                    AlertCount();
                    getAlertList();
                }
            });
        }
        $("#action").click(function (){

        });
    </script>

</head>

<header class="css-6k8tqb" id="header">
    <nav>
        <div class="css-1gkas1x-Grid e1689zdh0">
            <div class="css-1djzg97">
                <ul class="css-6v7b8v-VisualUl">
                    <li>
                        <a href="${pageContext.request.contextPath }/main">
                            <img src="/img/main/logo.jpg" height="42px;">
                        </a>

                    </li>

                    <li class="css-bj71cw" style="font-size: large; color: black;">CATcha</li>

                    <li class="categories css-bj71cw">
                        <a class="nav-link active customLi" href="${pageContext.request.contextPath }/main">Home</a>
                    </li>

                    <li class="categories">
                        <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Movie</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/movie/list?category=popularity.desc">인기</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/movie/list?category=vote_average.desc">높은 평점</a></li>
                        </ul>
                    </li>


                    <li class="categories">
                        <a class="nav-link dropdown-toggle customLi" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Tv</a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/tv/list?category=popularity.desc">인기</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/tv/list?category=vote_average.desc">높은 평점</a></li>
                        </ul>
                    </li>

                    <li class="categories">
                        <a class="nav-link active customLi" href="${pageContext.request.contextPath }/board">Board</a>
                    </li>

                    <li class="css-1c3wwgb">
                        <div class="css-1xm32e0">
                            <div class="css-1okeg7p">
                                <form onsubmit="return submit()" name="fr" action="<c:url value="/search"/>">
                                    <label class="css-kyr608" id="label">
                                        <div class="search">
                                            <input type="text" autocomplete="off" placeholder="다양한 콘텐츠를 검색해보세요." name="query" class="css-13i5xe6" id="keyword_header">
                                        </div>
                                        <div value="false" class="css-ikj5sk">
                                            <span aria-label="clear" role="button" class="css-14vwb0g"></span>
                                        </div>
                                    </label>
                                </form>
                                    <script>
                                        function submit(){

                                            var checkInput = document.getElementById('keyword_header').val();
                                            console.log("val = "+checkInput);

                                            if(checkInput==null){
                                                return false;
                                            }else{
                                                return true;
                                            }

                                        }
                                    </script>
                            </div>
                        </div>
                    </li>

                    <sec:authorize access="!isAuthenticated()">
                        <li class="css-bj71cw">
                            <a href="${pageContext.request.contextPath }/member/login" >
                                <button id="login-btn" type="button" class="css-fn0ezc-StylelessButton">로그인</button>
                            </a>
                        </li>

                        <li class="css-bj71cw">
                            <a class="css-memberButton" href="${pageContext.request.contextPath }/member/sign" >
                                <button id="login-btn" type="button" class="css-fn0ezc-StylelessButton">회원가입</button>
                            </a>
                        </li>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <li>
                            <a class="nav-link dropdown-toggle py-0" href="#" data-bs-toggle="dropdown" aria-expanded="true" onclick="callFunction(this);">
                                <img src="https://cdn-icons-png.flaticon.com/512/1827/1827271.png" style="width: 2.5em; height: 2.4em;">
                                <span id="newNoticeCnt" class="badge badge-pill badge-danger"></span>
                            </a>
                            <ul class="dropdown-menu myUl" id="alertInner" aria-labelledby="navbarDropdown">
                                <script>
                                    $('#alertInner').on('click', function(event){
                                        event.stopPropagation();
                                    });
                                </script>
                            </ul>

                        </li>

                        <li>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="/img/profile/${auth.imgs}" class="rounded-circle" height="45px;" width="45px;">
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li class="dropdown-item">${auth.name}</li><hr>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/member">프로필 보기</a></li>

                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/memberlike/${auth.id}">관심목록</a></li>
                                <li><a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#settingModal" style="cursor: pointer">정보 수정</a></li>
                                <hr>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>

<body>

    <div id="msgStack"><!-- 알림 박스 들어가는 영역 --></div>

</body>

                            모달창
<%--    <div id="settingModal" class="modal fade" tabindex="-1" role="dialog">--%>
<%--        <div class="modal-dialog modal-login">--%>
<%--            <div class="modal-content">--%>
<%--                <jsp:include page="/WEB-INF/views/member/setting.jsp" />--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    &lt;%&ndash;                        모달창&ndash;%&gt;--%>
<%--    <div id="deleteModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true" aria-labelledby="deleteModal">--%>
<%--        <div class="modal-dialog modal-login">--%>
<%--            <div class="modal-content">--%>
<%--                <jsp:include page="/WEB-INF/views/member/delete.jsp" />--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
















>
<%--<script>--%>
<%--    // 전역변수 설정--%>
<%--    var socket  = null;--%>
<%--    $(document).ready(function(){--%>
<%--        // 웹소켓 연결--%>
<%--        sock = new SockJS("<c:url value="/echo-ws"/>");--%>
<%--        socket = sock;--%>

<%--        // 데이터를 전달 받았을때--%>
<%--        sock.onmessage = onMessage; // toast 생성--%>
<%--    ...--%>
<%--    });--%>

<%--    // toast생성 및 추가--%>
<%--    function onMessage(evt){--%>
<%--        var data = evt.data;--%>
<%--        // toast--%>
<%--        let toast = "<div class='toast' role='alert' aria-live='assertive' aria-atomic='true'>";--%>
<%--        toast += "<div class='toast-header'><i class='fas fa-bell mr-2'></i><strong class='mr-auto'>알림</strong>";--%>
<%--        toast += "<small class='text-muted'>just now</small><button type='button' class='ml-2 mb-1 close' data-dismiss='toast' aria-label='Close'>";--%>
<%--        toast += "<span aria-hidden='true'>&times;</span></button>";--%>
<%--        toast += "</div> <div class='toast-body'>" + data + "</div></div>";--%>
<%--        $("#msgStack").append(toast);   // msgStack div에 생성한 toast 추가--%>
<%--        $(".toast").toast({"animation": true, "autohide": false});--%>
<%--        $('.toast').toast('show');--%>
<%--    };--%>
<%--</script>--%>