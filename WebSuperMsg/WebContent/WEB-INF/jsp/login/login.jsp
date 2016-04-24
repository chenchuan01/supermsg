<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../base/icon.jspf"%>
<%@include file="../base/baseTitle.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
	response.addHeader("pragma", "no-cache");
	response.addHeader("cache-control", "no-cache");
	response.addHeader("expires", "0");
%>
<base href="<%=basePath%>">
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="application/x-javascript">
	 
addEventListener("load", function() { 
	setTimeout(hideURLbar, 0); 
	}, false); 
function hideURLbar(){ 
	window.scrollTo(0,1
	); 
} 

</script>
<link href="${ctx }/login/css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='http://fonts.useso.com/css?family=PT+Sans:400,700,400italic,700italic|Oswald:400,300,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script type="text/javascript" src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="ctx"></script>
</head>
<body>
	<script>
		$(document).ready(function(c) {
			$('.close').on('click', function(c) {
				$('.login-form').fadeOut('slow', function(c) {
					$('.login-form').remove();
				});
			});
		});
	</script>
	<!--SIGN UP-->
	<div class="login-form">
		<div class="close"></div>
		<div class="head-info">
			<label class="lbl-1"> </label> 
			<label class="lbl-2"> </label> 
			<label class="lbl-3"> </label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="${ctx}/login/images/avtar.png" />
		</div>
		<form id="loginForm" action="login/verify.do" method="post">
			<input type="text" class="text" value="" name="userName" placeholder="Username" valid="repuired"/>
			<div class="key">
				<input type="password" value="" name="password" placeholer="Password" valid="repuired"/>
			</div>
		</form>
		<div class="signin">
			<input type="button" onclick="valid('#loginForm',function(){$('#loginForm').submit()})" value="Login">
		</div>
	</div>
</body>
</html>