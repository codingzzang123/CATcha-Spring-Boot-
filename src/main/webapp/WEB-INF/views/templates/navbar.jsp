<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="/css/hosun/nav.css" rel="stylesheet"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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

        .alertDiv{
            width: 400px; height: 150px; border-radius: 5em;
        }

        .myUl{ width: 400px; height:auto; max-height: 255px; overflow-y: scroll; white-space: nowrap;
            margin-top: 20px;
        }

        .alertImg{
            height: 2em; width: 2em; margin-left: 20px;
        }

        .alertDiv1{
            display: inline-block;
        }

        .alertDiv2{
            display: inline-block;
            margin-left: 20px;
        }

        .alertSpan{
            font-size: small;
        }

        .alertBorder{
            border: #2563eb;
        }
        /*.myLi{ display: inline-block; *display: inline; zoom: 1 }*/
    </style>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script>
        var socket;
        var name = '${auth.name }'

        $(document).ready(function(){
            if( ${auth ne null} ){ //로그인 했을때
                connect();
                AlertCount(name);
            }
        });

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
                AlertCount(name);

                var data = evt.data;

                let toast = "<div class='toast' id='toast' data-autohide='false'>";
                toast += "<div class='toast-header'><strong class='mr-auto text-primary'>알림</strong>";
                toast += "<small class='text-muted'>just now</small><button type='button' id='test' class='ml-2 mb-1 close' data-bs-dismiss='toast' aria-label='Close'>";
                toast += "&times;</button></div>";
                toast += "<div class='toast-body'>" + data + "</div></div>";
                $("#msgStack").append(toast);   // msgStack div에 생성한 toast 추가
                $(".toast").toast({"animation": true, "autohide": false});
                $('.toast').toast('show');
                // $("#newNoticeCnt").text($("#newNoticeCnt").text()*1+1); //알림 받았을때 카운트 +1증가

            };

        }

        /* 1. 알림 카운트 가져오기 */
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

        function callFunction() { //징글벨 눌렀을때
            AlertCount();
            getAlertList();
            return true;
        }


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
                            innerDate = date.getMonth()+1+"."+date.getDate()+" "+date.getHours()+":"+date.getMinutes();
                            title = data[i].title;
                            if(title.length > 8) {
                                title = title.substring(0, 10) + ".."
                            }

                            if(data[i].code == 0){
                                innerMessage = "님이 좋아요를 눌렀습니다.";
                                type ="게시글에"
                            }else if(data[i].code == 1){
                                innerMessage = "님이 댓글을 달았습니다.";
                                type ="게시글에"
                            }else{
                                innerMessage = "님이 댓글을 달았습니다.";
                                type ="댓글에"
                            }

                            alertHTML += "<li class='myLi'><div style='margin-top: 1px;' class='alertBorder'><div class='alertDiv1'><img class='rounded-circle alertImg' src='/img/profile/"+data[i].imgs+"'/><br>"+
                                        "<span class='alertSpan' style='margin-left: 7px;'>"+innerDate+"</span></div>"+
                                    "<div class='alertDiv2'>"+
                                            "<a href='"+contextPath+"/board/"+data[i].bno+"'><span class='alertSpan'>회원님의 [<strong>"+title+"</strong>] "+" "+type+"<br>"+
                                                "<strong>"+data[i].pubName+"</strong>"+innerMessage+"</span></div></div><hr></li>";

                        }

                    }else{
                        alertHTML = "<div style='text-align: center'>"+
                            "<span style='font-size: medium; font-style: italic;'>확인 할 알림이 없습니다.</span>"+
                        "</div>";
                    }
                    document.querySelector('#alertInner').innerHTML = alertHTML;
                }
            });
        }
        // $("#navAlert").click(function(){
        //     AlertCount();
        // });
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
                                <form action="<c:url value="/search"/>" method="get" onsubmit="return submit()">
                                    <label class="css-kyr608" id="label">
                                        <div class="search">
                                            <input type="text" autocomplete="off" placeholder="다양한 콘텐츠를 검색해보세요." name="query" class="css-13i5xe6" id="keyword_header">
                                        </div>
                                        <div value="false" class="css-ikj5sk">
                                            <span aria-label="clear" role="button" class="css-14vwb0g"></span>
                                        </div>
                                    </label>
                                    <script>
                                        function submit(){
                                            let val = $("input[name='query']").val();
                                            searchForm.find("input[name='query']").val(val);

                                            if(val != null){
                                                return true;
                                            }else{
                                                return false;
                                            }
                                        }
                                    </script>
                                </form>
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
                                <svg width="2.3em" height="2.3em" viewBox="0 0 16 16" class="bi bi-bell" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2z"/>
                                    <path fill-rule="evenodd" d="M8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z"/>
                                </svg>
                                <span id="newNoticeCnt" class="badge badge-pill badge-danger"></span>
                            </a>
                            <ul class="dropdown-menu myUl" id="alertInner" aria-labelledby="navbarDropdown">

                            </ul>
                        </li>


                        <li>
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <img src="/img/profile/${auth.imgs}" class="rounded-circle" height="45px;" width="45px;">
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li class="dropdown-item">${auth.name}</li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/member">프로필 보기</a></li>
                                <hr>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/memberlike/${auth.id}">관심목록</a></li>
                                <li><a class="dropdown-item" href="">설정</a></li>
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

    <div id="msgStack">

    </div>

</body>

<%--<div class="toast">--%>
<%--    <div class="toast-header">--%>
<%--        <strong class="mr-auto text-primary">알림</strong>--%>
<%--        <small class="text-muted">just now</small>--%>
<%--        <button type="button" id="test" class="ml-2 mb-1 close" data-bs-dismiss='toast' aria-label='Close'>--%>
<%--            &times;--%>
<%--        </button>--%>
<%--    </div>--%>
<%--    <div class="toast-body">회원님의 게시물에 댓글이 달렸습니다.</div>--%>
<%--</div>--%>
















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