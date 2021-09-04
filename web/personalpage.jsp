<%@page import="kh.my.board.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/board.css">
<title>mypage</title>
	<%	
    	String msg = (String)request.getAttribute("msg");
	%>
    <script type="text/javascript">
    	<%if(msg != null){%>
    		alert("<%=msg%>");
    	<%}%>
    </script>
</head>
<body>
	<%Member memberLoginInfo = (Member)session.getAttribute("memberLoginInfo");
	String name = null;
	if(memberLoginInfo != null){
		name = memberLoginInfo.getName();
	} %>
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
	<main class="wrap">
		<div class="text-center">
			<img class="logo mb-5" src="css/logo1.JPG" width="320px">
			<h2><%=name%>님, 환영합니다</h2>
			<p class="mb-5">회원정보 관리와 글 관리를 할 수 있습니다.</p>
		</div>
	
		<div class="row">
			<div class="col-sm-6 mb-5">
				<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-8">
				      <div class="card-body m-3">
				        <h3 class="card-title py-3 my-3">회원정보 수정</h3>
				        <a href="checkpwd.jsp" class="btn btn-color px-4">Go</a>
				      </div>
				    </div>
				    <div class="col-md-4 m-auto">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
						  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
						</svg>
				    </div>
				  </div>
				</div>
			</div>
			<div class="col-sm-6 mb-5">
				<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-8">
				      <div class="card-body m-3">
				        <h3 class="card-title py-3 my-3">회원 탈퇴</h3>
				        <a href="#" class="btn btn-color px-4">Go</a>
				      </div>
				    </div>
				    <div class="col-md-4 m-auto">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-person-x-fill" viewBox="0 0 16 16">
						  <path fill-rule="evenodd" d="M1 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm6.146-2.854a.5.5 0 0 1 .708 0L14 6.293l1.146-1.147a.5.5 0 0 1 .708.708L14.707 7l1.147 1.146a.5.5 0 0 1-.708.708L14 7.707l-1.146 1.147a.5.5 0 0 1-.708-.708L13.293 7l-1.147-1.146a.5.5 0 0 1 0-.708z"/>
						</svg>
				    </div>
				  </div>
				</div>
			</div>
			<div class="col-sm-6 mb-5">
				<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-8">
				      <div class="card-body m-3">
				        <h3 class="card-title py-3 my-3">작성 글 관리</h3>
				        <a href="#" class="btn btn-color px-4">Go</a>
				      </div>
				    </div>
				    <div class="col-md-4 m-auto">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-journal-text" viewBox="0 0 16 16">
						  <path d="M5 10.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
						  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
						  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
						</svg>
				    </div>
				  </div>
				</div>
			</div>
			<div class="col-sm-6 mb-5">
				<div class="card mb-3" style="max-width: 540px;">
				  <div class="row g-0">
				    <div class="col-md-8">
				      <div class="card-body m-3">
				        <h3 class="card-title py-3 my-3">작성 댓글 관리</h3>
				        <a href="#" class="btn btn-color px-4">Go</a>
				      </div>
				    </div>
				    <div class="col-md-4 m-auto">
				    	<svg xmlns="http://www.w3.org/2000/svg" width="70px" height="70px" fill="currentColor" class="bi bi-chat-text" viewBox="0 0 16 16">
						  <path d="M2.678 11.894a1 1 0 0 1 .287.801 10.97 10.97 0 0 1-.398 2c1.395-.323 2.247-.697 2.634-.893a1 1 0 0 1 .71-.074A8.06 8.06 0 0 0 8 14c3.996 0 7-2.807 7-6 0-3.192-3.004-6-7-6S1 4.808 1 8c0 1.468.617 2.83 1.678 3.894zm-.493 3.905a21.682 21.682 0 0 1-.713.129c-.2.032-.352-.176-.273-.362a9.68 9.68 0 0 0 .244-.637l.003-.01c.248-.72.45-1.548.524-2.319C.743 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.52.263-1.639.742-3.468 1.105z"/>
						  <path d="M4 5.5a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7a.5.5 0 0 1-.5-.5zM4 8a.5.5 0 0 1 .5-.5h7a.5.5 0 0 1 0 1h-7A.5.5 0 0 1 4 8zm0 2.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5z"/>
						</svg>
				    </div>
				  </div>
				</div>
			</div>
		</div> 
	</main>
</body>
</html>