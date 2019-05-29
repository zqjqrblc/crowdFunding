<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>众筹平台</title>
<link rel='stylesheet' type='text/css' href="css/header.css">
<link rel='stylesheet' type='text/css' href="css/register.css">
<link rel='stylesheet' type='text/css' href="css/footer.css">
<script>
	function register() {
		window.location.href = "login.jsp";
	}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="background">
		<div class="register">
			<!-- 标题 -->
			<h1 class="form-title">注册</h1>
			<!-- form -->
			<div class="form-group">
				<label>账号：</label> <input class='' placeholder="请输入您的账号" id="name">
			</div>
			<div class="form-group">
				<label>密码：</label> <input placeholder="请输入您的密码" type="password"
					id="password">
			</div>
			<button class="btn" onclick="register()" id="btnRegister">注册</button>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>