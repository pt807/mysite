package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;

	public List<GuestbookVo> getMessageList() {
		return guestbookRepository.findAll();
	}

	public void addMessage(GuestbookVo Vo) {
		guestbookRepository.insert(Vo);
	}

	public void deleteMessage(Long no, String password) {
		guestbookRepository.deleteByPassword(password, no);
	}

}
