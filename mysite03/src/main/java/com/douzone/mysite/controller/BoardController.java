package com.douzone.mysite.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

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

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(BoardVo vo) {
		boardService.addContents(vo);
		return "redirect:/board";
	}

	@RequestMapping("/view")
	public String view(@RequestParam("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/view";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Model model, @RequestParam("no") Long no) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo", vo);
		return "board/modify";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVo vo) {
		boardService.updateContents(vo);
		return "redirect:/board/view?no=" + vo.getNo();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete() {
		return "board/delete";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("user_no") Long user_no, @RequestParam("no") Long no,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1", required = false) int pageNum,
			@RequestParam(value = "amount", defaultValue = "10", required = false) int amount) {
		boardService.deleteContents(no, user_no);
		return "redirect:/board?pageNum=" + pageNum + "&amount=" + amount + "&keyword=" + keyword;
	}
}
