<%@page import="dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
<% 

String id=(String)request.getAttribute("id");
String name=(String)request.getAttribute("name");
int age=(Integer)request.getAttribute("age");
String birth=(String)request.getAttribute("birth");
String address=(String)request.getAttribute("address");
int height=(Integer)request.getAttribute("height");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>내정보 페이지</h2>

<div align="center">
<table border="1">
<tr>
	<th>아이디</th>
	<td><%=id %></td>
</tr>
<tr>
	<th>이름</th>
	<td><%=name %></td>
</tr>
<tr>
	<th>나이</th>
	<td><%=age %></td>
</tr>

<tr>
	<th>생년월일</th>
	<td><%=birth %></td>
</tr>
<tr>
	<th>주소</th>
	<td><%=address %></td>
</tr>
<tr>
	<th>키</th>
	<td><%=height %></td>
</tr>

</table>
<br><br>
<form action="member" method="post">
	<input type="hidden" name="param" value="delete">
	<input type="hidden" name="id" value="<%=mem.getId() %>">
	<input type="hidden" id="pwd" name="pwd">
	<input type="submit" id="btn_delete" value="회원탈퇴하기">
</form>
</div>
</body>
</html>
<script type="text/javascript">
$(document).ready(function() {
	$("#btn_delete").click(function(){
		let pwd = prompt("비밀번호를 입력해주세요");
		document.getElementById("pwd").value=pwd;
	});
});
</script>