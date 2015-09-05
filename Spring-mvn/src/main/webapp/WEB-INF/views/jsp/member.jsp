<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<body>
	<c:forEach items="${ members }" var="member">
	memberId : ${ member.id } <br/>
	memberName : ${ member.name }<br/>
	-----------------------------<br/>
	</c:forEach>
</body>
</html>