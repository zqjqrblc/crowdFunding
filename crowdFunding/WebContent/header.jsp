<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String username = "aaa";
%>
<!--这是header-->
<div>
	<header class='top'>
		<img alt="scut" src="images/scut.png">
		<%
			out.print(request.getAttribute("loginRes"));
		%>
	</header>
</div>