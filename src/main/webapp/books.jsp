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
<title>Adding New Book:</title>
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
    <h1>Available Books in Library</h1>
    
    <br/><br/>
    <div>
      <table class="table">
  <thead>
        <tr>
          <th>Book Name</th>
        </tr>
        </thead>
  <tbody>
        <c:forEach  items="${books}" var ="book">
        <tr>
          <td>${book.getName()}</td>
          <td><a href="/Library/books/${book.getId()}"><button type="button" class="btn btn-info">View Details</button></a></td>
        </tr>
        </c:forEach>
       </tbody>
</table>
    </div>
    
		</div>
		
		<div>
    <a href="/Library/addBook.jsp"><button type="button" class="btn btn-success">Add</button></a>

    </div>

	</div>
</body>
</html>