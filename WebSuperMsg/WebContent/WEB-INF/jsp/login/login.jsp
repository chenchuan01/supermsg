<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html style="overflow: hidden;">
<head>

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
<%@include file="../base/icon.jspf"%>
<script type="application/x-javascript">
var basePath = '<%=basePath%>';	 
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
<link href='${ctx }/login/css/fonts.useso.com.pt.css' type='text/css'>
<link href='${ctx }/login/css/fonts.useso.com.ex.css' rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script type="text/javascript" src="http://ajax.useso.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/js/common.js"></script>
</head>
<body >
	<script>
		$(document).ready(function(c) {
			$('.close').on('click', function(c) {
				$('.login-form').fadeOut('slow', function(c) {
					$('.login-form').remove();
				});
			});
		});
		function showRigist(){
			$('#regist').toggle();
			$('#login').toggle();
		}
	</script>
	<!--SIGN UP-->
	<div class="login-form" >
		<div class="close"></div>
		<div class="head-info">
			<label class="lbl-1" style="cursor: pointer;" title="Login" onclick="showRigist()"> </label> 
			<label class="lbl-2" style="cursor: pointer;" title="Regist" onclick="showRigist()"> </label> 
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img  src="${ctx}/login/images/avatar.png" />
		</div>
		<c:if test="${exp != null }">
			<div class="tip span3" style="color:#fff">
				${exp.msg }
			</div>
      	</c:if>
		<div id="login">
			<form id="loginForm" action="login/verify.do" method="post">
				<input type="text" class="text user" value="" name="userName" placeholder="Username" valid="required"/>
				<div class="key">
					<input type="password" value="" name="password" placeholder="Password" valid="required"/>
				</div>
			</form>
			<div class="signin">
				<input type="button" onclick="valid('#loginForm',function(){$('#loginForm').submit();})" value="Login">
			</div>
		</div>
		<div id="regist" style="display: none;">
			<form id="registForm" action="login/regist.do" method="post">
				<input type="text" class="text hotname" value="" name="name" placeholder="昵称" valid="required"/>
				<input type="text" class="text user" value="" name="userName" placeholder="Username" valid="required"/>
				<div class="key">
					<input type="password" value="" name="password" placeholder="Password" valid="required"/>
				</div>
			</form>
			<div class="signin">
				<input type="button" onclick="valid('#registForm',function(){$('#registForm').submit();})" value="Regist">
			</div>
		</div>
	</div>
</body>
</html>