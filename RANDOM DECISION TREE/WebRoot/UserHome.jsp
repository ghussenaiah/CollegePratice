
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
function disableBackButton() {
	window.history.forward();
}
setTimeout("disableBackButton()", 0);
</script>


	</head>

	<body background="./images/bbgg2.jpg">



		<jsp:include page="Header.jsp"></jsp:include>
		<p align="center"><BR><BR><BR><BR><BR><BR>
			<span class="SectionBoldRed"><font color="red"><b>WELCOME
						TO <%=session.getAttribute("role")%>  &nbsp;&nbsp;&nbsp; </font></b> </font> </span>
		</p>
		<br/><br/><br/><br/><br/><br/><br/><br/>
					<jsp:include page="Footer.jsp"></jsp:include>
	</body>
</html>
