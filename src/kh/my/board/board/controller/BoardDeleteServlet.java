package kh.my.board.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.board.model.service.BoardService;
import kh.my.board.member.model.vo.Member;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/boarddelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
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
		
		String bno = request.getParameter("bno");
		int bnoInt = 0;
		if(bno != null) {
			bnoInt = Integer.parseInt(bno);  //눌려진 페이지
		}
		Member memberLoginInfo = (Member)request.getSession().getAttribute("memberLoginInfo");
		String id = null;
		if(memberLoginInfo != null) {
			id = memberLoginInfo.getId();
		}
		int result = new BoardService().deleteBoard(bnoInt, id);
		
		if(result > 0) {
			request.setAttribute("msg", "삭제 성공");
			request.getRequestDispatcher("boardlist").forward(request, response);
		}else {
			request.setAttribute("msg", "삭제 실패");
			request.getRequestDispatcher("boarddetail.jsp").forward(request, response);
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
