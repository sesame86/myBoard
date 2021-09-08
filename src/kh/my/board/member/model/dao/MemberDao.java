package kh.my.board.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import kh.my.board.comm.JDBCTemplate;
import kh.my.board.member.model.vo.Member;

public class MemberDao {
	public MemberDao() {}
	//login
	public int login(Connection conn, String id, String pwd) {
		int result = 0;
		String query = "select member_id, member_pwd from member where member_id like ? and member_pwd like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			ps.setString(2, pwd);
			rs = ps.executeQuery();
			if(rs.next()) {
				result = 1;  //로그인 성공하면 1, 못찾으면 0
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	public int checkDuplicatedMember(Connection conn, Member vo) {
		int result = -1;
		String query = "select member_id from member where member_id like ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, vo.getId());
			rs = ps.executeQuery();
			if(rs.next()) {
				result = 2;  //기존회원이 있으면
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//navbar에 띄울 name 가져오기
	public Member getName(Connection conn, String id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Member vo = null;
		String query = "select member_name from member where member_id like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			vo = new Member();
			if(rs.next()) {
				vo.setName(rs.getString("member_name"));
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
	//create
	public int insertMember(Connection conn, Member vo) {
		int result = -1;
		String query = "insert into member(member_id, member_pwd, member_name, gender, email, phone, address, age, enroll_date) values (?, ?, ?, ?, ?, ?, ?, ?, sysdate)";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPwd());
			ps.setString(3, vo.getName());
			ps.setString(4, String.valueOf(vo.getGender()));
			ps.setString(5, vo.getEmail());
			ps.setString(6, vo.getPhone());
			ps.setString(7, vo.getAddress());
			ps.setInt(8, vo.getAge());
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
	public ArrayList<Member> selectMember(Connection conn) {
		ArrayList<Member> voList = null;
		Statement st = null;
		ResultSet rs = null;
		String query = "SELECT * FROM member";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			voList = new ArrayList<Member>();
			while(rs.next()) {
				Member vo = new Member();
				vo.setId(rs.getString("member_id"));
				vo.setPwd(rs.getString("member_pwd"));
				vo.setName(rs.getString("member_name"));
				vo.setGender(rs.getString("gender").charAt(0));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setAge(rs.getInt("age"));
				vo.setEnroll_date(rs.getDate("enroll_date"));
				vo.setPoint(rs.getInt("point"));
				voList.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(st);
		}
		return voList;
	}
	//update
	public Member checkPwd(Connection conn, String checkId, String checkPwd) {
		PreparedStatement ps =null;
		ResultSet rs = null;
		Member vo = null;
		String query = "SELECT * FROM member WHERE member_id like ? and member_pwd like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, checkId);
			ps.setString(2, checkPwd);
			rs = ps.executeQuery();
			while(rs.next()) {
				vo = new Member();
				vo.setId(rs.getString("member_id"));
				vo.setPwd(rs.getString("member_pwd"));
				vo.setName(rs.getString("member_name"));
				vo.setGender(rs.getString("gender").charAt(0));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setAge(rs.getInt("age"));
				vo.setEnroll_date(rs.getDate("enroll_date"));
				vo.setPoint(rs.getInt("point"));
			}
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);
		}
		return vo;
	}
	public int updateMember(Connection conn, Member vo) {
		int result = -1;
		PreparedStatement ps = null;
		String query = "update member set member_pwd = ?, member_name = ?, gender = ?, email = ?, phone = ?, address = ?, age = ?, enroll_date = ?  where member_id like ?";
		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, vo.getPwd());
			ps.setString(2, vo.getName());
			ps.setString(3, String.valueOf(vo.getGender()));
			ps.setString(4, vo.getEmail());
			ps.setString(5, vo.getPhone());
			ps.setString(6, vo.getAddress());
			ps.setInt(7, vo.getAge());
			ps.setDate(8, vo.getEnroll_date());
			ps.setString(9, vo.getId());
			result = 0;
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
	//delete
	public int deleteMember(Connection conn, String id) {
		int result = -1;
		try {
			String query = "delete from member where member_id like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1,  id);
			result = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println("연결 실패");
			e.printStackTrace();
		}
		return result;
	}
	public int updatePointMember(Connection conn, String id, int point) {
		int result = -1;
		String query = "update member set point = point + ? where member_id like ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, point);
			ps.setString(2, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(ps);
		}
		return result;
	}
}
