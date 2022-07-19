<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        *{
            box-sizing: border-box;
        }

        #chatt{
            width: 800px;
            margin: 20px auto;
        }

        #chatt #talk{
            width: 800px;
            height: 400px;
            overflow: scroll;
            border : 1px solid #aaa;
        }
        #chatt #msg{
            width: 740px;
            height:100px;
            display: inline-block;
        }

        #chatt #sendZone > *{
            vertical-align: top;

        }
        #chatt #btnSend{
            width: 54px;
            height: 100px;
        }

        #chatt #talk div{
            width: 70%;
            display: inline-block;
            padding: 6px;
            border-radius:10px;

        }

        #chatt .me{
            background-color : #ffc;
            margin : 1px 0px 2px 30%;
        }

        #chatt .other{
            background-color : #eee;
            margin : 2px;
        }
    </style>
</head>
<body>
<div id='chatt'>
    <h1>WebSocket Chatting</h1>
    <input type='text' id='mid' disabled value='${auth.name }'>님 환영합니다.
    <input type='button' value='로그인' id='btnLogin'>
    <br/>
    <div id='talk'></div>
    <div id='sendZone'>
        <textarea id='msg' value='hi...' ></textarea>
        <input type='button' value='전송' id='btnSend'>
    </div>
</div>
<script src='./js/chatt.js'></script>
</body>
</html>