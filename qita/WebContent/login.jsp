<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=path %>/js/jquery-1.8.2.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<form id="dl">
	用户名:<input type="text" name="name">
	密码:<input type="text" name="pwd">
	<input type="submit" value="登录" id="btn">
	<input type="submit" value="重置" id="cz">
</form>
</body>
<script type="text/javascript">
	$("#btn").click(function(){
		$.post(
				"<%=path%>/login.do",
				$("#dl").serialize(),
				function(data){
					if(data>0){
						alert("登录成功");
						location="<%=path%>/list.do";
					}else{
						alert("登录失败");
					}
				}
		)
	})
	
</script>
</html>