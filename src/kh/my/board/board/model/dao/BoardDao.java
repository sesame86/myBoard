package kh.my.board.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.my.board.board.model.vo.Board;
import kh.my.board.comm.JDBCTemplate;

public class BoardDao {
	public BoardDao() {}
	//총 글수
	public int getBoardCount(Connection conn) {
		int result = 0;
		String query = "select count(bno) from board";
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
		String query = "insert into board(bno, title, content, writer) values (seq_board.NEXTVAL, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(query);
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
		ArrayList<Board> voList = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select t2.*"
				+ " from (select ROWNUM r, t.* from (select b.*  from board b where b.delete_yn <> 'Y' order by create_date desc, bno desc) t) t2"
				+ " where r between ? and ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, start);
			ps.setInt(2, end);
			rs = ps.executeQuery();
			
			voList = new ArrayList<Board>();
			while(rs.next()) {
				Board vo = new Board();
				vo.setBno(rs.getInt("bno"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setCreate_date(rs.getDate("create_date"));
				vo.setWriter(rs.getString("writer"));
				vo.setDelete_yn(rs.getString("delete_yn").charAt(0));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return voList;
	}
	//update
		public int updateBoard(Connection conn, Board vo, String writer) {
			int result = -1;
			String query = "update board set title = ?, content = ? where bno like ? and writer like ? and delete_yn <> 'Y'";
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
			String query = "update board set delete_Yn = 'Y' where bno like ? and writer like ?";
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