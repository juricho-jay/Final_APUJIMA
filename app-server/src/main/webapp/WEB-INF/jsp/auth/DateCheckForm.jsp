<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"
 trimDirectiveWhitespaces="true"%> 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title>dateCheckForm.jsp</title>

  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>  
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
  
  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.5.1/moment.min.js"></script>
    
  <style>
  /* document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10); */
  </style>  
 </head> 
 <body> 
 <div style="text-align: center">

  <h3 style="margin-top: 15%;">출석 체크!</h3>
   <!-- <input type='date' id='currentDate'/> -->
   <form action="dateCheckFinder">
   <button class="btn btn-primary" type="submit">출석체크</button>
   </form>
    </div> 
    
  </body>
  
  <script>
  </script>
</html>

