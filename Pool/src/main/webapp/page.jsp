 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<div class="pagination">
	<ul>
		<li><a href="article?action=query&curpage=1">首页</a></li>
		<li><a href="article?action=query&curpage=${pb.curPage-1 }">前一页</a></li>
		
		
			<c:forEach begin="1" end="${pb.maxPage }" var="i">
			<li><a href="article?action=query&curpage=${i}">${i}</a></li>
			</c:forEach>
		
		
		
		<li><a href="article?action=query&curpage=${pb.curPage+1 }">下一页</a></li>
		<li><a href="article?action=query&curpage=${pb.maxPage }">尾页</a></li>
	</ul>
</div>