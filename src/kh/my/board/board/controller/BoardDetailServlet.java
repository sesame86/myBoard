package kh.my.board.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.my.board.board.model.service.BoardService;
import kh.my.board.board.model.vo.Board;
import kh.my.board.member.model.vo.Member;

/**
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/boarddetail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
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
			bnoInt = Integer.parseInt(bno);
		}
		Board vo  = new BoardService().getBoardDetail(bnoInt);
		ArrayList<Board> volist = new BoardService().commentList(bnoInt);
		
		Member memberLoginInfo = (Member)request.getSession().getAttribute("memberLoginInfo");
		String id = null;
		if(memberLoginInfo != null){
			id = memberLoginInfo.getId();
		}
		if(id !=  null) {
			request.setAttribute("vo", vo);
			request.setAttribute("bno", bnoInt);
			request.setAttribute("volist", volist);
			request.getRequestDispatcher("/boarddetail.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "로그인이 필요합니다.");
			request.setAttribute("allOnly", "all");
			request.getRequestDispatcher("/boardlist").forward(request, response);
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
