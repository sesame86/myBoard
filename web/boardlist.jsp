<%@page import="kh.my.board.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/board.css">
<title>board list</title>
</head>
<body>
	<% 
		String id = (String)request.getAttribute("memberLoginInfo");
		ArrayList<Board> volist = (ArrayList<Board>)request.getAttribute("boardvolist");
		int startPage = (int)request.getAttribute("startPage");
		int endPage = (int)request.getAttribute("endPage");
		int pageCount = (int)request.getAttribute("pageCount");
	%>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light nav-color">
      	<div class="container-fluid">
      		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
      			<span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
		    	<a class="navbar-brand" href="boardlist"><img class="logo" src="css/logo2.JPG" height="30px"></a>
		    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
		    		<li class="nav-item">
		    			<a class="nav-link active" href="#"></a>
		        	</li>
		        </ul>
		        <c:if test="${memberLoginInfo == null}">
		        <form class="d-flex" style="margin-right: 10px;">
	              <a class="btn login-btn" role="button" href="login.jsp">LogIn</a>
	         	</form>
	         	</c:if>
	         	<c:if test="${not empty memberLoginInfo}">
	         	<form class="d-flex" style="margin-right: 10px;">
	         		<p class="username"><%=id %> 님</p>
	         	</form>
	         	</c:if>
	            <form class="d-flex">
	            	<a class="btn login-btn" role="button" href="logout">Logout</a>
	         	</form>
	         </div>
	      </div>
      </nav>
	</header>
	<main>
		<div class="wrap">
			<h1 class="text-center web-title">게시판</h1>
			<table class="table table-hover">
				<tr class="th-color">
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
				</tr>
				<%if(volist != null){
				for(Board vo : volist){ %>
				<tr>
					<td><%=vo.getBno()%></td>
					<td><a class="text-decoration-none text-dark" href="boarddetail?bno=<%=vo.getBno()%>"><%=vo.getTitle()%></a></td>
					<td><%=vo.getWriter()%></td>
					<td><%=vo.getCreate_date()%></td>
				</tr>
				<%} }%>
			</table>
			<a class="btn btn-color" href="boardwrite.jsp">글쓰기</a>
			
			<nav aria-label="Page navigation example">
			  <ul class="pagination justify-content-center">
			    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
			    <%for (int i = startPage; i <= endPage; i++) {%>
			    <!-- 왜 memberLoginInfo가 session에 없을까.. -->
				<li class="page-item"><a class="page-link" href="boardlist?pagenum=<%=i%>"><%=i%></a></li>
				<%} %>
			    <li class="page-item"><a class="page-link" href="#">Next</a></li>
			  </ul>
			</nav>
		</div>
	</main>
</body>
</html>