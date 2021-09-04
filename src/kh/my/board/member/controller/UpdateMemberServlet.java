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
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//update할 데이터
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
		
		int result = new MemberService().updateMember(vo);
		
		if(result == 0) {
			request.setAttribute("msg", "변경 성공");
			request.getRequestDispatcher("personalpage.jsp").forward(request, response);
		}else if(result == -1) {
			request.setAttribute("msg", "변경 실패");
			request.getRequestDispatcher("memberupdate.jsp").forward(request, response);
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
