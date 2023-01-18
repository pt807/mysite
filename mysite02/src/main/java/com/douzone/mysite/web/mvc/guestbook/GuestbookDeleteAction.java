package com.douzone.mysite.web.mvc.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.web.mvc.Action;

public class GuestbookDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		new GuestbookDao().deleteByPassword(password, no);

		response.sendRedirect(request.getContextPath() + "/guestbook?a=list");
	}

}
