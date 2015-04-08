<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:if test="${empty clazz.id}">添加班级</c:if> <c:if
		test="${not empty clazz.id}">修改班级</c:if></title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath}/clazz/save"
		method="POST" modelAttribute="clazz">
		<label>班级名<form:input path="name" /></label>
		<form:hidden path="id" />
		<br />
		<form:button>
			<c:if test="${empty clazz.id}">添加</c:if>
			<c:if test="${not empty clazz.id}">修改</c:if>
		</form:button>
	</form:form>
</body>
</html>