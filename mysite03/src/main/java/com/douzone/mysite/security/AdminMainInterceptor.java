package com.douzone.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

public class AdminMainInterceptor implements HandlerInterceptor {
	@Autowired
	private SiteService siteService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		SiteVo vo = siteService.getSite();
		request.getServletContext().setAttribute("siteVo", vo);
		request.getRequestDispatcher("/WEB-INF/views/main/index.jsp").forward(request, response);
		return false;
	}

}
