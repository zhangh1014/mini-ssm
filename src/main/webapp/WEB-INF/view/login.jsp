<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/master/head.jsp">
	<jsp:param name="title" value="登录 " />
</jsp:include>
<style>
body {
	background: #43A047;
}

.login-panel {
	position: relative;
	width: 400px;
	height: 250px;
	background: #fff;
	padding: 50px;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	border-radius: 3px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-webkit-box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
	box-shadow: 0 10px 10px rgba(0, 0, 0, 0.1);
	width: 400px;
}

.login-txt {
	display: inline-block;
	width: 100%;
	height: 39px;
	line-height: 1.4285714285714286;
	font-size: 1.2rem;
	letter-spacing: 0.1rem;
	color: #091E42;
	background: #FAFBFC;
	border: 1px solid #F4F5F7;
	padding: 0 7px;
	vertical-align: middle;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	border-radius: 5px;
	max-width: 100%;
	transition: background-color 0.2s;
}

.login-txt:hover {
	background: #F4F5F7;
}

.login-txt:active, .login-txt:focus {
	background: #FFF;
	padding: 0 6px;
	border: 2px solid #4CAF50;
}

.login-btn {
	width: 100%;
	height: 39px;
	display: inline-block;
	vertical-align: middle;
	*vertical-align: auto;
	*zoom: 1;
	*display: inline;
	line-height: 1.5;
	font-size: 1.6rem;
	font-weight: 500;
	color: #fff;
	background-color: #43A047;
	border: 1px solid transparent;
	white-space: nowrap;
	text-decoration: none;
	cursor: pointer;
	padding: 0.375rem 0.75rem;
	margin: 0;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
	border-radius: 3px;
	-moz-user-select: -moz-none;
	-ms-user-select: none;
	-webkit-user-select: none;
	user-select: none;
	transition: 0.25s;
}

.login-btn:hover {
	background-color: #4CAF50;
}

.login-btn.active, .login-btn:focus {
	box-shadow: 0 0 0 0.275rem #C8E6C9;
}

.div-password {
	margin-top: 10px;
}

.div-button {
	margin-top: 24px;
}

.login-title {
	width: 400px;
	text-align: center;
	font-size: 3.5rem;
	color: #DCEDC8;
	position: absolute;
	bottom: 0px;
	left: 50%;
	margin-left: -200px;
	margin-bottom: 20px;
	-moz-user-select: -moz-none;
	-ms-user-select: none;
	-webkit-user-select: none;
	user-select: none;
}

.login-error {
	position: absolute;
	top: 20px;
	left: 0px;
	width: 400px;
	text-align: center;
	color: red;
}
</style>
<script>
	$(function() {
		$("#userName").focus();
	});
</script>
</head>
<body
	class="mini-flex mini-flex-t2b mini-flex-main-center mini-flex-cross-center">
	<div class="mini-xs-flex-1" style="position: relative; width: 100%;">
		<div class="login-title" class="login-title">登录到豌豆苗帐户</div>
	</div>
	<div class="login-panel">
		<div class="login-error">${ requestScope.message.msg }</div>
		<sf:form action="login/signin" method="post">
			<div>
				<input id="userName" class="login-txt" name="userName" type="text"
					value="admin" placeholder="用户名" />
			</div>
			<div class="div-password">
				<input id="password" class="login-txt" name="password" value="admin"
					type="password" placeholder="密码" />
			</div>
			<div class="div-button">
				<input class="login-btn" type="submit" value="登 录" />
			</div>
		</sf:form>
	</div>
	<div style="background: red;" class="mini-xs-flex-1"></div>
</body>
</html>