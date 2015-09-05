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
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${ fn:length(boards) > 0 }">
				<c:forEach items="${ boards }" var="board">
					<tr>
						<td>${ board.id }</td>
						<td><a href="/board/detail.wwz?pageNum=${ pageNum }&idx=${ board.id }">${ board.title }</a></td>
						<td>${ board.regName }</td>
						<td>${ board.hit }</td>
						<td>${ board.updDttmForView }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>등록된 게시물이 없습니다</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div><a href="/board/write.wwz">글쓰기</a></div>
	<%-- 계층형 구현 --%>
	<%-- 페이징 구현 --%>
</body>
</html>