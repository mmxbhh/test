<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript">
	function toDel(sid){
		location="<%=path%>/toDel.do?sid="+sid;
	}


	$(function(){
		$("#qx").click(function(){
			$(".ck").attr("checked",true);
		})
		
		$("#fx").click(function(){
			$(".ck").each(function(){
				if($(this).attr("checked")){
					$(this).attr("checked",false);
				}else{
					$(this).attr("checked",true);
				}
			})
		})
		
		
		$("#ps").click(function() {
			var sid="";
			$(".ck:checked").each(function() {
				sid+=","+$(this).val();
			})
			sid=sid.substring(1);
			toDel(sid);
		})
		
	})
</script>
<body>
<table>
	<tr>
		<td></td>
		<td>编号</td>
		<td>名字</td>
		<td>地址</td>
		<td>住址</td>
		<td>电话</td>
		<td>描述</td>
		<td>操作</td>
	</tr>
	<c:forEach items="${list}" var="l">
		<tr>
			<td><input type="checkbox" class="ck" value="${l.sid}"></td>
			<td>${l.sid}</td>
			<td>${l.sname}</td>
			<td>${l.address}</td>
			<td>${l.id}</td>
			<td>${l.tel}</td>
			<td>${l.remark}</td>
			<td>
				<input type="button" value="删除" onclick="toDel(${l.sid})">
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="2">
			<input type="button" value="全选" id="qx">
			<input type="button" value="反选" id="fx">
			<input type="button" value="批量删除" id="ps">
		</td>
	</tr>
</table>
</body>
</html>