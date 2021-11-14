<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Welcome to Apujima</title>
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body class="pt-5">


  
  <!-- 페이지 왼쪽에 달릴것 -->
  <div class="container mt-3">
    <div class="row" class = "my-4">

      <div class="col-sm-12 col-md-3 col-lg-2 p-2">

        <h3 class="my-4 text-center">게시판 선택</h3>
        <div class="list-group mb-4">
          <a href ="freeBoardList"class="list-group-item list-group-item-info text-center font-weight-bold">자유게시판</a>
          <a href="doctorBoardList" class="list-group-item list-group-item-action text-center font-weight-bold">Healer지식in</a>
             <a href="noticeBoardList" class="list-group-item list-group-item-action text-center font-weight-bold">공지사항</a>
        </div>

        </div>
     </div>
   </div>

</body>
</html>