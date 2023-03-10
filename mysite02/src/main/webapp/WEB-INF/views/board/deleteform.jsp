<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.request.contextPath }/board">
					<input type="hidden" name="a" value="delete">
					<input type='hidden' name="user_no" value='${param.user_no }'>
					<input type='hidden' name="no" value='${param.no }'>
					<label>삭제하시겠습니까?</label>
					<div class="bottom" style="margin-top: 50px; text-align-last: center;">
						<c:choose>
							<c:when test="${authUser.no == param.user_no }">
								<input type="submit" value="확인">
								<a href="${pageContext.request.contextPath }/board?a=list">게시판 리스트</a>
							</c:when>
							<c:otherwise>
								<a href="${pageContext.request.contextPath }/board?a=list" style="inline-size: -webkit-fill-available;">
									잘못된 접근 되돌아가기
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>