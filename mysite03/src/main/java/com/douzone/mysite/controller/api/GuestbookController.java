package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@RestController("guestbookApiController")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	@GetMapping("")
	public JsonResult getList(@RequestParam(value = "sno", required = true, defaultValue = "0") Long startNo) {
		System.out.println(startNo);
		List<GuestbookVo> list = guestbookService.getMessageList(startNo);

		return JsonResult.success(list);
	}

	@PostMapping("")
	public JsonResult addContent(@RequestBody GuestbookVo vo) {
		guestbookService.addMessage(vo);
		return JsonResult.success(vo);
	}

	@DeleteMapping("{no}")
	public JsonResult delete(@PathVariable("no") Long no,
			@RequestParam(value = "password", required = true, defaultValue = "") String password) {

		System.out.println("password:" + password);

		Boolean r = guestbookService.deleteMessage(no, password);
		
		System.out.println(r);
		if (r == true) {
			return JsonResult.success(no);
		} else {
			return JsonResult.success(-1);
		}
	}
}
