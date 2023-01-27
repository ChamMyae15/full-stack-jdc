<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leaves | Home</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<c:url var="commonCss" value="/resources/application.css"></c:url>
<link rel="stylesheet" href="${ commonCss }">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>
<body>

	<c:import url="/jsp/include/navbar.jsp">
		<c:param name="view" value="teachers"></c:param>
	</c:import>
	
	<div class="container">
		
		<h3 class="my-4">Teachers Container</h3>
		
		<!-- search bar -->
		
		<form action="#" class="row mb-4">
		
		<div class="col-auto">
			<label for="form-label">Teacher Name :</label>
			<input type="text" name="name" class="form-control" value="${ param.name}" placeholder="Enter Teacher name..." />
		</div>
		
		<div class="col-auto">
			<label for="form-label">Teacher Phone :</label>
			<input type="tel" name="phone" class="form-control" value="${ param.phone}" placeholder="Enter Teacher phone..." />
		</div>
		
		<div class="col-auto ${ empty param.id ? '':'d-none' }">
			<label for="form-label">Teacher Email :</label>
			<input type="text" name="email" class="form-control" value="${ param.email}" placeholder="Enter Teacher email..." />
		</div>
		
		<div class="col btn-wrapper">
			<button class="btn btn-outline-success me-2"><i class="bi bi-search"></i>Search</button>
			
			<c:url var="addNew" value="/teachers/edit"></c:url>
				<a href="${ addNew }" class="btn btn-outline-danger">
				<i class="bi bi-plus-lg"></i>Add New</a>
			
		</div>
		
		
		</form>
		
		<c:choose>
			
			<c:when test="${ empty list }">
				<div class="alert alert-info">There is no data.</div>
			</c:when>
			
			<c:otherwise>
				<!-- Table -->
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Assign Date</th>
							<th>Classes</th>
						</tr>
					</thead>

					<tbody>
					
						<c:forEach items="${ list }" var="t">
							<tr>
							<td>${ t.id }</td>
							<td>${ t.name }</td>
							<td>${ t.phone }</td>
							<td>${ t.email }</td>
							<td>${ t.assignDate }</td>
							<td>${ t.classCount }</td>
							<td><c:url var="edit" value="/teachers/edit">
									<c:param name="id" value="${ t.id }"></c:param>
								</c:url> <a href="${ edit }"><i class="bi bi-pencil"></i></a></td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</c:otherwise>
		
		</c:choose>
		
		
		
	
	</div>

</body>
</html>