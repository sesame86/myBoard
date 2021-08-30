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

/**
 * Servlet implementation class BoardReWriteServlet
 */
@WebServlet("/boardrewrite")
public class BoardReWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//답글이므로 어느글에 답글을 달 것인가 정보가 전달되어 올것
		Board originalVo = null;
		String viewBno = request.getParameter("bno");
		if(viewBno == null) {  //기존 읽고 있던 글이 없다면 원본 새글쓰기로 인식
			originalVo = new Board();
		}else {
			int bno = Integer.parseInt(viewBno);
			originalVo = new BoardService().getBoard(bno);
		}
		
		//화면에 전달되어 옴
		//http://localhost:8090/myBoard/boardwrite?t=title&c=content
		String title = request.getParameter("t");
		String content = request.getParameter("c");
		String writer = (String)request.getSession().getAttribute("memberLoginInfo");
		if(writer == null) {
			writer = "user01";
		}
		
		Board vo = new Board(originalVo.getBno(), title, content, writer, originalVo.getBref(), originalVo.getBreLevel(), originalVo.getBreStep());
		int result = new BoardService().insertBoard(vo);
		//오류 발생-1, 가입성공 1, 가입실패 0, 기존회원있으면 2, 가장큰수 0xFF
		if(result ==1) {
			out.println("게시글이 추가되었습니다.");
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
