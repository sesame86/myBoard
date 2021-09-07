package kh.my.board.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.board.model.service.BoardService;
import kh.my.board.board.model.vo.Board;
import kh.my.board.member.model.vo.Member;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/boardupdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
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
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board vo = new Board(bnoInt, title, content);
		int result = new BoardService().updateBoard(vo, id);
		
		if(result > 0) {
			request.setAttribute("msg", "변경 성공");
			request.setAttribute("bno", bnoInt);
			request.setAttribute("writer", id);
			request.getRequestDispatcher("boarddetail").forward(request, response);
		}else {
			request.setAttribute("msg", "변경 실패");
			request.setAttribute("bno", bnoInt);
			request.setAttribute("writer", id);
			request.getRequestDispatcher("boarddetail").forward(request, response);
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
