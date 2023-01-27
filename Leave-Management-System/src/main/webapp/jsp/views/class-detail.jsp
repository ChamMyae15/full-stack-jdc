<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	
		<h3 class="my-4">Class Detail</h3>
		
		<div class="card mb-4">
		
			<div class="card-header">Class Infomation</div>
			
			<div class="card-body row mb-3">
			
				<div class="col">
					<label class="form-label">Teacher</label>
					<span class="form-control">${ dto.classInfo.teacherName }</span>
				</div>
				
				<div class="col">
					<label class="form-label">Start Date</label>
					<span class="form-control">${ dto.classInfo.startDate }</span>
				</div>
				
				<div class="col">
					<label class="form-label">Duration</label>
					<span class="form-control">${ dto.classInfo.months } months</span>
				</div>
				
				<div class="col">
					<label class="form-label">Description</label>
					<span class="form-control">${ dto.classInfo.description }</span>
				</div>
			</div>
		</div>
		
		<div class="d-flex justify-content-between mb-4">
		
			<ul class="nav nav-pills">
			
				<li class="nav-item">
					<button class="nav-link active" data-bs-toggle="pill" data-bs-target="#registrations">
						<i class="bi bi-people-fill"></i>Registrations</button>
				</li>
				
				<li class="nav-item">
					<button class="nav-link" data-bs-toggle="pill" data-bs-target="#leaves">
						<i class="bi bi-person-x"></i>Leave Application</button>
				</li>
			
			</ul>
			
			<div>
				
					<c:url var="editClass" value="/classes/edit">
						<c:param name="id" value="${ dto.classInfo.id }"></c:param>
					</c:url>
					<a href="${ editClass }" class="btn btn-outline-primary">
						<i class="bi bi-pencil"></i>Edit Class</a>
					
					<c:url var="addRegistration" value="/classes/registration">
						<c:param name="classId" value="${ dto.classInfo.id }"></c:param>
						<c:param name="teacherName" value="${ dto.classInfo.teacherName }"></c:param>
						<c:param name="startDate" value="${ dto.classInfo.startDate }"></c:param>
					</c:url>
					<a href="${ addRegistration }" class="btn btn-outline-danger">
						<i class="bi bi-plus-lg"></i>Add New Registration</a>
			</div>
		
		
		
		</div>
			<div class="tab-content" id="contents">
			
				<div class="tab-pane fade show active" id="registrations">
					
					<!-- Registrations info -->
					
					<c:choose>
						<c:when test="${ empty dto.registrations }">
							<div class="alert alert-warning">There is no Data.</div>
						</c:when>
						<c:otherwise>
							<c:import url="/jsp/include/class-registrations.jsp"></c:import>
						</c:otherwise>
					</c:choose>
					
				</div>
				
				<div class="tab-pane fade" id="leaves">
				
					<!-- Leaves info -->
					<c:choose>
						<c:when test="${ empty dto.leaves }">
							<div class="alert alert-warning">There is no Data.</div>
						</c:when>
						<c:otherwise>
							<c:import url="/jsp/include/class-leaves.jsp"></c:import>
						</c:otherwise>
					</c:choose>
					
				</div>
			</div>
	</div>

</body>
</html>