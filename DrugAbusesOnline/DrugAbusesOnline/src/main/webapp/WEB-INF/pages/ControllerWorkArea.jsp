<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Drugs - Online Portal</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
<style>
.danger {
	color: grey;
}
</style>
</head>

<body>

<c:if test="${(user.personName != null) && (user.type == 'Controller')}">
<div class="jumbotron"><h2 class="text-center">WELCOME TO DRUGS - ONLINE PORTAL</h2></div>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<!-- Data Toogle Navbar -->
	<div class="container-fluid navigation-header">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
					</button>
					<a class="navbar-brand" href="homepage.htm">HOME</a>
				</div>
    
				<div class="collapse navbar-collapse" id="myNavbar">      						
					<ul class="nav navbar-nav navbar-right">
						<li id = "logOut"><a href="#"><span class="glyphicon glyphicon-user"></span>${user.userName}</a></li>				
						<li id = "logOut"><a href="javascript:formSubmit()"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
						
					</ul>

    			</div>
  			</div>
		</nav>				
	</div>
	<div class="container">
	<div class="row">
	<div class="col-md-4">
		<a href="addPharmacy.htm"><button type="button" class="btn btn-primary">ADD PHARMACY</button></a>
	</div>
	<div class="col-md-4">	
		<a href="addDoctor.htm"><button type="button" class="btn btn-primary">ADD DOCTOR</button></a>
	</div>
	<div class="col-md-4">	
		<a href="addMedicine.htm"><button type="button" class="btn btn-primary">ADD MEDICINE</button></a>
	</div>	
	</div>
	</div>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
	</c:if>
	<c:if test="${user.personName == null}">
		<p>PLEASE LOGIN TO CONTINUE</p>
		<a href="login.htm">CLICK HERE TO LOGIN</a>
	</c:if>	
</body>
</html>