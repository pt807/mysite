package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String shit = request.getParameter("hit");
		Integer hit = Integer.parseInt(shit);

		String sg_no = request.getParameter("g_no");
		Integer g_no = Integer.parseInt(sg_no);

		String so_no = request.getParameter("o_no");
		Integer o_no = Integer.parseInt(so_no);

		String sdepth = request.getParameter("depth");
		Integer depth = Integer.parseInt(sdepth);

		String suser_no = request.getParameter("user_no");
		Integer user_no = Integer.parseInt(suser_no);

		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(hit);
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);
		vo.setUser_no(user_no);
		
		new BoardDao().insert(vo);
		
		MvcUtil.redirect(request.getContextPath() + "/board?a=list", request, response);
	}

}
