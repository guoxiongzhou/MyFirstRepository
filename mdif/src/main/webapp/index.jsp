<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录页</title>
<link type="text/css" href="<%=request.getContextPath()%>/css/styles.css" rel="Stylesheet" />
<script src="./js/jquery.js" type="text/javascript"></script>
</head>
<body>

	<div class="main login-bg">
		<div class="login-con">
			<form action="user/dologin.do" method="post">
				<table class="login-table">
					<tr>
						<td><div class="username-img">
								<span><input type="text" name="userName"></span>
							</div></td>
					</tr>
					<tr>
						<td><div class="password-img">
								<span><input type="password" name="password"></span>
							</div></td>
					</tr>
					<tr>

						<td><button class="btn-login">登录</button></td>

					</tr>
					<tr>
						<td><span class="red-text">用户名或密码错误</span></td>
					</tr>
					</form>
				</table>
		</div>
	</div>


</body>
</html>