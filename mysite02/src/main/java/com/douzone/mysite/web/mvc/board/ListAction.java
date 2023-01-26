package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.PageVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum = 0;
		int amount = 10;

		if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}

		List<BoardVo> list = new BoardDao().findAll(pageNum, amount);
		request.setAttribute("list", list);

		int total = new BoardDao().totalBoard();
		PageVo pageVo = new PageVo(pageNum, amount, total);
		request.setAttribute("pageVo", pageVo);

		MvcUtil.forward("board/list", request, response);
	}

}
