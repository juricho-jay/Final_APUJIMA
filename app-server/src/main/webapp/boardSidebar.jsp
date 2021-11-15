<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
aside#left{
    float: left;
    width: 10em;
    margin-right: 1em;
}
 
aside#left ul{ list-style: none}
 
aside#left ul li{
    background-color: gray;
    padding: 5px 10px;
    border-bottom: 1px solid black;
}
 
aside#left ul li a{
    color: blue;
    text-decoration: none;
}
 
aside#left ul li:hover{
    background-color: red;
}
 
aside#main{
    float: left;
}
</style>
<aside id = "left"> 
<div id = "container">
<h4 style= "margin-left :30px"> 게시판 선택</h4>


  <ul style = "width : 260px; height : 100px">
  <li><a href ="list"class="list-group-item list-group-item-action text-center font-weight-bold">전체 목록</a></li>
  <li><a href ="freeBoardList"class="list-group-item list-group-item-action text-center font-weight-bold">자유게시판 목록</a></li>
  <li><a href="doctorBoardList" class="list-group-item list-group-item-action text-center font-weight-bold">Healer지식in</a></li>
  <li> <a href="noticeBoardList" class="list-group-item list-group-item-action text-center font-weight-bold">공지사항</a></li>
</ul>
</div>     
 
</aside>