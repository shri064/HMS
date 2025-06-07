<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Specialist</title>
<%@include file="../component/allcss.jsp"%>
</head>
<body>

	<%@include file="navbar.jsp"%>

	<div class="container p-5">
		<h3 class="text-center text-danger">Add Specialist</h3>

		<c:if test="${not empty successMsg}">
			<p class="text-center text-success fs-5">${successMsg}</p>
			<c:remove var="successMsg" scope="session" />
		</c:if>

		<c:if test="${not empty errorMsg}">
			<p class="text-center text-danger fs-5">${errorMsg}</p>
			<c:remove var="errorMsg" scope="session" />
		</c:if>

		<form action="../addSpecialist" method="post"
			class="w-50 mx-auto mt-4">
			<div class="form-group mb-3">
				<label class="form-label">Enter Specialist Name</label> <input
					type="text" name="specialistName" class="form-control"
					placeholder="Enter specialist name" required />
			</div>
			<div class="text-center">
				<button type="submit" class="btn btn-danger">Add Specialist</button>
			</div>
		</form>
	</div>

</body>
</html>
