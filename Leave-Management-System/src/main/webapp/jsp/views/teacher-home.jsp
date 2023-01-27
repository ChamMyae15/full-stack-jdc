<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Teacher | Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>
</head>>
<body>

	<c:import url="/jsp/include/navbar.jsp">
	</c:import>
	<div class="container">
		<h3 class="my-4">Teacher Home</h3>
		
			<form class="row mb-4">
			
				<div class="col-auto">
					<input type="date" name="targrtDate" class="form-control" value="${ targetDate }" />
				</div>
				<div class="col-auto">
					<button type="submit" class="btn btn-outline-success">
						<i class="bi bi-search"> Search</i>
					</button>
				</div>
				
			</form>
			
			<div class="row g-3">
			
				<c:forEach items="${ list }" var="item">
					<div class="col-4">
						<div class="card">
							<div class="card-body">
								<h4>${ item.teacher }</h4>
								<span class="text-secondary">${ item.startDate }</span>
								
								<div class="row mt-4">
									<div class="col-4">
										<h5><i class="bi bi-people"> ${ item.students }</i></h5>
										<div class="text-secondary">${ item.detail }</div>
										<span class="text-secondary">Students</span>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class="col-4">
										<h5><i class="bi bi-people-fill"></i>${ item.leaves }</h5>
										<span class="text-secondary">Leaves</span>
									</div>
								</div>
								
								<div class="row mt-4">
									<div class="col-4">
									<c:url var="detail" value="/classes/${ item.classId }"></c:url>
										<a href="${ detail }" class="btn btn-outline-success"><i class="bi bi-send"></i>Show Details</a>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				
				</c:forEach>
			
			</div>
	
	</div>

</body>
</html>