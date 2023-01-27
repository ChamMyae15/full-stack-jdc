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
		<c:param name="view" value="leaves"></c:param>
	</c:import>
	
	<div class="container">
		<h3>Leaves</h3>
		
		<c:url value="/leaves" var="search"></c:url>
		<form action="${ search }" method="get" class="row mb-4">
			
			<div class="col-auto">
				<label class="form-label">Leave Date From : </label>
				<input type="date" name="from" class="form-control" value="${ param.from }" />
			</div>
			
			<div class="col-auto">
				<label class="form-label">Leave Date To : </label>
				<input type="date" name="to" class="form-control" value="${ param.to }" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-success"><i class="bi bi-search"></i> Search</button>
			</div>
		</form>
		
		<table class="table table-hover">
			<thead>
				<tr>
					
					<th>Student Name</th>
					<th>Student Phone</th>
					<th>Teacher Name</th>
					<th>Apply Date</th>
					<th>Start Date</th>
					<th>Days</th>
					<th>Reason</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:choose>
					<c:when test="${ empty list }">
						<div class="alert alert-info">There is no Leave Data.</div>
					</c:when>
					
					<c:otherwise>
						<c:forEach items="${ list }" var="l">
							<tr>
								
								<td>${ l.student }</td>
								<td>${ l.studentPhone }</td>
								<td>${ l.teacher }</td>
								<td>${ l.applyDate }</td>
								<td>${ l.startDate }</td>
								<td>${ l.days }</td>
								<td>${ l.reason }</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				
			</tbody>
		</table>
		
		<div class="col-3">
			<c:url value="/home" var="home"></c:url>
			<a href="${ home }" class=" btn btn-outline-success"><i class="bi bi-arrow-left-circle-fill"> Back to Home</i></a>
		</div>
		
	</div>

</body>
</html>