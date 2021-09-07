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
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/boardwrite.kh")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
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
		
		// 답글이므로 어느글에 답글을 달것인가 정보가 전달되어 올 것임.
		Board oVo = null;
		String bno = request.getParameter("bno");
		int bnoInt = 0;
		if(bno == null) {
			oVo = new Board();
		} else {
			bnoInt = Integer.parseInt(bno);
			// 알아와야함. bref, bre_level, bre_step
			oVo = new BoardService().getBoard(bnoInt);
		}
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Member memberLoginInfo = (Member)request.getSession().getAttribute("memberLoginInfo");
		String id = null;
		if(memberLoginInfo != null) {
			id = memberLoginInfo.getId();
		}
		String comment = request.getParameter("comment");
		
		if(id == null) {
			id = "unknown";
		}
		Board vo = new Board(oVo.getBno(), title, content, id, oVo.getBref(), oVo.getBreLevel(), oVo.getBreStep());
		int result = new BoardService().insertBoard(vo);
		if(comment == null) {
			request.setAttribute("msg", "작성 완료");
			request.getRequestDispatcher("boardlist").forward(request, response);
		}else if(comment.equals("comment")){
			request.setAttribute("msg", "작성 완료");
			//request.setAttribute("bno", oVo.getBref());
			//request.setAttribute("writer", id);
			request.getRequestDispatcher("boarddetail?bno="+oVo.getBref()+"&writer="+id).forward(request, response);
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
