package kh.my.board.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.member.model.service.MemberService;
import kh.my.board.member.model.vo.Member;

/**
 * Servlet implementation class UpdateMemberServlet
 */
@WebServlet("/update")  // 회원정보수정
public class UpdateMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO : 회원정보수정 
		// 각자 회원 정보 수정 방식을 정하고 작성
		// passwd 한번더 확인 --> 기존 데이터 읽어오기  -> 수정--> 
		// update Member set pwd ='aa', gender-'d...........   where 
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//update할 데이터
		String id = "kyy806";
		String pwd = "kyy0806";
		String name = "KimYeeun";
		char gender = 'F';
		String email = "sesame0806@gmail.com";
		String phone = "010-0000-0000";
		String address = "대한민국";
		int age = 24;
		
		Member vo = new Member(id, pwd, name, gender, email, phone, address, age);
		
		String checkPwd = "kyy0806";
		int result = new MemberService().updateMember(vo, checkPwd);
		
		if(result == 0) {
			response.getWriter().append("변경 성공");
		}else if(result == 1) {
			response.getWriter().append("변경 실패");
		}else {
			response.getWriter().append("에러");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
