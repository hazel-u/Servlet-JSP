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
	
	${param.n ge 3} <br>
	${param.n==null || param.n==""}<br>
	${empty param.n} <br>
	${not empty param.n}<br>
	${empty param.n?'값이 비어있습니다':param.n} <br>
	${param.n/2}<br>
	${param.n div 2}<br>
</body>
</html>