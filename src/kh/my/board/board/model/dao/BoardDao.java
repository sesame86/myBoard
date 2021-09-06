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
		String query = "select bno, bref, bre_level, bre_step,title, content, create_Date, writer, delete_Yn"
				+ " from board_r where bno like ?";
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
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_Date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_Yn").charAt(0));
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
	public int getBoardCount(Connection conn, String writer, String comment) {
		int result = 0;
		String allCount = "select count(bno) from board_r where bre_level like 0";
		String onlyCount = "select count(bno) from board_r where writer like ? and bre_level like 0";
		String commetCount = "select count(bno) from board_r where writer like ? and bre_level <> 0";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			if(writer == null) {
				ps = conn.prepareStatement(allCount);
				rs = ps.executeQuery();
			}else {
				if(comment == null) {
					ps = conn.prepareStatement(onlyCount);
					ps.setString(1, writer);
					rs = ps.executeQuery();
				}else {
					ps = conn.prepareStatement(commetCount);
					ps.setString(1, writer);
					rs = ps.executeQuery();
				}
			}
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
		ResultSet rs = null;
		// 답글, 답답....
		String sqlUpdate = "UPDATE board_r set bre_step=bre_step+1  where bref=? and bre_step>?";
		
		String sqlInsert = "INSERT INTO" + " board_r" + " (BNO, TITLE, CONTENT, WRITER, bref, bre_level, bre_step)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		String sqlSeqNextVal = "select SEQ_BOARD.nextval from dual";
		
		int bref = 0;
		int bre_level = 0;
		int bre_step = 1;
		int nextVal = 0;
		try {
			ps = conn.prepareStatement(sqlSeqNextVal);
			rs = ps.executeQuery();
			if (rs.next()) {
				nextVal = rs.getInt(1);
			}
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
			
			if (vo.getBno() != 0) { // 답,,,,,글 쓰기
				bref = vo.getBref();
				bre_step = vo.getBreStep();
				ps = conn.prepareStatement(sqlUpdate); // UPDATE
				ps.setInt(1, bref);
				ps.setInt(2, bre_step);
				result = ps.executeUpdate();
				JDBCTemplate.close(ps);

				bre_level = vo.getBreLevel() + 1;
				bre_step++; 
			}
			
			ps = conn.prepareStatement(sqlInsert); // Insert
			if (vo.getBno() != 0) {// 답,,,,,글 쓰기
				ps.setInt(5, bref);
			} else {// 새글 쓰기
				ps.setInt(5, nextVal);
			}
			ps.setInt(6, bre_level);
			ps.setInt(7, bre_step);
			ps.setInt(1, nextVal);
			ps.setString(2, vo.getTitle());
			ps.setString(3, vo.getContent());
			System.out.println(vo.getWriter());
			ps.setString(4, vo.getWriter());
			result = ps.executeUpdate();
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
	//read
	public ArrayList<Board> selectBoard(Connection conn, int start , int end, String writer) {
		ArrayList<Board> volist = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String selectAllQuery = "select t2.*"
				+ " from (select ROWNUM r, t1.* from (select *  from board_r where delete_yn <> 'Y' and bre_level like 0 order by bref desc, bre_step asc) t1) t2"
				+ " where r between ? and ?";
		String sqlSelectOnly = "select t2.*"
				+ " from (select ROWNUM r, t1.* from (select *  from board_r where bre_level like 0 and writer like ? order by bref desc, bre_step asc) t1) t2"
				+ " where r between ? and ?";
		try {
			if(writer == null) {
				ps = conn.prepareStatement(selectAllQuery);
				ps.setInt(1, start);
				ps.setInt(2, end);
				rs = ps.executeQuery();
			} else {
				ps = conn.prepareStatement(sqlSelectOnly);
				ps.setString(1, writer);
				ps.setInt(2, start);
				ps.setInt(3, end);
				rs = ps.executeQuery();
			}
			volist = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_yn").charAt(0));
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
				vo.setCreateDate(rs.getDate("create_date"));
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
	//read comment
	public ArrayList<Board> commentList(Connection conn, int bno) {
		ArrayList<Board> volist = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select t2.*"
				+ " from (select ROWNUM r, t.* from (select b.*  from board_r b where b.delete_yn <> 'Y' order by bref desc, bre_step asc) t) t2"
				+ " where bref like ? and bre_level <> 0";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, bno);
			rs = ps.executeQuery();
			
			volist = new ArrayList<Board>();
			while(rs.next()) {
				Board vo1 = new Board();
				vo1.setBno(rs.getInt("bno"));
				vo1.setTitle(rs.getString("title"));
				vo1.setContent(rs.getString("content"));
				vo1.setCreateDate(rs.getDate("create_date"));
				vo1.setWriter(rs.getString("writer"));
				volist.add(vo1);
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
	//read personal comment
	public ArrayList<Board> personalCommentList(Connection conn, int start , int end, String writer) {
		ArrayList<Board> volist = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCommentOnly = "select t2.*"
				+ " from (select ROWNUM r, t1.*"
				+ " from (select *  from board_r where bre_level <> 0 and writer like ? order by bref desc, bre_step asc) t1) t2"
				+ " where r between ? and ?";
		try {

			ps = conn.prepareStatement(sqlCommentOnly);
			ps.setString(1, writer);
			ps.setInt(2, start);
			ps.setInt(3, end);
			rs = ps.executeQuery();
			
			volist = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreateDate(rs.getDate("create_date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDeleteYn(rs.getString("delete_yn").charAt(0));
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