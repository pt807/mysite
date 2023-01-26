package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		String suser_no = request.getParameter("user_no");
		Integer user_no = Integer.parseInt(suser_no);

		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);

		new BoardDao().deleteByUserNo(user_no, no);

		MvcUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
	}

}
