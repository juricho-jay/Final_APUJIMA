<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>버킷리스트 등록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
    xborder: 1px solid red;
    xwidth: 640px;
    }
    
    .inputName {
    margin-top: 5%;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>버킷리스트 추가하기</h1>
<table class="table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>작성자</th>
    <th>제목</th>
    <th>내용</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

  <c:forEach items="${bucketList}" var="bucket">
      <tr>
		    <td>${bucket.no}</td>
		    <td>${bucket.writer.id}</td> 
		    <td>${bucket.title}</td> 
		    <td>${bucket.content}</td> 
		    <td>${bucket.registeredDate}</td>
      </tr>
  </c:forEach>
</tbody>   
</table>
</div>
    
   <!--  <form>
    <div id="inputNameContainer">
    <div class="inputName">
      <span class="name">버킷 리스트 추가</span> : <input type="text" name="name1"><br>
    </div>
    </div>
    <button id="btnAddName" type="button">추가</button><br>
    <button id="btn1">확인</button>
  </form>
<script>

var inputNameIndex = 1;
var btnAddName = document.getElementById("btnAddName");
var inputNameContainer = document.querySelector("#inputNameContainer");
var inputNameDiv = document.querySelector(".inputName");

btnAddName.onclick = function() {
  inputNameIndex++;
  var e = inputNameDiv.cloneNode(true);
  e.querySelector(".name").innerHTML = "이름" + inputNameIndex;
  e.querySelector("input").name = "name" + inputNameIndex;
  inputNameContainer.appendChild(e);
};

</script>  --> 
</body>
</html>








