package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class UpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// access control
		HttpSession session = request.getSession();
		if (session == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		UserVo authuser = (UserVo) session.getAttribute("authUser");
		if (authuser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}
		//////////////////////////////////////////////////////////////////
		
		UserVo vo = new UserDao().findByNo(authuser.getNo());
		request.setAttribute("name", vo.getName());
		request.setAttribute("password", vo.getPassword());
		request.setAttribute("gender", vo.getGender());
		request.setAttribute("email", vo.getEmail());
		request.setAttribute("no", vo.getNo());
		MvcUtil.forward("user/updateform", request, response);
	}

}
