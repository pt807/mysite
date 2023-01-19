<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String name = (String)request.getAttribute("name");
String password = (String)request.getAttribute("password");
String gender = (String)request.getAttribute("gender");
String email = (String)request.getAttribute("email");
Long no = (Long)request.getAttribute("no");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"/>
		
		<div id="content">
			<div id="user">

				<form id="update-form" name="updateForm" method="post" action="<%=request.getContextPath() %>/user">
					<input type="hidden" name='a' value="update">
					<input type="hidden" name='no' value="<%=no %>">
					<label class="block-label" for="name">이름</label>
					<input id="name" name="name" type="text" value="<%=name %>">

					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="<%=email %>" readonly="readonly">
					
					<label class="block-label">패스워드</label>
					<input name="password" type="password" value="<%=password %>">
					
					<fieldset>
						<legend>성별</legend>
						<%if(gender.equals("female")) {%>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>남</label> <input type="radio" name="gender" value="male">
						<%} else {%>
						<label>여</label> <input type="radio" name="gender" value="female">
						<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
						<%} %>
					</fieldset>
					
					<input type="submit" value="수정하기">
					
				</form>
			</div>
		</div>
		<div id="navigation">
			<jsp:include page="/WEB-INF/views/includes/navigation.jsp"/>
		</div>
		<div id="footer">
			<jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
		</div>
	</div>
</body>
</html>