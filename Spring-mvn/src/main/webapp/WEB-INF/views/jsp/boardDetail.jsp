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
	<c:choose>
		<c:when test="${ !isExist }">
			해당 게시물은 존재하지 않습니다
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>번호</th>
					<td>${ board.id }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${ board.title }</td>
				</tr>
				<tr>
					<th>글쓴이</th>
					<td>${ board.regName }</td>
					<th>작성일</th>
					<td>${ board.updDttm }</td>
					<th>조회수</th>
					<td>${ board.hit }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${ board.content }</td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	
	<div>
		<div>댓글</div>
		<div>
		<c:choose>
			<c:when test="${ fn:length(comments) > 0 }">
				<c:forEach items="${ comments }" var="comment">
					<div>이름: ${ comment.regName }</div>
					<div>내용: ${ comment.content }</div>
					<div>작성일: ${ comment.updDttm }</div>
					<a href="/board/comment/delete.wwz?idx=${ comment.id }&pageNum=${pageNum}&contentId=${board.id}">삭제</a>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>등록된 댓글이 없습니다</div>
			</c:otherwise>
		</c:choose>
		</div>
		<div>
			<form action="/board/comment/save.wwz" method="post" name="boardComment">
				<input type="hidden" name="idx" value="${ board.id }" />
				<input type="hidden" name="contentId" value="${ board.id }" />
				<input type="hidden" name="pageNum" value="${ pageNum }" />
				<table>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" name="regName" />
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="content" id="content"></textarea>
						</td>
					</tr>
				</table>
				<input type="submit" value="저장" />
			</form>
		</div>
	</div>
	<div><a href="/board/write.wwz?parentId=${board.id}">답글쓰기</a></div>
	<div><a href="/board/modify.wwz?idx=${ board.id }">수정하기</a></div>
	<div><a href="/board/delete.wwz?idx=${ board.id }">삭제하기</a></div>
	<div><a href="/board/list.wwz?pageNum=${ pageNum }">목록으로</a></div>
</body>
</html>