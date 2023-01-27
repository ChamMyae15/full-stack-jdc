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
		<c:param name="view" value="students"></c:param>
	</c:import>
	
	<div class="container">
	
		<h3 class="my-4">Students List</h3>
		
		
		<c:url value="/students" var="students"></c:url>
		<form action="${ students }" method="get" class="row mb-4">
			
			<div class="col-auto">
				<label class="form-label">Student Name :</label>
				<input type="text" name="name" class="form-control" value="${ param.name }" placeholder="Enter student name..." />
			</div>
			
			<div class="col-auto">
				<label class="form-label">Student Phone :</label>
				<input type="tel" name="phone" class="form-control" value="${ param.phone }" placeholder="Enter student phone..." />
			</div>
			
			<div class="col-auto">
				<label class="form-label">Student Email :</label>
				<input type="email" name="email" class="form-control" value="${ param.email }" placeholder="Enter student email..." />
			</div>
			
			<div class="col btn-wrapper">
			
				<button class="btn btn-outline-success me-2">Search</button>
				
				
				<!-- <a href="#" class="btn btn-outline-danger"><i class="bi bi-plus-lg"> Add New</i></a>  -->
			</div>
		</form>
		
		<c:choose>
			<c:when test="${ empty list }">
				<div class="alert alert-info">There is no Student Data!</div>
			</c:when>
			
			<c:otherwise>
				<table class="table table-hover">
					
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Phone</th>
							<th>Email</th>
							<th>Education</th>
							<th>Classes</th>
							<th></th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach var="s" items="${ list }">
							<tr>
								<td>${ s.id }</td>
								<td>${ s.name }</td>
								<td>${ s.phone }</td>
								<td>${ s.email }</td>
								<td>${ s.education }</td>
								<td>${ s.classCount }</td>
								<!-- <td>
									<c:url value="/students/edit" var="editStu">
										<c:param name="id" value="${ s.id }" ></c:param>
									</c:url>
										<a href="${ editStu }"><i class="bi bi-pencil"> Edit</i></a>
								</td> -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
		
	</div>

</body>
</html>