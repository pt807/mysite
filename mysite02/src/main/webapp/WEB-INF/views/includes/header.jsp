<%@page import="com.douzone.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
UserVo authuser = (UserVo) session.getAttribute("authUser");
%>
<div id="container">
	<div id="header">
		<h1>MySite</h1>
		<ul>
			<%
			if (authuser == null) {
			%>
			<li><a href="<%=request.getContextPath()%>/user?a=loginfrom">로그인</a>
			<li>
			<li><a href="<%=request.getContextPath()%>/user?a=joinform">회원가입</a>
			<li>
				<%
				} else {
				%>
			
			<li><a href="<%=request.getContextPath()%>/user?a=updateform">회원정보수정</a>
			<li>
			<li><a href="<%=request.getContextPath()%>/user?a=logout">로그아웃</a>
			<li>
			<li><%=authuser.getName() %>님 안녕하세요 ^^;</li>
			<%
			}
			%>
		</ul>
	</div>
</div>