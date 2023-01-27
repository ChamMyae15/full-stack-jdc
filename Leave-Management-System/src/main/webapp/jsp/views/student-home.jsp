<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student | Home</title>
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
	
	<c:import url="/jsp/include/navbar.jsp"></c:import>

		<div class="container">
			
			<h3 class="my-4">Student home</h3>
			
				<div class="row">
					<div class="col-3">
						<div class="card">
						
							<div class="card-header">Personal Information</div>
							
							<div class="card-body">
							
								<div class="mt-3">
									<span class="text-secondary"></span>
									<h5>${ dto.student.name }</h5>
								</div>
								
								<div class="mt-3">
									<span class="text-secondary"></span>
									<h5>${ dto.student.phone }</h5>
								</div>
								
								<div class="mt-3">
									<span class="text-secondary"></span>
									<h5>${ dto.student.email }</h5>
								</div>
								
								<div class="mt-3">
									<span class="text-secondary"></span>
									<h5>${ dto.student.education }</h5>
								</div>
							</div>
						</div>
					</div>
				
				
				<div class="col">
				
					<h3 class="mb-4">Registration</h3>
					
					<div class="row g-3">
					
						<c:forEach var="item" items="${ dto.registrations }">
						
						<div class="col-4">
						
							<div class="card card-body">
						
							<h5>${ item.classInfo }</h5>
							
							<div class="d-flex justify-content-between text-secondary mb-3">
								<span>${ item.startDate }</span>
								<span>${ item.teacher }</span>
							</div>
							
							<div>
								<c:url var="applyLeave" value="/leaves/edit">
									<c:param name="classesId" value="${ item.classId}"></c:param>
									<c:param name="studentId" value="${ item.studentId }"></c:param>
								</c:url>
								<a href="${ applyLeave }" class="btn btn-outline-success">
									<i class="bi bi-send"> Apply Leave</i>
								</a>
							</div>
						
						</div>
						
						</div>
					
						
						
						</c:forEach>
						
					</div>
					
					</div>
					
				</div>
		
		</div>

</body>
</html>