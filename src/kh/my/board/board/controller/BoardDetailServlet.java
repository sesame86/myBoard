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
		
		String update = request.getParameter("update");
		String bno = request.getParameter("bno");
		int bnoInt = 0;
		if(bno != null) {
			bnoInt = Integer.parseInt(bno);
		}
		//게시글 한개 정보
		Board vo  = new BoardService().getBoardDetail(bnoInt);
		//게시글에 달린 댓글 list
		ArrayList<Board> volist = new BoardService().commentList(bnoInt);
		
		Member memberLoginInfo = (Member)request.getSession().getAttribute("memberLoginInfo");
		String id = null;
		if(memberLoginInfo != null){
			id = memberLoginInfo.getId();
		}
		if(id !=  null) {
			if(update == null) {
				request.setAttribute("vo", vo);
				request.setAttribute("bno", bnoInt);
				request.setAttribute("volist", volist);
				request.getRequestDispatcher("/boarddetail.jsp").forward(request, response);
			}else if(update.equals("update")){
				request.setAttribute("vo", vo);
				request.setAttribute("bno", bnoInt);
				request.getRequestDispatcher("/boardupdate.jsp").forward(request, response);
			}
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
