<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<!-- 判断session的用户名是否存在 ,，存在说明该用户已经登录，在脚本中写-->
	<!-- 这里使用过滤器进行登录过滤，所以注释了 -->
	<%-- <% 
		String username = (String)session.getAttribute("xyz");	
		if(username==null){
				request.setAttribute("msg", "请登录");
				request.getRequestDispatcher("../index.jsp").forward(request, response);
			}
	%> --%>
	登陆成功了
</body>
</html>