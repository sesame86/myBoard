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
	String bno = request.getParameter("bno");
	int bnoInt = 0;
	if(bno != null) {
		bnoInt = Integer.parseInt(bno);
	}
	Board vo = (Board)request.getAttribute("vo");
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
	         		<p>${memberLoginInfo} 님</p>
	         	</form>
	         	</c:if>
	            <form class="d-flex">
	            	<a class="btn login-btn" role="button" href="#" onclick="">Logout</a>
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
				<div class="jumbotron bg-white">
					<p><%=vo.getContent()%></p>
				</div>
			</div><br>
			<div class="mb-3">
			  <label for="exampleFormControlTextarea1" class="form-label"><b>comment</b></label>
			  <textarea name="c" class="form-control" id="exampleFormControlTextarea1" rows="2"></textarea>
			</div>
			<div class="speech-bubble-yellow"><%=vo.getTitle()%></div>
			<div class="speech-bubble-white"><%=vo.getTitle()%></div>
		</div>
	</main>
</body>
</html>