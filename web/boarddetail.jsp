<%@page import="java.util.ArrayList"%>
<%@page import="kh.my.board.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/board.css">
<title>board detail</title>
</head>
<body>
	<%
	String name = (String)session.getAttribute("memberLoginInfo");
	String bno = request.getParameter("bno");
	int bnoInt = 0;
	if(bno != null) {
		bnoInt = Integer.parseInt(bno);
	}
	Board vo = (Board)request.getAttribute("vo");
	ArrayList<Board> volist = (ArrayList<Board>)request.getAttribute("volist");
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
	         		<p class="username"><%=name %> 님</p>
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
			<h1 class="text-center web-title">게시글</h1>
			<div>
				<span class="badge mb-2" style="background-color: #4B6587">no. <%=bno%></span>
				<h2><%=vo.getTitle()%></h2>
				<p class="text-end mb-0"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-calendar-event" viewBox="0 0 16 16">
				  <path d="M11 6.5a.5.5 0 0 1 .5-.5h1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-1a.5.5 0 0 1-.5-.5v-1z"/>
				  <path d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5zM1 4v10a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V4H1z"/>
				</svg> <%=vo.getCreateDate()%> &nbsp&nbsp<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
				  <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
				</svg> <%=vo.getWriter()%></p>
				<div class="jumbotron bg-white">
					<p><%=vo.getContent()%></p>
				</div>
			</div>
			<form method="get" action="boardwrite.kh" >
			<input type="hidden" name="bno"  value="<%=bnoInt %>" readonly >
				<div class="input-group mb-3 mt-3">
					<span class="input-group-text" id="basic-addon1">Title</span>
					<input type="text" name="title" class="form-control" id="exampleFormControlInput1" required="required">
				</div>
				<div class="input-group mb-3">
				  <span class="input-group-text" id="basic-addon1">Content</span>
				  <textarea name="content" class="form-control" id="exampleFormControlTextarea1" rows="1" required="required"></textarea>
				</div>
				<input class="btn btn-color px-5 mb-3" type="submit" value=" 등록 ">
			</form>
			<div style="width: 50%">
				<%if(volist != null){
				for(Board comment : volist){ %>
				<%if(comment.getBno() % 2 == 0){ %>
				<div class="speech-bubble-yellow"><span class="badge" style="background-color: #4B6587"><%=vo.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
				<p class="comment-meta-font"><%=vo.getCreateDate() %></p>
				<%} else { %>
				<div class="speech-bubble-white"><span class="badge" style="background-color: #4B6587"><%=vo.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
				<p class="comment-meta-font"><%=vo.getCreateDate() %></p>
				<%} }}%>
			</div>
		</div>
	</main>
</body>
</html>