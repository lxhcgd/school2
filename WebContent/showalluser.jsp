<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.icss.vo.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- JSTL+EL形式显示数据 -->
<%-- <%ArrayList<User1995> al  = (ArrayList<User1995>)request.getAttribute("list");%> --%>
	<table border="1px">
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>状态</td>
		</tr>
		<%-- <%for(User1995 a:al){%>
		<tr>
			<td></td>
			<td></td>
			<td></td>
		</tr>
		<%} %> --%>
		
		<c:forEach var="xyz" items="${requestScope.list }" >
			<tr>
				<td>${xyz.username }</td>
				<td>${xyz.password }</td>
				<td>
					<c:if test="${xyz.status==0 }">
						<c:set var="sta" value="等待审核"></c:set>
					</c:if>
					<c:if test="${xyz.status==1 }">
						<c:set var="sta" value="审核通过"></c:set>
					</c:if>
					<c:if test="${xyz.status==2 }">
						<c:set var="sta" value="审核失败"></c:set>
					</c:if>
					${sta }
				</td>
			</tr>
		</c:forEach>
		
		
	</table>






	<!-- jsp表达式形式显示数据 -->
	<%-- <%ArrayList<User1995> al  = (ArrayList<User1995>)request.getAttribute("list");%>
	<table border="1px">
		<tr>
			<td>用户名</td>
			<td>密码</td>
			<td>状态</td>
		</tr>
		<%for(User1995 a:al){%>
		<tr>
			<td><%=a.getUsername()%></td>
			<td><%=a.getPassword()%></td>
			<td>
				<%=a.getStatus()%>
				<%
					String status = a.getStatus();
				String str ="";
				if("0".equals(status)){
					str="等待审核";
				}else if("1".equals(status)){
					str="审核通过";
				}else if("2".equals(status)){
					str="审核未通过";
				}%>
				<%=str%>
			
			
			</td>
		</tr>
		<%} %>
	</table> --%>
	
</body>
</html>