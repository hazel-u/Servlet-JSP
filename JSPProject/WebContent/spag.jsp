<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	pageContext.setAttribute("result", "pageResult");
%>
<body>
	${requestScope.result}입니다. <br>
	
	${names[0]} <br>
	${names[1]} <br>
	
	${notice.title} <br>
	${notice.id} <br>
	
	${result} <br>
	
	${param.n} <br>
	${header.accept} <br>
</body>
</html>