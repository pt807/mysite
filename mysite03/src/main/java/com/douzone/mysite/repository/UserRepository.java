package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataSource.getConnection();

			String sql = "insert into user values(null, ?, ?, password(?), ?, now())";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}

	public UserVo findByNo(Long no) {
		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = "select name, password, gender, email, no from user where no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = new UserVo();

				String name = rs.getString(1);
				String password = rs.getString(2);
				String gender = rs.getString(3);
				String email = rs.getString(4);
				Long userNo = rs.getLong(5);

				result.setName(name);
				result.setPassword(password);
				result.setGender(gender);
				result.setEmail(email);
				result.setNo(userNo);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();
			
			if("".equals(vo.getPassword())) {
				String sql = "update user set name=?, gender=? where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			} else {
				String sql = "update user set name=?, gender=?, password=password(?) where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

}
