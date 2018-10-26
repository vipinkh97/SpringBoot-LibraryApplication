<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


	<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

	<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
<title>Displaying ${bikes.getName()}:</title>
</head>
<body>
	
	<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/Library/books">Library</a>
    </div>
     <ul class="nav navbar-nav navbar-right">
    <li><a href="/Library/logout">Logout</a></li>
    </ul>
  </div>
</nav>

	<div class="container">

		<div class="starter-template">
    <h1>Displaying ${books.getName()}</h1>
    
    <br/><br/>
    <div>
      <table class="table">
  <thead>
        <tr>
          <th>Book Name</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Author</th>
        </tr>
        </thead>
  <tbody>
        <tr>
          <td>${books.getName()}</td>
          <td>${books.getPrice()}</td>
          <td>${books.getQuantity()}</td>
          <td>${books.getAuthor()}</td>
        </tr>
       </tbody>
</table>
    </div>
    
		</div>
		
		<div>
	<a href="/Library/books/${books.getId()}/update"><button type="button" class="btn btn-warning" class="float-right">Update</button></a>
	<a href="/Library/books/${books.getId()}/delete"><button type="button" class="btn btn-danger" class="float-right">Delete</button></a>



    </div>

	</div>
</body>
</html>