<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>enroll</title>
    <!-- Bootstrap core CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">	
	<!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="css/signin.css">
    
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
    	String duplicationMsg = (String)request.getAttribute("duplicationMsg");
	%>
    <script type="text/javascript">
    	<%if(duplicationMsg != null){%>
    		alert("<%=duplicationMsg%>");
    	<%}%>
    </script>
</head>
<body class="form-join text-center">
	<main><img class="logo" src="css/logo1.JPG" width="320px">
		<form class="row g-3" method="post" action="join">
		    <div class="col-md-6 form-floating">
		    	<input name="id" class="form-control" id="floatingInput" placeholder="id" required="required">
		    	<label for="floatingInput">id</label>
		    </div>
		    <div class="form-floating col-md-6">
		    	<input name="pwd" type="password" class="form-control" id="floatingPassword" placeholder="Password" required="required">
		    	<label for="floatingPassword">Password</label>
		    </div>
		    <div class="form-floating col-md-6">
			    <input name="name" class="form-control" id="floatingInput" placeholder="name" required="required">
			    <label for="floatingInput">name</label>
			</div>
			<div class="form-floating col-md-6">
			    <label for="inputState" class="form-label"></label>
			    <select name="gender" id="inputState" class="form-select">
			    	<option value="F">여자</option>
			    	<option value="M">남자</option>
			    </select>
			</div>
			<div class="form-floating col-md-4">
			    <input name="age" class="form-control" id="floatingInput" placeholder="age" required="required">
			    <label for="floatingInput">age</label>
			</div>
			<div class="form-floating col-md-12">
			    <input name="email" class="form-control" id="floatingInput" placeholder="email" required="required">
			    <label for="floatingInput">email</label>
			</div>
			<div class="form-floating col-md-12">
			    <input name="phone" class="form-control" id="floatingInput" placeholder="phone" required="required">
			    <label for="floatingInput">phone</label>
			</div>
			<div class="form-floating col-md-12">
			    <input name="address" class="form-control" id="floatingInput" placeholder="address" required="required">
			    <label for="floatingInput">address</label>
			</div>
			<button class="w-100 btn btn-color" type="submit">Join</button>
		</form>
	</main>
</body>
</html>