<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班级列表</title>
</head>

<body>
    

	<c:if test="${not empty message}">
        ${message}
    </c:if>
	<br />
	<a href="${pageContext.request.contextPath}/clazz/form">添加班级</a>
	<form id="searchForm" action="${pageContext.request.contextPath}/clazz/search"
		method=POST>
		<input name="q" type="text" value="${q}"> <input type="submit"
			value="搜索">
		<input id="page" name="page" type="hidden" value="${pagePaginator.page}">
	</form>

	<table>
		<thead>
			<tr>
				<th>班级名</th>
				<th>操作</th>
			</tr>

		</thead>
		<tbody>
			<c:forEach items="${page}" var="clazz">
				<tr>
					<td>${clazz.name}</td>
					<td><a
						href="${pageContext.request.contextPath}/clazz/form?id=${clazz.id}">修改</a>
						<a
						href="${pageContext.request.contextPath}/clazz/delete?id=${clazz.id}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<c:if test="${pagePaginator.prePage eq 1 and pagePaginator.page ne 1}">
上一页: <a href="javascript:search(${pagePaginator.prePage})">${pagePaginator.prePage}</a>  
</c:if>
当前页: ${pagePaginator.page}
<c:if test="${pagePaginator.totalPages ne pagePaginator.page}">
下一页: <a href="javascript:search(${pagePaginator.nextPage})">${pagePaginator.nextPage}</a>
</c:if>
 总页数: ${pagePaginator.totalPages}  
总条数: ${pagePaginator.totalCount}
<script>
function search(page){
    
    document.getElementById("page").value = page;
    
    document.getElementById("searchForm").submit();
}

</script> 
</body>
</html>