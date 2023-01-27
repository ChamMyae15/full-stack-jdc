<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leaves | Sign in</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
<c:url var="commonCss" value="/resources/application.css"></c:url>
<link rel="stylesheet" href="${ commonCss }">
</head>
<body class="vh-100">
	
	<div class="d-flex vh-100 justify-content-center align-items-center">
		
		<div class="card login-form">
			
			<div class="card-header">
				
				<i class="bi bi-door-open"></i> Sign In 
			
			</div>
			
			<div class="card-body">
				
				<c:url var="signin" value="/signin"></c:url>
				<sf:form action="${ signin }" method="post">
				
				<c:if test="${ not empty param.error}">
					<div class="alert alert-warning">Login Error.</div>
				</c:if>
					
					<div class="mb-3">
						<label class="form-label">Email : </label>
						<input type="email" name="username" placeholder="Please enter email address..." class="form-control"/>
					</div>
					
					<div class="mb-3">
						<label class="form-label">Password : </label>
						<input type="password" name="password" placeholder="Please enter password..." class="form-control"/>
					</div>
					
					<div class="mb-3">
						<button type="submit" class="btn btn-outline-success">
							<i class="bi bi-door-open"></i> Sign In
						</button>
					</div>
				
				</sf:form>
			
			</div>
		
		</div>
	
	</div>


</body>
</html>