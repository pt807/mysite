package com.douzone.mysite.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.UserRepository;
import com.douzone.mysite.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void join(UserVo vo) {
		userRepository.insert(vo);
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findbyEmailAndPassword(vo);
	}

	public UserVo getUser(Long no) {
		return userRepository.findByNo(no);
	}

	public void updateUser(UserVo vo) {
		userRepository.update(vo);
		
	}

}
