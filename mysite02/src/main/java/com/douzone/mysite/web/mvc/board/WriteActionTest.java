package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;

public class WriteActionTest  implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardVo vo = new BoardVo();
		
		for(int i = 1; i <= 150; i++) {
			vo.setTitle("test" + i);
			vo.setContents("content" + i);
			vo.setUser_no(2);
			new BoardDao().insert(vo);
		}
	}
	
}
