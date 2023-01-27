<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leaves | Home</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<c:url var="commonCss" value="/resources/application.css"></c:url>
<link rel="stylesheet" href="${ commonCss }">
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="classes"></c:param>
	</c:import>
	
	<div class="container">
		<h3 class="my-4">
			Class management system
		</h3>
		
		<c:url value="/classes" var="search"></c:url>
		<form action="${ search }" class="row mb-4">
		
			<div class="col-auto">
				<label for="form-label">Teacher Name :</label>
				<input type="text" name="teacher" placeholder="Enter Teacher Name... " class="form-control" value="${ param.teacher }" />
			</div>
			
			<div class="col-auto">
				<label for="form-label">Date From :</label>
				<input type="date" name="from" class="form-control" value="${ param.from }"/>
			</div>
			
			<div class="col-auto">
				<label for="form-label">Date To :</label>
				<input type="date" name="to" class="form-control" value="${ param.to }" />
			</div>
			
			<div class="col btn-wrapper">
				<button class="btn btn-outline-success me-2"><i class="bi bi-search"></i> Search</button>
				<c:url var="addNew" value="/classes/edit"></c:url>
				<a href="${ addNew }" class="btn btn-outline-danger"><i class="bi bi-plus-lg"></i> Add New</a>
			</div>
		
		</form>
		
		<c:choose>
			<c:when test="${ empty list }">
				<div class="alert alert-info">There is no Info.</div>
			</c:when>
			
			<c:otherwise>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Teacher Name</th>
							<th>Teacher Phone</th>
							<th>Start Date</th>
							<th>Months</th>
							<th>Students</th>
							<th>Description</th>
							<th></th>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${ list }" var="item">
							<tr>
								<td>${ item.id }</td>
								<td>${ item.teacherName }</td>
								<td>${ item.teacherphone }</td>
								<td>${ item.startDate }</td>
								<td>${ item.months }</td>
								<td>${ item.studentCount }</td>
								<td>${ item.description }</td>
								<td><c:url var="edit" value="/classes/edit">
										<c:param name="id" value="${ item.id }"></c:param>
									</c:url>
									<a href="${ edit }"><i class="bi bi-pencil me-3"></i></a> 
									<c:url var="details" value="/classes/${ item.id }"></c:url> 
									<a href="${ details }"><i class="bi bi-cursor"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
	</div>

</body>
</html>