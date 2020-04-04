<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 您是第<%=application.getAttribute("counts") %>位登录的用户<br>
	欢迎<%=request.getAttribute("a") %>登录 --%>
	您是第${applicationScope.counts}位登录的用户<br>
	欢迎${requestScope.a }登录<br>
	<a href="admin/admin.jsp">跳转到admin.jsp页面</a><br>
	<a href="<%=response.encodeUrl("admin/admin.jsp")%>">访问admin/admin.jsp(强制吧jsessionid传到目标资源，URL重写)</a><br>
	<a href="LogOffServlet">注销</a><br>
	
	<a href="admin/admin1.jsp">访问admin1.jsp</a><br>
	<a href="admin/admin2.jsp">访问admin1.jsp</a><br>
	<br>
	<br>
	<br>
	<a href="SelectAllUserServlet">查询所有用户</a><br>
</body>
</html>