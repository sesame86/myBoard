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
		<c:if test="${accessMsg == '비밀번호가 틀렸습니다.'}">
				<script type="text/javascript">alert("${accessMsg}")</script>
		</c:if>
		<div class="text-center" style="width: 350px; margin: 300px auto">
			<div class="form-signin">
				<form method="post" action="updatecheck">
					<input type="hidden" name="id"  value="<%=id %>" readonly >
					<h3 class="mb-3">비밀번호를 입력해주세요.</h3>
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