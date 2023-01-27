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
		
		<h3 class="my-4">Registration Detail</h3>
		
		<div class="row">
			
			<div class="col">
			
				<div class="card">
					
					<div class="card-header">Student Information</div>
					
					<div class="card-body">
						
						<div class="mb-3">
							<label class="form-label">Name</label>
							<span class="form-control">${ dto.studentInfo.name }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Phone</label>
							<span class="form-control">${ dto.studentInfo.phone }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Email</label>
							<span class="form-control">${ dto.studentInfo.email }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Registration Date</label>
							<span class="form-control">${ dto.registrationDate }</span>
						</div>
					
					</div>
				
				</div>
			
			</div>
			
			
			<div class="col">
			
				<div class="card">
					
					<div class="card-header">Class Information</div>
					
					<div class="card-body">
						
						<div class="mb-3">
							<label class="form-label">Teacher</label>
							<span class="form-control">${ dto.classInfo.teacherName }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Start Date</label>
							<span class="form-control">${ dto.classInfo.startDate }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Duration</label>
							<span class="form-control">${ dto.classInfo.months }</span>
						</div>
						
						<div class="mb-3">
							<label class="form-label">Description</label>
							<span class="form-control">${ dto.classInfo.description }</span>
						</div>
					
					</div>
				
				</div>
			
			</div>
		</div>
		
		<div class="mt-4">
			
			<c:url var="edit" value="/classes/registration">
				<c:param name="classId" value="${ dto.classInfo.id }"></c:param>
				<c:param name="studentId" value="${ dto.studentInfo.id }"></c:param>
				<c:param name="teacherName" value="${ dto.classInfo.teacherName }"></c:param>
				<c:param name="startDate" value="${ dto.classInfo.startDate }"></c:param>
			</c:url>
			<a href="${ edit }" class="btn btn-outline-danger me-2">
				<i class="bi bi-pencil"></i> Edit Registration
			</a>
			
			<c:url var="classDetail" value="/classes/${ dto.classInfo.id }"></c:url>
				<a href="${ classDetail }" class="btn btn-outline-primary">
				<i class="bi bi-mortarboard"></i> Class Detail
			</a>
		</div>
	</div>
	
	

</body>
</html>