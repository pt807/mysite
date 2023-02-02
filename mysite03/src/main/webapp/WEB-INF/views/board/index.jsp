<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		
<!DOCTYPE html>
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
				<form id="search_form" action="${pageContext.request.contextPath }/board" method="post">
					<input type="text" id="kwd" name="keyword" value="${keyword }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>				
			
				
				<c:forEach items="${list }" var="vo" varStatus="status" >
								<tr>
								<td>[${vo.no }]</td>
									<c:if test="${vo.depth == 0}">	
										<td style="text-align: left; padding-left: 0px">
										<a href="${pageContext.request.contextPath }/board/view?no=${vo.no }&pageNum=${pageNum }&amount=${amount }&keyword=${keyword }">
											${vo.title }
										</a>
										</td>
									</c:if>
									<c:if test="${vo.depth == 1}">
										<td style="text-align: left; padding-left: 15px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.request.contextPath }/board/view?no=${vo.no }&pageNum=${pageNum }&amount=${amount }&keyword=${keyword }">
											${vo.title }
										</a>
										</td>
									</c:if>
									<c:if test="${vo.depth > 1}">
										<td style="text-align: left; padding-left: 30px">
										<img src="${pageContext.request.contextPath }/assets/images/reply.png">
										<a href="${pageContext.request.contextPath }/board/view?no=${vo.no }&pageNum=${pageNum }&amount=${amount }&keyword=${keyword }">
											${vo.title }
										</a>
									</c:if>
						
								<td>${vo.name}</td>
								<td>${vo.hit}</td>
								<td><fmt:formatDate value="${vo.reg_date}" pattern="y:M:d-ah:m:s" type="date"/></td>
								<c:choose>
									<c:when test="${authUser.no == vo.user_no }">
										<td><a href="${pageContext.request.contextPath }/board/delete?user_no=${vo.user_no }&no=${vo.no }&pageNum=${pageNum }&amount=${amount }&keyword=${keyword }" class="del">삭제</a></td>
									</c:when>
									<c:otherwise>
										<td></td>
									</c:otherwise>
								</c:choose>
								
							</tr>		
				</c:forEach>
				</table>
				<!-- pager 추가 -->
				<div class="pager"> 
						<ul>
							<c:if test="${pageNum != 1 || prev }">
								<li><a href="${pageContext.request.contextPath }/board?pageNum=1&amount=${amount }&keyword=${keyword }">«</a></li>
							</c:if>
							
							<c:if test="${pageNum != 1 }"> 
								<li><a href="${pageContext.request.contextPath }/board?pageNum=${pageNum - 1 }&amount=${amount }&keyword=${keyword }">◀</a></li>
							</c:if>
							
							<c:forEach var="num" begin="${startPage }" end="${endPage }">
								<li class='${pageNum eq num ? "selected" : "" }'>
								<a href="${pageContext.request.contextPath }/board?pageNum=${num }&amount=${amount }&keyword=${keyword }">${num }</a>
								</li>
							
							</c:forEach>
							
							<c:if test="${pageNum < endPage || next}">
								<li><a href="${pageContext.request.contextPath }/board?pageNum=${pageNum + 1 }&amount=${amount }&keyword=${keyword }">▶</a></li>
							</c:if>
							
							<c:if test="${pageNum < endPage || next }">
								<li><a href="${pageContext.request.contextPath }/board?pageNum=${endPageTotal }&amount=${amount }&keyword=${keyword }">»</a></li>
							</c:if>
						</ul>
					
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="${pageContext.request.contextPath }/board/write?user_no=${authUser.no }&pageNum=${pageNum }&amount=${amount }&keyword=${keyword }" id="new-book">글쓰기</a>
					</c:if >
				
					
				</div>				
			</div>
		</div>
			<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
			<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
	</div>
</body>
</html>