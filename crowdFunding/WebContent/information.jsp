<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>众筹平台</title>

<link rel='stylesheet' type='text/css' href="css/header.css">
<link rel='stylesheet' type='text/css' href="css/nav.css">
<link rel='stylesheet' type='text/css' href="css/information.css">
<link rel='stylesheet' type='text/css' href="css/footer.css">
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="nav.jsp"%>
	<div class="informationDiv">
		<%@include file="projectsNav.jsp"%>
		<div class="information">
			<table>
				<tr>
					<td>用户名：</td>
					<td id="userName">${user.userName}</td>
				</tr>
				<tr>
					<td>账户金额：</td>
					<td id="amount">$</td>
				</tr>
				<tr>
					<td>已发起项目：</td>
					<td id="projectsRaisedNum">2</td>
				</tr>
				<tr>
					<td>已投资项目：</td>
					<td id="projectsInvestedNum">3</td>
				</tr>
			</table>
		</div>

	</div>


	<%@include file="footer.jsp"%>
</body>
</html>