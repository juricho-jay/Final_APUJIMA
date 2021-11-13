<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
     trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
   <title>메인 화면</title>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  
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
              <table align=right border=0 cellpadding=1 cellspacing="1" width=80 >
                  <form name=form1 method=post action="로그인 누르면 연동되는 곳" >
                     <tr align=left>
                        <td><img src="img/id.gif" /></td>
                        <td><input TYPE="text" NAME=id value="admin" size=15></td>
                     </tr>
                     <tr></tr>
                     <tr align=left>
                        <td><img src="img/password.gif" /></td>
                        <td><input type="password" name=password value="onlyroot" size=15></td>
                        <td rowspan=2>
                        <a href = "/apus/member/MemberSignUp.jsp">
                        <input type="button" value="회원가입" onclick="MemberForm.jsp">
                        </td>
                        </a>
                     </tr>
                  </form>
               </table>
            </h6>
   
   <div class = "Header_Menu">
   
    <a href = ""><button class = "Header_intro" style = "font-size: 2.0em">소개</button></a>
    <a href = "board/list"><button class = "Header_community" style = "font-size: 2.0em">커뮤니티</button></a>
    <a href = "mailbox/list"><button class = "Header_mailBoxForm" style = "font-size: 2.0em">쪽지함</button></a>
    <a href = "medicine/list"><button class = "Header_anything" style = "font-size: 2.0em">약품으로 해볼까</button></a>
    <a href = "bucket/list"><button class = "Header_anything" style = "font-size: 2.0em">버킷리스트</button></a>
    <a href = "doctorinfo/list"><button class = "Header_counselingDoctorList" style = "font-size: 2.0em">HEALER</button></a>
    <a href = "plant/list"><button class = "Header_anything" style = "font-size: 2.0em">화분</button></a>
    
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