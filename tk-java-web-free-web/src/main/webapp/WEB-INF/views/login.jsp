<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="container-login100" style="background-image: url(<c:url value='/template/login/images/bg-01.jpg'/>);">
		<div class="wrap-login100 p-t-30 p-b-50">
			<span class="login100-form-title p-b-41">
				Account Login
			</span>
			<c:if test="${param.incorrectAccount != null}">
				<div class="alert alert-block alert-danger">Sai mật khẩu hay sai tài khoản</div>
			</c:if>
			<c:if test="${param.accessDenied != null}">
				<div class="alert alert-block alert-danger">Bạn không có quyền truy cập vào trang này</div>
			</c:if>
			<form action="j_spring_security_check" method="POST" class="login100-form validate-form p-b-33 p-t-5">

				<div class="wrap-input100 validate-input" data-validate = "Enter username">
					<input class="input100" type="text" name="j_username" placeholder="User name" id="username" autocomplete = "off">
					<span class="focus-input100" data-placeholder="&#xe82a;"></span>
				</div>

				<div class="wrap-input100 validate-input" data-validate="Enter password">
					<input class="input100" type="password" name="j_password" placeholder="Password" id="password">
					<span class="focus-input100" data-placeholder="&#xe80f;"></span>
				</div>

				<div class="container-login100-form-btn m-t-32">
					<button class="login100-form-btn">
						Login
					</button>
				</div>

			</form>
		</div>
	</div>
</body>
</html>