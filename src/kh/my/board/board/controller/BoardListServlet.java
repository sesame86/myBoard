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

/**
 * Servlet implementation class BoardList
 */
@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
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
		
		final int PAGE_SIZE = 10;  //한페이지당 글 수 
		final int PAGE_BLOCK = 3;  //한화면에 나타날 페이지 링크 수
		int bCount = 0;  //총 글수
		int pageCount = 0;  //총페이지 수 
		int startPage = 1;  //화면에 나타날 시작페이지
		int endPage = 1;  //화면에 나타날 마지막페이지
		int currentPage =1;  //눌려진 페이지
		int startRnum = 1; //화면에 나타날 글 번호
		int endRnum = 1; //화면에 나타날 글 번호
		
		String pageNum = request.getParameter("pagenum");
		if(pageNum != null) {
			currentPage = Integer.parseInt(pageNum);  //눌려진 페이지
		}
		String writer = request.getParameter("writer");
		String allOnly = request.getParameter("allOnly");
		//처음 페이지에 들어오면 allOnly값이 null이기 때문에 전체 리스트를 출력하기 위해 all으로 설정해준다.
		if(allOnly == null) {
			allOnly = "all";
		}
		//총 글수
		//allOnly의 값이 all이면 전체글수 값이 only면 개인 작성 글 수
		bCount = new BoardService().getBoardCount(writer, allOnly);
		
		//총 페이지수 = (총글수/페이지당 글수) + (총글개수에서 페이지당글수로 나눈 나머지가 0이 아니라면 페이지개수를 12증가)
		pageCount = (bCount/PAGE_SIZE) + (bCount%PAGE_SIZE == 0 ? 0 : 1);

		startRnum = (currentPage - 1) * PAGE_SIZE + 1;
		endRnum = startRnum + PAGE_SIZE -1;
		if(endRnum > bCount) {
			endRnum = bCount;
		}
		
		if(currentPage%PAGE_BLOCK == 0) {
			startPage = (currentPage/PAGE_BLOCK - 1) *PAGE_BLOCK + 1;
		}else {
			startPage = (currentPage/PAGE_BLOCK) *PAGE_BLOCK + 1;
		}
		
		endPage = startPage + PAGE_BLOCK - 1;
		if(endPage > pageCount) {
			endPage = pageCount;
		}
		
		//writer의 값이 null이면 전체리스트 반환 값이 있으면 개인 작성 리스트 반환
		ArrayList<Board> volist = new BoardService().selectBoard(startRnum, endRnum, writer);
		ArrayList<Board> commentList = new BoardService().personalCommentList(startRnum, endRnum, writer);

		//Data 전달을 위해서 request에 셋
		request.setAttribute("boardvolist", volist);
		request.setAttribute("commentList", commentList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCount", pageCount);
		
		if(allOnly.equals("all")) {
			request.setAttribute("allOnly", "all");
		}else if(allOnly.equals("only")){
			request.setAttribute("allOnly", "only");
		}else if(allOnly.equals("commentOnly")) {
			request.setAttribute("allOnly", "commentOnly");
		}
		//page 이동하면서 Data도 전달
		request.getRequestDispatcher("/boardlist.jsp").forward(request, response);
		
//		//member 리스트를 화면에 출력
//		//if(voList.size() > 0) {
//			for(Board vo: voList) {
//				out.println("<h4>" + vo.toString()+"</h4>");
//			}
//		//}
//		if(startPage > 1) {
//			out.println("이전 ");
//		}
//		for(int i = startPage; i <= endPage; i++) {
//			out.println(i);
//			if(i != endPage) {
//				out.println(", ");
//			}
//		}
//		if(endPage < pageCount) {
//			out.println(" 다음");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
