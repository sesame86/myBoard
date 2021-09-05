<%@page import="kh.my.board.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/board.css">
<title>check</title>
</head>
<body>
	<%Member memberLoginInfo = (Member)session.getAttribute("memberLoginInfo");
	String name = null, id = null;
	if(memberLoginInfo != null){
		name = memberLoginInfo.getName();
		id = memberLoginInfo.getId();
	}
	String func = request.getParameter("func");
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
	              <a class="btn login-btn" role="button" href="login">LogIn</a>
	         	</form>
	         	</c:if>
	         	<c:if test="${not empty memberLoginInfo}">
	         	<form class="d-flex" style="margin-right: 10px;">
	         		<a class="username" href="personalpage.jsp"><%=name %> 님</a>
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
		<c:if test="${msg == '비밀번호가 틀렸습니다.'}">
				<script type="text/javascript">alert("${msg}")</script>
		</c:if>
		<div class="text-center" style="width: 350px; margin: 13% auto">
			<div class="form-signin">
				<%if(func.equals("update")){ %>
				<form method="post" action="updatecheck">
					<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-person-circle mb-3" viewBox="0 0 16 16">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
						  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
					</svg>
					<h2 class="mb-3">회원정보 수정</h2>
					<input type="hidden" name="func"  value="update" readonly >
				<%} else if(func.equals("delete")){ %>
				<form method="post" action="updatecheck">
					<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-person-x-fill mb-3" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
					</svg>
					<h2 class="mb-3">회원 탈퇴</h2>
					<input type="hidden" name="func"  value="delete" readonly >
				<%} %>
					<input type="hidden" name="id"  value="<%=id %>" readonly >
					<h5 class="mb-1" style="color: #C8C6C6">비밀번호를 입력해주세요.</h5>
				    <div class="form-floating">
				    	<input name="pwd" type="password" class="form-control mb-3" id="floatingPassword" placeholder="Password">
				        <label for="floatingPassword">Password</label>
				    </div>
				    <button class="w-100 btn btn-color" type="submit">submit</button>
				</form>
			</div>
		</div>
	</main>
</body>
</html>