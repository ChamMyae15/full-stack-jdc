<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leaves | Home</title>
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
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
	</c:import>
	
	<div class="container">
		
		<h3 class="my-4">${  empty param.id ? 'Add New' : 'Edit' } Registration</h3>
		
		<div class="row">
			<c:url var="save" value="/classes/registration"></c:url>
			<sf:form action="${ save }" method="post" modelAttribute="registForm" cssClass="col-6">
				
				<sf:hidden path="studentId"/>
				<sf:hidden path="classId"/>
				<sf:hidden path="registrationDate"/>
				
				<div class="mb-3">
					<label class="form-label">Start date</label>
					<span class="form-control">${ param.startDate }</span>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Teacher Name</label>
					<span class="form-control">${ param.teacherName }</span>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Student Name</label>
					<sf:input path="studentName" type="text" placeholder="Eg.Ko Ko" cssClass="form-control"/>
					<sf:errors path="studentName" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Email</label>	
					<sf:input path="email" type="email" placeholder="Eg.KoKo@gmail.com" cssClass="form-control"/>
					<sf:errors path="email" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Phone</label>
					<sf:input path="phone" type="tel" placeholder="Eg.09xxxxxxxxx" cssClass="form-control"/>
					<sf:errors path="phone" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Last Education</label>
					<sf:input path="education" type="text" placeholder="Eg.College" cssClass="form-control"/>
				</div>
				
				<div>
					<button type="submit" class="btn btn-outline-danger">
						<i class="bi bi-save"></i> Save
					</button>
				</div>
			
			</sf:form>
			
			
		
		</div>
	
	</div>

</body>
</html>