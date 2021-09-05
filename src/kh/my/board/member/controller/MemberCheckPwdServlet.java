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
 * Servlet implementation class MemberCheckPwdServlet
 */
@WebServlet("/updatecheck")
public class MemberCheckPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberCheckPwdServlet() {
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
		
		String checkId = request.getParameter("id");
		String checkPwd = request.getParameter("pwd");
		String func = request.getParameter("func");
		
		Member vo = new MemberService().checkPwd(checkId, checkPwd);
		
		if(func.equals("update")) {
			if(vo != null) {
				//member정보 jsp에 전달해서 화면에 출력
				request.setAttribute("membervo", vo);
				request.getRequestDispatcher("memberupdate.jsp").forward(request, response);
			}else {
				request.setAttribute("msg", "비밀번호가 틀렸습니다.");
				request.getRequestDispatcher("checkpwd.jsp").forward(request, response);
			}
		}else if(func.equals("delete")) {
			if(vo != null) {
				request.getRequestDispatcher("memberdelete").forward(request, response);
			}else {
				request.setAttribute("msg", "비밀번호가 틀렸습니다.");
				request.getRequestDispatcher("checkpwd.jsp").forward(request, response);
			}
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
