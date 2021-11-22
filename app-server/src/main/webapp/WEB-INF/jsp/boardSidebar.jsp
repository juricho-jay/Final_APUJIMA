<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>

.btn4 {
  
  padding: 10px;
  color: #2c473e;
  font-family: sans-serif;
  text-transform: uppercase;
  text-align: center;
  position: relative;
  text-decoration: none !important;
  display:inline-block;
}

  .btn4::before {
  content: '';
  position: absolute;
  bottom: 0%;
  left: 0px;
  width: 100%;
  height: 1px;
  background: #2c473e;
  display: block;
  -webkit-transform-origin: right top;
  -ms-transform-origin: right top;
  transform-origin: right top;
  -webkit-transform: scale(0, 1);
  -ms-transform: scale(0, 1);
  transform: scale(0, 1);
  -webkit-transition: transform 0.4s cubic-bezier(1, 0, 0, 1);
  transition: transform 0.4s cubic-bezier(1, 0, 0, 1);
}

.btn4:hover::before {
  -webkit-transform-origin: left top;
  -ms-transform-origin: left top;
  transform-origin: left top;
  -webkit-transform: scale(1, 1);
  -ms-transform: scale(1, 1);
  transform: scale(1, 1)
}

h5 {
  text-align: center;
}


aside#left{
    float: left;
    width: 180px;
}
 
aside#left ul{list-style: none; padding-left: 0;}
 
aside#left ul li{
    /* background-color: gray; */
    padding: 5px 10px;
    /* border-bottom: 1px solid black; */
    margin-left: 0; 
    text-align: center;
}
 
aside#left ul li a{
   /*  color: blue; */
    text-decoration: none;
}
 
aside#left ul li:hover{
   /*  background-color: red; */
}
 
aside#main{
    float: left;
}
</style>
<aside id = "left"> 
<!-- <h5>게시판 선택</h5> -->


  <ul>
  <li><a href ="list"class="btn4">전체 목록</a></li>
  <li><a href ="freeBoardList"class="btn4" style="color: #2c473e;">자유게시판 목록</a></li>
  <li><a href="doctorBoardList" class="btn4" style="color: #2c473e;">Healer지식in</a></li>
  <li> <a href="noticeBoardList" class="btn4" style="color: #2c473e;">공지사항</a></li>
</ul>
 
</aside>