<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<head>
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
  </head>
</head>
<body class="text-center">
	<main class="form-signin">
	  <form method="post" action="memberlogin">
	  	<img class="logo" src="css/logo1.JPG" width="320px">
	    <div class="form-floating">
	      <input name="id" class="form-control" id="floatingInput" placeholder="id">
	      <label for="floatingInput">id</label>
	    </div>
	    <div class="form-floating">
	      <input name="pwd" type="password" class="form-control" id="floatingPassword" placeholder="Password">
	      <label for="floatingPassword">Password</label>
	    </div>
	    <button class="w-100 btn btn-color" type="submit">Sign in</button>
	  </form>
	</main>
</body>
</html>