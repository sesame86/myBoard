package kh.my.board.board.model.service;

import static kh.my.board.comm.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import kh.my.board.board.model.dao.BoardDao;
import kh.my.board.board.model.vo.Board;
import kh.my.board.comm.JDBCTemplate;

public class BoardService {

	public BoardService() {}
	public Board getBoard(int bno) {
		Board vo = null;
		Connection conn = JDBCTemplate.getConnection();
		vo = new BoardDao().getBoard(conn, bno);
		JDBCTemplate.close(conn);
		return vo;
	}
	//총 글수
	public int getBoardCount() {
		//0이나 -1이나 똑같음~ 읽은게 없다는뜻은 똑같다
		int result = 0;
		Connection conn = JDBCTemplate.getConnection();
		
		result = new BoardDao().getBoardCount(conn);
		
		return result;
	}
	//create
	public int insertBoard(Board vo) {
		int result =-1;
		Connection conn = JDBCTemplate.getConnection();
		
		result = new BoardDao().insertBoard(conn, vo);
			
		JDBCTemplate.close(conn);
		return result;	
	}
	//read
	public ArrayList<Board> selectBoard(int start , int end) {
		ArrayList<Board> volist = null;
		Connection conn = getConnection();
		
		volist = new BoardDao().selectBoard(conn, start, end);
		JDBCTemplate.close(conn);
		return volist;
	}
	public Board getBoardDetail(int bno) {
		Connection conn = getConnection();
		Board vo = new Board();
		vo = new BoardDao().getBoardDetail(conn, bno);
		JDBCTemplate.close(conn);
		return vo;
	}
	//read comment
		public ArrayList<Board> commentList(int bno) {
			ArrayList<Board> volist = null;
			Connection conn = getConnection();
			
			volist = new BoardDao().commentList(conn, bno);
			JDBCTemplate.close(conn);
			return volist;
		}
	//update
	public int updateBoard(Board vo, String writer) {
		int result = -1;
		Connection conn = getConnection();
		result = new BoardDao().updateBoard(conn, vo, writer);
		JDBCTemplate.close(conn);
		return result;
	}
	//delete
	public int deleteBoard(int bno, String writer) {
		int result = -1;
		Connection conn = getConnection();
		
		result = new BoardDao().deleteBoard(conn, bno, writer);

		JDBCTemplate.close(conn);
		return result;
	}
}