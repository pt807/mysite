package com.douzone.mysite.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("")
	public String index(Model model,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount) {
		Map<String, Object> map = boardService.getContentsList(pageNum, amount, keyword);

		model.addAllAttributes(map);
		return "board/index";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, BoardVo vo) {

		boardService.addContents(vo);
		return "redirect:/board";
	}

	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no, HttpServletRequest request, HttpServletResponse response,
			Model model) {

		Cookie oldCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("recruitView")) {
					oldCookie = cookie;
				}
			}
		}
		if (oldCookie != null) {
			if (!oldCookie.getValue().contains("[" + no.toString() + "]")) {
				boardService.updateHit(no);
				oldCookie.setValue(oldCookie.getValue() + "_[" + no + "]");
				oldCookie.setPath("/");
				oldCookie.setMaxAge(60 * 60 * 24);
				response.addCookie(oldCookie);
			}
		} else {
			boardService.updateHit(no);
			Cookie newCookie = new Cookie("recruitView", "[" + no + "]");
			newCookie.setPath("/");
			newCookie.setMaxAge(60 * 60 * 24);
			response.addCookie(newCookie);
		}

		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@AuthUser UserVo authUser, Model model, @RequestParam("no") Long no) {

		BoardVo vo = boardService.getContents(no, authUser.getNo());
		model.addAttribute("vo", vo);
		return "board/modify";
	}

	@Auth
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@AuthUser UserVo authUser, BoardVo vo) {

		boardService.updateContents(vo);
		return "redirect:/board/view?no=" + vo.getNo();
	}

	@Auth
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() {
		return "board/delete";
	}

	@Auth
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") Long no,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount,
			@AuthUser UserVo authUser) {

		boardService.deleteContents(no, authUser.getNo());
		return "redirect:/board?pageNum=" + pageNum + "&amount=" + amount + "&keyword=" + keyword;
	}

	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(@RequestParam("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/reply";
	}

	@Auth
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(BoardVo vo,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount) {
		boardService.addReply(vo);
		return "redirect:/board?pageNum=" + pageNum + "&amount=" + amount + "&keyword=" + keyword;
	}
}
