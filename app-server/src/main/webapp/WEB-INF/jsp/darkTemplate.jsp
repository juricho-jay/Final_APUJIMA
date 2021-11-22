<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>${pageTitle}</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
  <script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
  
  <style>
  
  /* 전체 높이  */
   html,body { 
   margin:0;
   padding:0; 
   width:100%; 
   height:100% 
   }
   container{
    width:1200px;
   }
   
   @media (min-width: 768px) {
      .container {
        width: 900px;
      }
    }
    
    @media (min-width: 992px) {
      .container {
        width: 1200px;
      }
    }
   </style>
  
</head>

<body>
<jsp:include page="darkHeader.jsp"/>
<%-- <jsp:include page="/sidebar.jsp"/> --%>
<div class="container">
<div id="content">
<jsp:include page="${contentUrl}"/>



</div><!-- #content --> 


</div><!-- .container -->

<jsp:include page="footer.jsp"/>
</body>
</html>







