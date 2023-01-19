package com.douzone.mysite.web.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.UserDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);

		new UserDao().update(vo);

		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
