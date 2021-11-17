<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>화분 상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>

 
    .container {
        xborder: 1px solid red;
        xwidth: 640px;
    }
  </style>
</head>
<body >
<div class="container">
 
<form name ="PlantInfo" onsubmit="return checkValue()">
<input id = "f-no" type ="hidden" name = "no"  value ="${plant.no}">
 <input id = "ownerName"  type ="hidden" name = "ownerName" value ="${board.owner.id}">
  <table id="datatable-scroller"
  class="table table-bordered tbl_Form">
    <thead>
      <tr>
         <th colspan ="3" style = "background-color: #eeeeee; text-align: center;">화분 보기</th> 
      </tr>
    </thead>
    <tbody>
 

     
        <tr>
           <td>화분 이름</td>
           <td>${plant.plantName}</td>
         <tr>
  
      <tr>
        <td>화분 주인</td>
        <td>${loginUser.nickname}</td>
     </tr>
  
  <tr>
      <td>화분 레벨</td>
      <td>${plant.level}</td>
 </tr>
 
 
 <tr>
      <td>화분 경험치</td>
      <td>${plant.exp }</td>
 </tr>

  <tr>
      <td>화분 생성일</td>
      <td>${plant.registeredDate}</td>
      
  </tr>
  
   <tr>
  
      <td>화분 모양</td>
      <td><img src = ../img/${plant.shape} width ="150" height="150"></td>
  </tr>
 
   <tr>
   <td>
<a href= 'updateForm?no=${plant.no}' class="btn btn-primary" onclick = "return checkVaild()">수정</a>
<a href= 'delete?no=${plant.no}' class="btn btn-primary" >삭제</a>
<a href= 'grow?no=${plant.no}' onclick ="return growalert()" class= "btn btn-primary">물 주기</a>
<a href = 'list'  class= "btn btn-primary">목록</a>
   </td>
   </tr>
     </tbody>

  </table>
  </form>
  </div><!-- .container -->

<script>
function growalert(){
	
	var check0 = confirm("화분에 물을 주시겠습니까?");
	 if(!check0){
		 alert ("화분에 물을 주지 않았습니다.");
		 return false;
	 }
	
	 if (${plant.exp+130 > 1000}){
		    var check = confirm("화분에 물을 주어도 경험치가 1000 이상으로 증가하지 않습니다. 그래도 주시겠습니까?")
		    
		    if(check){
		      alert("화분에 물을 주었습니다.");
		    }else {
		      alert("화분에 물을 주지 않았습니다.");
		      return false;
		    }
		  }
	
	 alert("화분에 물을 주었습니다!")

}
</script>  
</body>
</html>