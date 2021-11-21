<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>신고 게시판 목록</title>
   <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>
    .container {
    xborder: 1px solid red;
    xwidth: 640px;
    }
  </style>
  
</head>
<body>
<div class="container">
<h1>신고 게시판 목록</h1>

<!--  <a href='form' class="btn btn-outline-primary btn-sm">분류</a><br>-->
<table class="table table-hover">
<thead>
  <tr>
    <th>게시글 번호</th>
    <th>게시판 종류</th>
    <th>제목</th>
    <th>신고자</th>
    <th>신고 사유</th>
    <th  style = 'text-align: center'>승인관리</th>
  </tr>
</thead>
<tbody>
<c:forEach items="${reportApprovalList}" var="report">
<form action = 'reportConfirm'>
 <input type = 'hidden' name = 'id' value = '${report.requester.id}'>
  <input type = 'hidden' name = 'no' value = '${report.requestBoard.no}'>
<tr>
  <td>${report.requestBoard.no}</td>
 <c:if test='${report.requestBoard.whichBoard == 1}'>
    <td>자유게시판</td> 
  </c:if>
  <c:if test='${report.requestBoard.whichBoard == 2}'>
    <td>Healer지식in</td> 
  </c:if>
  <c:if test='${report.requestBoard.whichBoard == 3}'>
    <td>공지사항</td> 
  </c:if>
    <td><a href = '../board/detail?no=${report.requestBoard.no}'>${report.requestBoard.title}</td>
    <td>${report.requester.id}</td> 
   
    <td>${report.reason}</td> 
    <td><input type = "submit" value ='승인' onclick = "checkOk();"></td>
 <td><input type = "button" value = "거절"  onclick = "reject(this.form);"></td>
   <!--  <td><input type = "button" value = "거절"  onclick = "javascript: form.action = 'reportReject';"></td> -->
    
</tr>
</form>
</c:forEach>
</tbody>
</table>
</div><!-- .container -->

<script>

function reject(frm) {
	frm.action = "reportReject";
	frm.submit();
    alert("사유가 충분하지 않아 게시판을 삭제하지 않습니다.");
	return true;
}

function checkOk() {
    alert("유해한 게시물로 판단되어 삭제되었습니다.");
  }

</script>

</body>
</html>








