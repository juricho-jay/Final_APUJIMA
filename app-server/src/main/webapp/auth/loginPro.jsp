<!-- loginPro.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
<%
String id = request.getParameter("id");
String pw = request.getParameter("pw");
System.out.println(id + " : " + pw);
%>
아이디: <%=id %><br />
패스워드: <%=pw %><br />
</body>
</html>