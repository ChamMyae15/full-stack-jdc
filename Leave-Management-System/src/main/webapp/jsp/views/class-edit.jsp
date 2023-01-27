<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leaves | Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
	</c:import>
	
	<div class="container">
		
		<h3 class="my-4">${ not empty param.id ? 'Edit':'Add New' } Classes</h3>
		
		<div class="row">
		
			<div class="col-lg-6 col-md-9 col-sm-12">
			
			<c:url var="save" value="/classes"></c:url>
			
			<sf:form action="${ save }" method="post" modelAttribute="classForm">
			
				<sf:hidden path="id"/>
				
				<div class="mb-3">
					<label for="form-label">Teacher</label>
					<sf:select path="teacher" items="${teachers}" itemLabel="name" itemValue="id" cssClass="form-select">
						<sf:options/>
					</sf:select>
				</div>
				
				<div class="row mb-2">
					<div class="col">
						<label for="form-label">Start Date</label>
						<sf:input path="start" type="date" cssClass="form-control"/>
						<sf:errors path="start" cssClass="text-secondary"></sf:errors>
					</div>
					
					<div class="col">
						<label for="form-label">Months</label>
						<sf:input path="months" type="number" cssClass="form-control"/>
						<sf:errors path="months" cssClass="text-secondary"></sf:errors>
					</div>
				</div>
				
				<div class="mb-3">
					<label for="form-label">Description</label>
					<sf:textarea path="description" cssClass="form-control"/>
					<sf:errors path="description" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div>
					<button type="submit" class="btn btn-outline-danger">Save</button>
				</div>
				
			</sf:form>
			
			</div>
		</div>
	
	</div>

</body>
</html>