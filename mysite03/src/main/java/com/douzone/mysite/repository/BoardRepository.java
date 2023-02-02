package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<BoardVo> findAllByPageAndKeyWord(int pageNum, int amount, String keyword) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", (pageNum - 1) * amount);
		map.put("amount", amount);
		map.put("keyword", keyword);
		
		return sqlSession.selectList("board.findAllByPageAndKeyWord", map);
	}

	public int getTotalCount(String keyword) {
		return sqlSession.selectOne("board.getTotalCount", keyword);
	}
	
	public BoardVo findByNo(Long no) {
		return sqlSession.selectOne("board.findByNo", no);
	}

	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}

	public void update(BoardVo vo) {
		sqlSession.update("board.update", vo);
	}

	public void deleteByUserNo(Long no, Long user_no) {
		Map<String, Object> map = Map.of("no", no, "user_no", user_no);
		sqlSession.delete("board.deleteByUserNo", map);
	}
	
	public void oNoUpdate(BoardVo vo) {
		sqlSession.update("board.oNoUpdate", vo);
	}
	
	public void insertReply(BoardVo vo) {
		sqlSession.insert("board.insertReply", vo);
	}

	public void updateByHit(Long no) {
		sqlSession.update("board.updateByHit", no);
	}

}
