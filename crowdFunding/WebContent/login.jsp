<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String loginRes = (String) request.getAttribute("loginRes"); //获取用户登陆是否成功
	if (loginRes == "N") {
%>
<script type="text/javascript">
	alert("登录失败！请重新登录！您输入的账号或密码错误！");
	location.href = "login.jsp";
</script>
<%
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>众筹平台</title>
<link rel='stylesheet' type='text/css' href="css/header.css">
<link rel='stylesheet' type='text/css' href="css/login.css">
<link rel='stylesheet' type='text/css' href="css/footer.css">
<script type="text/javascript">
	//表单验证
	function checkForm() {
		var userName = document.getElementById("userName").value;
		if (userName == "") {
			alert("Sorry,Username cannot be empty.");
			return false;
		}

		var password = document.getElementById("password").value;
		if (password == "") {
			alert("Sorry,Password cannot be empty.");
			return false;
		}
	}

	function register() {
		window.location.href = "register.jsp";
	}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<div class="top-content">
		<div class="left-part">
			<p class="text">值得信赖的项目资金众筹平台</p>
		</div>
		<div class="right-part" id="formBox">
			<div id="form">
				<!-- form -->
				<form class="loginForm" method="post" onsubmit="return checkForm();"
					action="<%=basePath%>ActionServlet">
					<!-- 标题 -->
					<table>
						<tr>
							<td colspan="2">
								<h1 class="form-title">登录</h1>
							</td>
						</tr>
						<tr>
							<td><label>账号：</label></td>
							<td><input placeholder="请输入您的账号" name="userName"></td>
						</tr>
						<tr>
							<td><label>密码：</label></td>
							<td><input placeholder="请输入您的密码" type="password"
								name="password"></td>
						</tr>
						<tr>
							<td colspan="2"><button class="btn" type="submit"
									id="btnLogin">登录</button></td>
						</tr>
						<tr>
							<td colspan="2"><p class="forget">忘记密码?</p></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>