<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<style type="text/css">
	body{
		background-image: url("images/longinbac.jpg");
		background-size: 100%;
   		background-repeat: no-repeat;
	}
	form{
		width: 500px;
	    height: 260px;
	    padding: 13px;
	 
	    position: absolute;
	    left: 50%;
	    top: 50%;
	    margin-left: -200px;
	    margin-top: -200px;
	 
	    background-color: rgba(10, 55, 135, 0.5);
	 
	    border-radius: 10px;
	    text-align: center;
	}
</style>
<script type="text/javascript">
	var xmlHttp;
	function createXMLHttpRequest(){
		if (window.ActiveXObject) {
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        } else if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        }
	}
	function validate(){
		//alert("11");
		//ajax编程
		//1.创建ajax核心对象
		createXMLHttpRequest();
		//2.使用open方法发出ajax请求
		var uname = document.getElementById("uname").value;
		//alert(uname);
		var url = "ValidateNameService?username="+escape(uname);
		//escape加密
		//true---异步提交
		xmlHttp.open("GET",url,true);
		//alert("222")
		//3.当状态发生变化的时候，调用函数
		xmlHttp.onreadystatechange = callback;
		xmlHttp.send(null);
	}
	function  callback(){
		//readyState == 4意味着服务器端返回给客户端响应
		if(xmlHttp.readyState==4){
			if(xmlHttp.status ==200){
				//获得服务器端返回给客户端的字符串
				document.getElementById("span1").innerHTML = xmlHttp.responseText;
			}
		}
	}




		/* js验证登录的用户名和密码是否为空的问题 */
		function fun(){
			//flag用来标记用户名和密码是否为空
			//alert(1);
			var flag=1;
			var uname = document.getElementById("uname").value;
			var pwd = document.getElementById("pwd").value;
			//alert(uname);
			//alert(pwd);
			//用户名为空时
			if(uname==null||uname==""){
				document.getElementById("span1").innerHTML="用户名不能为空";
				document.getElementById("span1").style.color="red";
				flag=2;
			}
			//密码为空时
			if(pwd==null||pwd==""){
				document.getElementById("span2").innerHTML="密码不能为空";
				document.getElementById("span2").style.color="red";
				flag=2;
			}
			//用户名和密码都不为空时
			if(flag==1){
				//获取表单ID
				var f1 = document.getElementById("form1");
				//设置跳转页面
				f1.action="/day0318_javaEE_mvc/LoginServlet";
				//表单提交
				f1.submit(); 
			}
		}
	
	</script>


</head>
<body>
	<!-- cookie的逻辑 -->
	<%
		String username = null;
		String password = null;
		//1.取出客户端的所有cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			//2.将cookie中所有的username和password的值取出
			for(int i=0;i<cookies.length;i++){
				if("username".equals(cookies[i].getName())){
					username = cookies[i].getValue();
				}
				if("password".equals(cookies[i].getName())){
					password = cookies[i].getValue();
				}
				
			}
		}
		//如果用户名和密码都不为空，那么证明登陆成功过，直接跳转到servlet页面
		if(username!=null&&password!=null){
			request.getRequestDispatcher("LoginServlet?username="+username+"&password="+password).forward(request, response);
		}
			
	
	
	%>

		<!-- msg不为空时显示 -->
		<%String msg =(String)request.getAttribute("msg");
		  if(msg!=null&&!"".equals(msg)){%>
			  <font color="red">
			 	 <%-- <%= msg%> --%>
			 	 ${msg }
			  </font>
		<%}%>
		
		<form method="post" id="form1">		
			用户名：<input type="text" id="uname" name="username" value="${param.username }" onblur="validate()">
			<span id="span1"></span><br>
			密码：<input type="text" id="pwd" name="password">
			<span id="span2"></span><br>
			<select name="time">
				<option value="0">每次都需要登录</option>
				<option value="1">1分钟后需要登录</option>
				<option value="3">三分钟后需要登录</option>
			</select>
			<input type="button" id="btn_button" onclick="fun()" value="登录">		
		</form>
	
	
</body>
</html>