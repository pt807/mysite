package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web2.mvc.Action;
import com.douzone.web2.util.MvcUtil;

public class GuestbookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		new GuestbookDao().deleteByPassword(password, no);

		MvcUtil.redirect(request.getContextPath() + "/guestbook?a=list", request, response);
	}

}
