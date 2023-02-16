package com.douzone.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;

@Service
public class BoardService {
//	private static final int LIST_SIZE = 5; // 리스팅되는 게시물의 수
//	private static final int PAGE_SIZE = 5; // 페이지 리스트의 페이지 수

	@Autowired
	private BoardRepository boardRepository;

	public void addContents(BoardVo vo) {
		boardRepository.insert(vo);
	}

	public BoardVo getContents(Long no) {		
		return boardRepository.findByNo(no);
	}

	public BoardVo getContents(Long no, Long userNo) {
		return boardRepository.findByNoAndUserNo(no, userNo);
	}

	public void updateContents(BoardVo vo) {
		boardRepository.update(vo);
	}

	public void deleteContents(Long no, Long userNo) {
		boardRepository.deleteByUserNo(no, userNo);
	}
	
	public void addReply(BoardVo vo) {
		vo.setDepth(vo.getDepth() + 1);
		boardRepository.oNoUpdate(vo);
		boardRepository.insertReply(vo);
	}
	
	public void updateHit(Long no) {
		boardRepository.updateByHit(no);
	}

	public Map<String, Object> getContentsList(int pageNum, int amount, String keyword) {
		int toTalCount = boardRepository.getTotalCount(keyword);
		// 1. view에서 게시판 리스트를 렌더링 하기 위한 데이터 값 계산
		int startPage;
		int endPage;
		boolean prev;
		boolean next;
		int total;
		int endPageTotal;

		total = toTalCount;

		// ceil 반올림
		endPage = (int) Math.ceil(pageNum * 0.1) * amount;

		startPage = endPage - amount + 1;

		// 전체글 / 화면에 뿌려줄 데이터 개수
		endPageTotal = (int) Math.ceil(total / (double) amount);

		if (endPage > endPageTotal) {
			endPage = endPageTotal;
		}

		prev = startPage > 1;

		next = endPage < endPageTotal;

		// 2.리스트 가져오기
		List<BoardVo> list = boardRepository.findAllByPageAndKeyWord(pageNum, amount, keyword);

		// 3. 리스트 정보를 맵에 저장
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("prev", prev);
		map.put("next", next);
		map.put("total", total);
		map.put("endPageTotal", endPageTotal);
		map.put("pageNum", pageNum);
		map.put("amount", amount);
		map.put("keyword", keyword);

		return map;
	}
}
