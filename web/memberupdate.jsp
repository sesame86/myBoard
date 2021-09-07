<%@page import="kh.my.board.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>enroll</title>
    <!-- Bootstrap core CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">	
	<!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="css/board.css">
    
    <style>
    .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }
      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
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
	}
	String accessMsg = (String)request.getAttribute("accessMsg");
	Member membervo = (Member)request.getAttribute("membervo");
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
		<div class="text-center" style="width: 350px; margin: 10% auto">
			<form class="row g-3" method="post" action="memberupdate">
			    <div class="col-md-6 form-floating">
			    	<input name="id" class="form-control" id="floatingInput" placeholder="id" required="required" value="<%=membervo.getId()%>">
			    	<label for="floatingInput">id</label>
			    </div>
			    <div class="form-floating col-md-6">
			    	<input name="pwd" type="password" class="form-control" id="floatingPassword" placeholder="Password" required="required" value="<%=membervo.getPwd()%>">
			    	<label for="floatingPassword">Password</label>
			    </div>
			    <div class="form-floating col-md-6">
				    <input name="name" class="form-control" id="floatingInput" placeholder="name" required="required" value="<%=membervo.getName()%>">
				    <label for="floatingInput">name</label>
				</div>
				<div class="form-floating col-md-6">
				    <label for="inputState" class="form-label"></label>
				    <select name="gender" id="inputState" class="form-select" >
				    	<%if(membervo.getGender() == 'F') {%>
				    	<option value="F" selected>여자</option>
				    	<option value="M">남자</option>
				    	<%}else { %>
				    		<option value="F">여자</option>
				    		<option value="M" selected>남자</option>
				    	<%} %>
				    </select>
				</div>
				<div class="form-floating col-md-4">
				    <input name="age" class="form-control" id="floatingInput" placeholder="age" required="required" value="<%=membervo.getAge()%>">
				    <label for="floatingInput">age</label>
				</div>
				<div class="form-floating col-md-12">
				    <input name="email" class="form-control" id="floatingInput" placeholder="email" required="required" value="<%=membervo.getEmail()%>">
				    <label for="floatingInput">email</label>
				</div>
				<div class="form-floating col-md-12">
				    <input name="phone" class="form-control" id="floatingInput" placeholder="phone" required="required" value="<%=membervo.getPhone()%>">
				    <label for="floatingInput">phone</label>
				</div>
				<div class="form-floating col-md-12">
				    <input name="address" class="form-control" id="floatingInput" placeholder="address" required="required" value="<%=membervo.getAddress()%>">
				    <label for="floatingInput">address</label>
				</div>
				<button class="w-100 btn btn-color" type="submit">Update</button>
			</form>
		</div>
	</main>
</body>
</html>