package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Access Control(보안, 인증체크)
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath(), request, response);
			return;
		}

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String sg_no = request.getParameter("g_no");
		Integer g_no = Integer.parseInt(sg_no);

		String so_no = request.getParameter("o_no");
		Integer o_no = Integer.parseInt(so_no);

		String sdepth = request.getParameter("depth");
		Integer depth = Integer.parseInt(sdepth);

		String suser_no = request.getParameter("user_no");
		Integer user_no = Integer.parseInt(suser_no);

//		if (o_no == 1) {
//			BoardVo vo1 = new BoardVo();
//			vo1.setG_no(g_no);
//			vo1.setO_no(o_no);
//			new BoardDao().o_noUpdate1(vo1);
//		} else {
//			BoardVo vo1 = new BoardVo();
//			vo1.setG_no(g_no);
//			vo1.setO_no(o_no);
//			vo1.setDepth(depth);
//			new BoardDao().o_noUpdate2(vo1);
//		}

		// o_no, depth 확인해서 +1
		BoardVo vo2 = new BoardVo();
		vo2.setTitle(title);
		vo2.setContents(content);
		vo2.setG_no(g_no);
		vo2.setO_no(o_no);
		vo2.setDepth(depth + 1);
		vo2.setUser_no(user_no);
		new BoardDao().o_noUpdate1(vo2);
		new BoardDao().insertReply(vo2);

		MvcUtil.redirect(request.getContextPath() + "/board?a=list", request, response);

	}

}
