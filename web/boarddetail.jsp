<%@page import="kh.my.board.member.model.vo.Member"%>
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
	<script src="https://kit.fontawesome.com/616f27e0c4.js" crossorigin="anonymous"></script>
	<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	<title>board detail</title>
	<%	
    	String msg = (String)request.getAttribute("msg");
	%>
    <script type="text/javascript">
    	function accordion(id) {
    		var x = document.getElementById(id);
    		if (x.className.indexOf("show") == -1) {
    			x.className += "show";
    		} else { 
    			x.className = x.className.replace("show", "");
    		}
    	}
    	<%if(msg != null){%>
    		alert("<%=msg%>");
    	<%}%>
    </script>
</head>
<body>
	<%
	Member memberLoginInfo = (Member)session.getAttribute("memberLoginInfo");
	String name = null, id = null;
	if(memberLoginInfo != null){
		name = memberLoginInfo.getName();
		id = memberLoginInfo.getId();
	}
	String bno = request.getParameter("bno");
	int bnoInt = 0;
	if(bno != null) {
		bnoInt = Integer.parseInt(bno);
	}
	String writer = request.getParameter("writer");
	
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
	         		<a class="username" href="memberpage"><%=name %> 님</a>
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
				<div class="jumbotron bg-white mb-3">
					<p><%=vo.getContent()%></p>
				</div>
				<div class=text-end>
				<%if(id.equals(writer)){%>
					<!-- 작성자와 사용자가 같은사람이면 -->
					<a href="boarddelete?bno=<%=bnoInt %>" class="text-black btn btn-color px-3" role="button">삭제 <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
					  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
					</svg></a>
					<a href="boarddetail?bno=<%=bnoInt %>&update=update" class="text-black btn btn-color px-3" role="button">수정 <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-eraser-fill" viewBox="0 0 16 16">
					  <path d="M8.086 2.207a2 2 0 0 1 2.828 0l3.879 3.879a2 2 0 0 1 0 2.828l-5.5 5.5A2 2 0 0 1 7.879 15H5.12a2 2 0 0 1-1.414-.586l-2.5-2.5a2 2 0 0 1 0-2.828l6.879-6.879zm.66 11.34L3.453 8.254 1.914 9.793a1 1 0 0 0 0 1.414l2.5 2.5a1 1 0 0 0 .707.293H7.88a1 1 0 0 0 .707-.293l.16-.16z"/>
					</svg></a>
				<%} %>
				</div>
			</div>
			<h5 class="mt-4">댓글 남기기</h5>
			<form method="get" action="boardwrite.kh" >
			<input type="hidden" name="bno"  value="<%=bnoInt %>" readonly >
			<input type="hidden" name="comment"  value="comment" readonly >
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
					<%if(comment.getBreLevel() == 1) {%>
					<div class="speech-bubble-yellow"><span class="badge" style="background-color: #4B6587"><%=comment.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
					<p class="comment-meta-font"><%=comment.getCreateDate() %> &nbsp&nbsp
					<a href="#" onclick="accordion('event<%=comment.getBno()%>')"><i class="fas fa-comments text-black"></i></a>
						<div id="event<%=comment.getBno()%>" class="hide">
							<!-- 대댓글 -->
							<h5 class="mt-4">댓글에 댓글남기기</h5>
							<form method="get" action="boardwrite.kh" >
							<input type="hidden" name="bno"  value="<%=comment.getBno()%>" readonly >
							<input type="hidden" name="comment"  value="comment" readonly >
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
						</div>
					</p>
					<%} else{ %>
					<div class="sb-comment-yellow"><span class="badge" style="background-color: #4B6587"><%=comment.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
					<p class="comment-meta-font"><%=comment.getCreateDate() %></p>
					<%} %>
				<%} else { %>
					<%if(comment.getBreLevel() == 1) {%>
					<div class="speech-bubble-white"><span class="badge" style="background-color: #4B6587"><%=comment.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
					<p class="comment-meta-font"><%=comment.getCreateDate() %> &nbsp&nbsp
						<a href="#" onclick="accordion('event<%=comment.getBno()%>')"><i class="fas fa-comments text-black"></i></a>
						<div id="event<%=comment.getBno()%>" class="hide">
							<!-- 대댓글 -->
							<h5 class="mt-4">댓글에 댓글남기기</h5>
							<form method="get" action="boardwrite.kh" >
							<input type="hidden" name="bno"  value="<%=comment.getBno()%>" readonly >
							<input type="hidden" name="comment"  value="comment" readonly >
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
						</div>
					</p>
					<%} else { %>
					<div class="sb-comment-white"><span class="badge" style="background-color: #4B6587"><%=comment.getWriter()%></span> <%=comment.getTitle()%><br><%=comment.getContent()%></div>
					<p class="comment-meta-font"><%=comment.getCreateDate() %></p>
					<%} %>
				<%} %>
				<%} }%>
			</div>
		</div>
	</main>
</body>
</html>