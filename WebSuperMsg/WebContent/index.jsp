<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://sys.com/sys" prefix="sys"%>
<html style="height: 100%;">
<header>
<title>WEBMSG</title>
<link rel="shortcut icon" href="icon/icon.ico">
<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
</header>
<body>
<div id="load">
	<div id="load_bg">
	</div>
	<div id="load_show"><img alt="loading" src="images/spinner.gif"/></div>	
</div>
<script type="text/javascript">
$(function(){
	window.location.href = "${pageContext.request.contextPath}/home";
});
</script>
</body>
</html>