<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.edm.vo.*" %>
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
		padding-top: 60px;
	}
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
<%
	List<Files> list = (List<Files>)request.getAttribute("list");
	Files file = null;
%>
<div class="container">

    <form action="data" method="post">
        <div class="form-group">
            <label>选择文件</label>
            <select class="form-control" name="fileName">
            	<%
            		for(int i=0; i<list.size(); i++){
            	            				file = (Files)list.get(i);
            	%>
				<option value=<%=file.getFileName() %>><%=file.getFileName() %></option>
				<%		
				}
				%>
          	</select>
        </div>
        <div class="form-group">
            <label>选择算法</label>
            <select class="form-control">
                <option>随机森林</option>
                <option disabled>决策树</option>
                <option disabled>支持向量机</option>
                <option disabled>KNN</option>
                <option disabled>MARS</option>
                <option disabled>RFFS</option>
            </select>
        </div>
        <div class="form-group">
            <label>训练数据集</label>
            <select class="form-control" disabled>
                <option>文件1</option>
                <option>文件2</option>
                <option>文件3</option>
                <option>文件4</option>
            </select>
        </div>
        <div class="form-group">
            <label>设置参数</label>
            <div class="form-inline">
                <div class="form-group">
                    <label>森林大小</label>
                    <input class="form-control" value="默认" placeholder="默认">
                </div>
                <div class="form-group">
                    <label>决策树大小</label>
                    <input class="form-control" value="默认" placeholder="默认">
                </div>
                <div class="form-group">
                    <label>随机种子</label>
                    <input class="form-control" value="随机" placeholder="随机">
                </div>
            </div>
        </div>
        <button class="btn btn-info" type="submit">生成数据</button>
    </form>
</div>
</body>
</html>