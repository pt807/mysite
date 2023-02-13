package com.douzone.mysite.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;


@Repository
public class GalleryRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<GalleryVo> findAll() {
		return sqlSession.selectList("gallery.findAll");
		
	}

	public void insertImage(String url, String comments) {
		Map<String, Object> map = new HashMap<>();
		map.put("url", url);
		map.put("comments", comments);
		sqlSession.insert("gallery.insertImage" ,map);
	}

	public void deleteImage(Long no) {
		sqlSession.delete("gallery.deleteImage", no);
	}
}

