<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/"+request.getContextPath()+"/";	
%>
<base href="basePath">
<link href="css/bootstrap.min.css" rel="stylesheet"/>
<style>
	body{
		padding-top: 30px;
	}
	span{
		font-family: '雅黑';
		font-size: x-large;
		font-weight: bolder;
	}
</style>
</head>
<body>
<%
	List titleList = (List)request.getAttribute("titleList");
	List sumInfoList = (List)request.getAttribute("sumInfoList");
	List importanceList = (List)request.getAttribute("importanceList");
%>
<div class="container-fluid">
<span>原始数据概要分析</span>
<hr/>
<p style="color:red;">
<b>备注：</b><br/>
性别: 0 女  1 男；	高血压：0 无 1 轻度高血压 2中度高血压 3 中度高血压<br/>
疾病：0 无 1 糖尿病 2高血压	饮酒：0 从不 2 轻度 3 中度 6 重度 9 过量		吸烟：0 从不 2 轻度 4 中度 6 重度 8 过量 
</p>
    <table class="table table-striped table-hover">
    	<%
    		for(int i=0; i<titleList.size(); i++){
		%>
				<tr>
					<td><%=titleList.get(i) %></td>	
					<td><%=sumInfoList.get(i*6) %></td>
					<td><%=sumInfoList.get(i*6+1) %></td>
					<td><%=sumInfoList.get(i*6+2) %></td>
					<td><%=sumInfoList.get(i*6+3) %></td>
					<td><%=sumInfoList.get(i*6+4) %></td>
					<td><%=sumInfoList.get(i*6+5) %></td>
				</tr>
		<%
    		}
    	%>
     </table>
<span>随机森林因变量重要性分析结果</span>
<hr/>
	<table class="table table-striped table-hover">
		<%
			int impLength = titleList.size()-1;
    		for(int i=0; i<titleList.size()-1; i++){
		%>
				
				<%
				if((i*6) < (impLength)){
				%>
				<tr>
				<%
				}
				%>	
					<%
					if((i*6) < (impLength)){
					%>
					<td>因变量</td>	
					<td><%=titleList.get(i*6)%></td>
					<%
					}
					%>	
									
					<%
					if((i*6+1) < (impLength)){
					%>
					<td><%=titleList.get(i*6+1)%></td>
					<%	
					}
					%>	
									
					<%
					if((i*6+2) < (impLength)){
					%>
					<td><%=titleList.get(i*6+2)%></td>
					<%	
					}
					%>	
									
					<%
					if((i*6+3) < (impLength)){
					%>
					<td><%=titleList.get(i*6+3)%></td>
					<%	
					}
					%>	
									
					<%
					if((i*6+4) < (impLength)){
					%>
					<td><%=titleList.get(i*6+4)%></td>
					<%	
					}
					%>	
									
					<%
					if((i*6+5) < (impLength)){
					%>
					<td><%=titleList.get(i*6+5)%></td>
					<%	
					}
					%>	
				<%
				if((i*6) < (impLength)){
				%>
				</tr>
				<%
				}
				%>
				
				
				
				<%
				if((i*6) < (impLength)){
				%>
				<tr>
				<%
				}
				%>	
					<%
					if((i*6+0) < (impLength)){	
					%>
					<td>相对重要性</td>	
					<td><%=importanceList.get(i*6+0)%></td>
					<%
					}
					%>
						
					<%
					if((i*6+1) < (impLength)){	
					%>
					<td><%=importanceList.get(i*6+1)%></td>
					<%
					}
					%>
					<%
					if((i*6+2) < (impLength)){	
					%>
					<td><%=importanceList.get(i*6+2)%></td>
					<%
					}
					%>
					<%
					if((i*6+3) < (impLength)){	
					%>
					<td><%=importanceList.get(i*6+3)%></td>
					<%
					}
					%>
					<%
					if((i*6+4) < (impLength)){	
					%>
					<td><%=importanceList.get(i*6+4)%></td>
					<%
					}
					%>
					<%
					if((i*6+5) < (impLength)){	
					%>
					<td><%=importanceList.get(i*6+5)%></td>
					<%
					}
					%>
				<%
				if((i*6) < (impLength)){
				%>
				</tr>
				<%
				}
				%>
				
				
				<%
				int count = impLength+i*6;
				%>
				<%
				if((i*6) < (impLength)){
				%>
				<tr>
				<%
				}
				%>
					
					<%
						if((count+0) < (impLength*2)){	
					%>
					<td>节点纯度</td>	
					<td>0000</td>
					<%
					}
					%>
					<%
						if((count+1) < (impLength*2)){	
					%>
					<td>11</td>
					<%
					}
					%>
					<%
						if((count+2) < (impLength*2)){	
					%>
					<td>222</td>
					<%
					}
					%>
					<%
						if((count+3) < (impLength*2)){	
					%>
					<td>333</td>
					<%
					}
					%>
					<%
						if((count+4) < (impLength*2)){	
					%>
					<td>444</td>
					<%
					}
					%>
					
					<%
						if((count+5) < (impLength*2)){	
					%>
					<td>555</td>
					<%
					}
					%>
				<%
				if((i*6) < (impLength)){
				%>
				</tr>
				<%
				}
				%>
		<%
    		}
    	%>
	</table>
</div>
</body>
</html>