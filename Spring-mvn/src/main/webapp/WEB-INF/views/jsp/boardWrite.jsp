<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
		table{
			width: 800px;
		}
	</style>
</head>
<body>
	<form action="${ actionUrl }" method="post" name="boardwrite">
		<input type="hidden" name="id" value="${ board.id }"/>	
		<input type="hidden" name="grp" value="${ grp }"/>
		<input type="hidden" name="order" value="${ order }"/>
		<input type="hidden" name="lvl" value="${ lvl }"/>
		<input type="hidden" name="parentId" value="${ parentId }"/>
	
	<table>
		<tr>
			<th>제목</th>
			<td>
				<c:choose>
					<c:when test="${ board == null }">
						<input type="text" name="title" />
					</c:when>
					<c:otherwise>
						<input type="text" name="title" value="${ board.title }"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>글쓴이</th>
			<td>
				<c:choose>
					<c:when test="${ board == null }">
						<input type="text" name="regName" />
					</c:when>
					<c:otherwise>
						<input type="text" name="regName" value="${ board.regName }"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<c:choose>
					<c:when test="${ board == null }">
						<textarea name="content" id="content"></textarea>
					</c:when>
					<c:otherwise>
						<textarea name="content" id="content">${ board.content }</textarea>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	<div>
		<input type="submit" value="저장" />
		<a href="/board/list.wwz">취소</a>
	</div>
	</form>
</body>
</html>