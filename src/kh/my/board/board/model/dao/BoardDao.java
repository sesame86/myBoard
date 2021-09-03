package kh.my.board.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.my.board.board.model.vo.Board;
import kh.my.board.comm.JDBCTemplate;

public class BoardDao {
	public BoardDao() {}
	public Board getBoard(Connection conn, int bno) {
		Board vo = null;
		String query = "select bno, bref, bre_level, bre_step from board_r where bno like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setBref(rs.getInt("bref"));
				vo.setBreLevel(rs.getInt("bre_level"));
				vo.setBreStep(rs.getInt("bre_step"));
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return vo;
	}
	//총 글수
	public int getBoardCount(Connection conn) {
		int result = 0;
		String query = "select count(bno) from board_r";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			//-1
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//create
	public int insertBoard(Connection conn, Board vo) {
		int result = -1;
		PreparedStatement ps = null;
		//답글, 답답글, 답답답글...
		String sqlUpdate = "update board_r set bre_step = bre_step+1"
				+ " where bref = ? and bre_step > ?";
		//새글
		String sqlInsertNew = "insert into  board_r (bno, title, content, writer, bref, bre_level, bre_step)"
				+ " VALUES (seq_board.nextval, ?, ?, ?, seq_board.nextval, ?, ?)";
		
		String sqlInsert = "insert into  board_r (bno, title, content, writer, bref, bre_level, bre_step)"
				+ " VALUES (seq_board.nextval, ?, ?, ?, ?, ?, ?)";
		int bref = 0;
		int bre_level = 0;
		int bre_step = 1;
		try {
			//bref, bre_level, bre_step 추가
			if(vo.getBno() != 0) {
				bref = vo.getBref();
				bre_step = vo.getBreStep();
				ps = conn.prepareStatement(sqlUpdate);
				ps.setInt(1, bref);
				ps.setInt(2, bre_step);
				result = ps.executeUpdate();
				JDBCTemplate.close(ps);
				
				bre_level = vo.getBreLevel() + 1;
				bre_step++;
				ps = conn.prepareStatement(sqlInsert);
				ps.setInt(4, bref);
				ps.setInt(5,  bre_level);
				ps.setInt(6, bre_step);
			}else {
				ps = conn.prepareStatement(sqlInsertNew);
				ps.setInt(4,  bre_level);
				ps.setInt(5, bre_step);
			}
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setString(3, vo.getWriter());
			result = ps.executeUpdate();
		} catch (Exception e) {
			//-1
			System.out.println("연결 실패");
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//read
	public ArrayList<Board> selectBoard(Connection conn, int start , int end) {
		ArrayList<Board> volist = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select t2.*"
				+ " from (select ROWNUM r, t.* from (select b.*  from board_r b where b.delete_yn <> 'Y' order by bref desc, bre_step asc) t) t2"
				+ " where r between ? and ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			volist = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreate_date(rs.getDate("create_date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDelete_yn(rs.getString("delete_yn").charAt(0));
				vo.setBref(rs.getInt("bref"));
				vo.setBreLevel(rs.getInt("bre_level"));
				vo.setBreStep(rs.getInt("bre_step"));
				volist.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return volist;
	}
	public Board getBoardDetail(Connection conn, int bno) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Board vo = new Board();
		String query = "select title, content, create_date, writer from board_r where bno like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreate_date(rs.getDate("create_date"));
				vo.setWriter(rs.getString("writer"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return vo;
	}
	//update
		public int updateBoard(Connection conn, Board vo, String writer) {
			int result = -1;
			String query = "update board_r set title = ?, content = ? where bno like ? and writer like ? and delete_yn <> 'Y'";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getContent());
				ps.setInt(3, vo.getBno());
				ps.setString(4, writer);
				result = ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			return result;
		}
		//delete
		public int deleteBoard(Connection conn, int bno, String writer) {
			int result = -1;
			//String query = "delete from board where bno like ? and member_id like ?";
			String query = "update board_r set delete_Yn = 'Y' where bno like ? and writer like ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(query);
				ps.setInt(1,  bno);
				ps.setString(2,  writer);
				result = ps.executeUpdate();
			} catch (Exception e) {
				System.out.println("연결 실패");
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(ps);
			}
			return result;
		}
}