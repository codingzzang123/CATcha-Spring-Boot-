<%--
  Created by IntelliJ IDEA.
  User: yaele
  Date: 2022-07-15
  Time: 오후 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상세보기</title>
</head>
<body>
<div class="container mx-auto">
    <h1 class="text-2xl mb-10 text-left">Title of Board</h1>
    <form method="POST"action="/board/detail">

        <div class="board_wrap">
            <div class="flex justify-start items-center gap-5">
                <span>카테고리</span>
                <span>${board.cate}</span>
<%--                    <select name="cate" id="${board.cate}" class="p-1 w-48">--%>
<%--                        <option value="일반" selected>일반</option>--%>
<%--                        <option value="영화">영화</option>--%>
<%--                        <option value="TV">TV</option>--%>
<%--                    </select>--%>
            </div>

            <div class="flex w-2/3 justify-start mt-5">
                <input type="text"  class="p-2 w-full" placeholder="제목을 입력해주세요" id ="${board.title}" value="This is an example of the title">
            </div>

            <div class="mt-5 flex justify-center items-center">
                <textarea class="w-full board_write p-5" name="content" id="" cols="30" rows="10" id ="{board.content}" placeholder="내용을 입력해주세요">Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsum qui, ipsa fugiat accusantium officia velit nisi incidunt vel voluptate, perspiciatis, tempora laboriosam aliquam cumque! Facere error nihil ratione odit iste. Lorem, ipsum dolor sit amet consectetur adipisicing elit. Ratione, ex veritatis. Quibusdam, sit dolorum voluptates necessitatibus porro aut. Aliquam laudantium ex inventore blanditiis, laborum at fugiat soluta aliquid dolore explicabo?
                </textarea>
            </div>
        </div>

        <div class="flex justify-end mt-5">
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-white rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center" href='/board/list'>뒤로가기</a>
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-white rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center" href='/board/edit/${board.no}'>수정</a>
            <a class="inline-block w-24 border border-gray-500 bg-gray-500 text-white rounded-md px-4 py-2 m-2 transition duration-500 ease select-none hover:bg-gray-600 focus:outline-none focus:shadow-outline text-center" href='/board/delete/${board.no}'>삭제</a>
        </div>
    </form>

</div>
</body>

</html>

<style>
    * {
        font-family: 'Noto Sans KR', sans-serif;
        color:#333;
    }
    .board_wrap {
        background: #ddd;
        padding:1rem; ;
    }
    .board_write::placeholder{
        display: flex; justify-content: center; align-items: center;
        text-align: center;
        vertical-align: center;
        padding-top:100px;
    }

</style>

