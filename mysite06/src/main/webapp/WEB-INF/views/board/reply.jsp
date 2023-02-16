<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form class="board-form" method="post" action="${pageContext.request.contextPath }/board/reply">
					<input type = "hidden" name = "pageNum" value= ${param.pageNum }>
					<input type = "hidden" name = "amount" value= ${param.amount }>
					<input type = "hidden" name = "g_no" value="${vo.g_no}">
					<input type = "hidden" name = "o_no" value="${vo.o_no}">
					<input type = "hidden" name = "depth" value="${vo.depth}">
					<input type = "hidden" name = "user_no" value= ${authUser.no }>
					<table class="tbl-ex">
						<tr>
							<th colspan="2">답글 작성</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td><input type="text" name="title" value=""></td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<textarea id="content" name="contents"></textarea>
							</td>
						</tr>
					</table>
					<div class="bottom">
								<a href="${pageContext.request.contextPath }/board/view?no=${param.no }&pageNum=${param.pageNum }&amount=${param.amount }&keyword=${param.keyword}">취소</a>
								<c:if test="${not empty authUser }">
								<input type="submit" value="등록">
								</c:if >
					</div>
				</form>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>