<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Object obj = session.getAttribute("login");
if(obj == null){
	%>
	<script>
	alert("로그인해 주십시오");
	location.href = "login.jsp";
	</script>	
	<%
}

MemberDto mem = (MemberDto)obj;
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="member" method="post">
	<input type="hidden" name="param" value="mypage">
	<input type="hidden" name="id" value="<%=mem.getId() %>">
	<h2>로그인에 성공하였습니다!</h2>
	<input type="submit" id="btn_mypage" value="내정보">
</form>
</body>
</html>