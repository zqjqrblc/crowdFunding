<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>众筹平台</title>

<link rel='stylesheet' type='text/css' href="css/header.css">
<link rel='stylesheet' type='text/css' href="css/nav.css">
<link rel='stylesheet' type='text/css' href="css/raise.css">
<link rel='stylesheet' type='text/css' href="css/footer.css">
<script type="text/javascript">
	//表单验证
	function checkForm() {
		var projectName = document.getElementById("projectName").value;
		if (projectName == "") {
			alert("Sorry,projectName cannot be empty.");
			return false;
		}

		var deadLineDate = document.getElementById("deadLineDate").value;
		if (deadLineDate == "") {
			alert("Sorry,deadLineDate cannot be empty.");
			return false;
		}
		
		var deadLineTime = document.getElementById("deadLineTime").value;
		if (deadLineTime == "") {
			alert("Sorry,deadLineTime cannot be empty.");
			return false;
		}
		
		var maxAmount = document.getElementById("maxAmount").value;
		if (maxAmount == "") {
			alert("Sorry,maxAmount cannot be empty.");
			return false;
		}
		
		var minAmount = document.getElementById("minAmount").value;
		if (minAmount == "") {
			alert("Sorry,minAmount cannot be empty.");
			return false;
		}
	}
</script>
</head>
<body>
	<%@include file="header.jsp"%>
	<%@include file="nav.jsp"%>
	<div class="raiseDiv">
		<div class="formDiv">
			<form class="raiseForm" method="post" onsubmit="return checkForm();">
				<table>
					<tr>
						<td><label>项目名称：</label></td>
						<td><input placeholder="请输入项目名称" id="projectName"></td>
					</tr>
					<tr>
						<td><label>发起时间：</label></td>
						<td><label id="startDate">2019.05.28 17：00</label></td>
					</tr>
					<tr>
						<td><label>截止时间：</label></td>
						<td><input type="date" id="deadLineDate"><input
							type="time" id="deadLineTime"></td>
					</tr>
					<tr>
						<td><label>最大集资金额：</label></td>
						<td><input type="number" placeholder="请输入最大集资金额"
							id="maxAmount"></td>
					</tr>
					<tr>
						<td><label>最小集资金额：</label></td>
						<td><input type="number" placeholder="请输入最小集资金额"
							id="minAmount"></td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="btn" type="submit" id="btnLogin">确认</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>