<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.edm.vo.User" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	body{
		background-color: #09C;
	}
	table{
		width:100%;
		height:60px;
	}
	.title{
		padding-left: 100px;
		font-weight: bolder;
		font-size:x-large;
		color:#fff;
		width:90%;
	}
</style>
<title>头部</title>
</head>
<body>
<table>
	<tr>
		<td class="title">
			教育数据挖掘管理系统   &nbsp;
		</td>
		<td>
			<img alt="" src="images/user.jpg" width="20px" height="20px" align="middle"/>
			&nbsp;<%User user = (User)session.getAttribute("user");%>
			<%=user.getUsername()%>
		</td>
	</tr>
</table>

</body>
</html>