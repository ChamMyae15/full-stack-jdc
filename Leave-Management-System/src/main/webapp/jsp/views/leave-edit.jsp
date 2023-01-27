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
		<c:param name="view" value="leaves"></c:param>
	</c:import>
	
	<div class="container">
		<h3>Leave Application</h3>
		
		<div class="row">
			
			<c:url var="save" value="/leaves"></c:url>
			<sf:form action="${ save }" method="post" modelAttribute="form" cssClass="col-6">
			
				<sf:hidden path="classId"/>
				<sf:hidden path="studentId"/>
				<sf:hidden path="applyDate"/>
				
				<div class="mb-3">
					<label class="form-label">Class Information</label>
					<span class="form-control">${ classInfo.description } (${ classInfo.startDate })</span>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Teacher Name</label>
					<span class="form-control">${ classInfo.teacherName }</span>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Student Name</label>
					<span class="form-control">${ studentInfo.name }</span>
				</div>
				
				
				<div class="mb-3">
					<label class="form-label">Leave Start Date :</label>
					<sf:input path="startDate" type="date" cssClass="form-control"/>
					<sf:errors path="startDate" cssClass="text-secondary"></sf:errors>
				
					<label class="form-label">Leaves Days :</label>
					<sf:input path="days" type="number" cssClass="form-control"/>
					<sf:errors path="days" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div class="mb-3">
					<label class="form-label">Reason :</label>
					<sf:input path="reason" type="text" cssClass="form-control"/>
					<sf:errors path="reason" cssClass="text-secondary"></sf:errors>
				</div>
				
				<div>
					<button class="btn btn-outline-danger"><i class="bi bi-send"></i> Save</button>
				</div>
			</sf:form>
		</div>
	</div>

</body>
</html>