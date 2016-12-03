<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ADD DOCTOR</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>
<c:if test="${(user.personName != null) && (user.type == 'Controller')}">
<div class="jumbotron"><h2 class="text-center">WELCOME TO DRUGS - ONLINE PORTAL</h2><h2 class="text-center">ADD DOCTOR</h2></div>
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
		<div class="container-fluid">
		<form:form commandName="doctor" action="addDoctor.htm" method="post">
			<div class="row">
			<div class="col-md-3">Doctor Name :</div>
			<div class="col-md-3"><form:input type="text" path="personName" class="form-control" placeholder ="Doctor Name"/><font color="red"><form:errors path="personName"/></font></div>
			<div class="col-md-3">Doctor Specilization :</div>
			<div class="col-md-3"><form:input type="text" path="specilization" class="form-control" placeholder ="Doctor Specilization"/><font color="red"><form:errors path="specilization"/></font></div>
			</div>
			<div class="row">
			<div class="col-md-3">Doctor Username :</div>
			<div class="col-md-3"><form:input type="text" path="userName" class="form-control" placeholder ="Username"/><font color="red"><form:errors path="userName"/></font></div>
				<div class="col-md-2">
				<c:if test="${error1 == 1}">
				<p class="danger">Username already exists</p>
				</c:if>
				</div>
			</div>	
			<div class="row">
			<div class="col-md-3">Doctor Password :</div>
			<div class="col-md-3"><form:input type="password" path="password" class="form-control" placeholder ="Password"/><font color="red"><form:errors path="password"/></font></div>
			<div class="col-md-3">Doctor Age :</div>
			<div class="col-md-3"><form:input type="number" path="age" class="form-control" /><font color="red"><form:errors path="age"/></font></div>
			</div>
			<div class="row">
			<div class="col-md-3">Doctor SSN :</div>
			<div class="col-md-3"><form:input type="text" path="ssn" class="form-control" placeholder ="SSN"/><font color="red"><form:errors path="ssn"/></font></div>
			</div>
			<div class="col-md-5">
			</div>
			<div class="col-md-2">
			<input type="submit" value="Create" class = "form-control" />
			</div>
			<div class="col-md-5">
			</div>
		</form:form>		
	</div>
	<hr />
	<c:if test="${count == 1}">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2">
				<strong class="text-center">EMPLOYEE ID</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">DOCTOR NAME</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">SPECILIZATION</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">DOCTOR USERNAME</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">DOCTOR PASSWORD</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">DOCTOR AGE</strong>
			</div>
		</div>
		<c:forEach items="${doctors}" var="ph1">
			<div class="row">
			<div class="col-md-2">
				<strong class="text-center">${ph1.personId}</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">${ph1.personName}</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">${ph1.specilization}</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">${ph1.userName}</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">${ph1.password}</strong>
			</div>
			<div class="col-md-2">
				<strong class="text-center">${ph1.age}</strong>
			</div>
		</div>
			
		</c:forEach>
		
	</div>
	</c:if>
	<c:if test="${count == 0}">
		<strong class="text-center">NO DOCTORS CREATED</strong>
	</c:if>
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