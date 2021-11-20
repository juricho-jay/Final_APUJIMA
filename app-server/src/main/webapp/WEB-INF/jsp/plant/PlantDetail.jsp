<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

  <title>화분 상세</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
body {
    font-family: 'Roboto Condensed', sans-serif;
    background-color: #f5f5f5;
}
.left-side-pic img {
    width: 100%;
}

.right-plant-detail span{
 font-size: 15px;
}
.right-plant-detail p{
font-size: 25px;
    color: #000000;
}
 
    .container {
        xborder: 1px solid red;
        xwidth: 640px;
    }
  </style>

<div class="container">
  <div class = "row m-0">
    <h1>화분 상세보기</h1>
    <div class ="col-lg-4 left-side-pic pb-3">
       <img src = ../../img/${plant.shape} >
    </div>
    
    <div class ="col-lg-8">
      <div class ="right-plant-detail border p-3 m-0">
        <div class = "col-lg-12">
          <span>Apus 화분</span>
          <p class ="m-0 p-0">${loginUser.nickname}님의 화분 </p>
        </div>
        
        <div class = "col-lg-12">
          <span>화분 상세</span>
          <p class ="m-0 p-0">${loginUser.nickname}님이 지어주신 화분 이름 : ${plant.plantName} </p>
          <p> 화분 레벨 : ${plant.level}</p>
          <p> 화분 경험치 : ${plant.exp }</p>
          <p> 화분이 생성된 날짜 : ${plant.registeredDate}</p>
        </div>
          <form>
      
      <a href= 'updateForm?no=${plant.no}' class="btn btn-primary" onclick = "return checkVaild()">수정</a>
      <a href= 'delete?no=${plant.no}' class="btn btn-primary" >삭제</a>
      <a href= 'grow?no=${plant.no}' onclick ="return growalert()" class= "btn btn-primary">물 주기</a>
      <a href = 'list'  class= "btn btn-primary">목록</a>
  </form>
      </div>  
    
    </div>


  </div>
</div> <!-- Container -->
   

<script>
function growalert(){
	
	var check0 = confirm("화분에 물을 주시겠습니까?");
	 if(!check0){
		 alert ("화분에 물을 주지 않았습니다.");
		 return false;
	 }
	
	 if (${plant.exp+130 > 1000}){
		    var check = confirm("화분에 물을 주어도 경험치가 1000 이상으로 증가하지 않습니다. 그래도 주시겠습니까?")
		    
		    if(!check){
		      alert("화분에 물을 주지 않았습니다.");
		      return false;
		    }
		  }
	
	 alert("화분에 물을 주었습니다!")
	 

}
</script>  


