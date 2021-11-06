<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>메인 화면</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  
  <script src="../node_modules/@popperjs/core//dist/umd/popper.js"></script>
  <script src="../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  
  <style>

  </style>
  
</head>

<body>
  <header>

      <table align = center border = 0   width = 100%>
   <tr>
      <td rowspan = 5 width = 7%>
      <td rowspan = 5 width = 180 align = left> 
      <a href="index.jsp"> <img src="img/logo.png" width="150" height="75"></a> </td>
      <td rowspan = 5 width = 70% > <h2><br>  "왼쪽 허접한 이미지 이쁜걸로 줘라!"APUJIMA </h2> </td>
      
   </tr>

   </table>
 </header>

            <h6>
            <form action="/auth/logout" method = "get">
              <table align=right border=0 cellpadding=1 cellspacing="1" width=200 >
                     <tr align=left>
                        <td>${loginUser.id}님 반갑습니다..</td>
                        <td rowspan=10>
                        <input type="button" value="로그아웃">
                        </td>
                        </a>
                     </tr>
               </table>
              </form>
            </h6>
   
   <div class = "Header_Menu">
   
    <a href = ""><button class = "Header_intro" style = "font-size: 2.0em">소개</button></a>
    <a href = "board/list"><button class = "Header_community" style = "font-size: 2.0em">커뮤니티</button></a>
    <a href = ""><button class = "Header_anything" style = "font-size: 2.0em">테스트 다 해봐</button></a>
   </div>
     <table align=center border="1" cellpadding="8" cellspacing="0" bordercolor="#999999">
      <tr>
         <td>
            <img src="" width="265" height="315"/><br><font color=black><H3  align=center>여긴 뭐라도</H3></font>
         </td>
         <td>
            <img src="" width="265" height="315"/><br><font color=black><H3 align=center>넣으면</H3>
         </td>
         <td>
            <img src="" width="265" height="315"/><br><font color=black><H3 align=center>그림이</H3>
         </td>
         <td>
            <img src="" width="265" height="315"/><br><font color=black><H3 align=center>이쁠것</H3>
         </td>
         <td>
            <img src="" width="265" height="315"/><br><font color=black><H3 align=center>같은데</H3>
         </td>
         </tr>
         </table>
         
         
      <section>
    <div class="contents">
        추가적인 <br>것들 <br>아래에 <br>계속 <br>추가<br>
   
      </div>
    </section>
         
   <footer>
      <hr>
      <div class="footer">
        <p>뭔가가 필요하다면 여기에 적기</p>
        <p>대표번호 : 010-1111-1111 (평일 09:30 ~ 18:30 / 점심 12:00 ~ 13:00)</p>
        <p>운영시간 : 09 : 00 ~ 24 : 00 ㅣ 사업자등록번호 : 000-00-00000 ㅣ 대표: 이제 정해보자</p>
      </div>
      <hr>
      <p class="footer_copyright">ⓒAPUJIMA 주식회사 
      <a = href ="https://www.instagram.com"><img width = "20px" src = "img/instar.png"></a>
      <a = href ="https://www.facebook.com"><img width = "30px" src = "img/facebook.png"></a>
      </p>
    </footer>


</body>
</html>