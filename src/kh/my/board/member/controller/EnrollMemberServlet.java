package kh.my.board.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.member.model.service.MemberService;
import kh.my.board.member.model.vo.Member;


/**
 * Servlet implementation class EnrollMemberServlet
 */
@WebServlet("/join")  // 회원가입
public class EnrollMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnrollMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		char genderch = ' ';
		if(gender != null) {
			genderch = gender.charAt(0);
		}
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String age = request.getParameter("age");
		int ageInt = 0;
		if(age != null) {
			ageInt = Integer.parseInt(age);
		}
		
		Member vo = new Member(id, pwd, name, genderch, email, phone, address, ageInt);
		int result = new MemberService().insertMember(vo);
		//오류 발생-1, 가입성공 1, 가입실패 0, 기존회원있으면 2, 가장큰수 0xFF
		if(result ==1) {
			request.setAttribute("newMemberMsg", "회원가입 성공");
			//page 이동하면서 Data도 전달
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else if(result == 2) {
			request.setAttribute("duplicationMsg", "기존회원 id 존재");
			//page 이동하면서 Data도 전달
			request.getRequestDispatcher("memberenroll.jsp").forward(request, response);
		}else {
			out.println("예기치 못한 오류 발생");
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
