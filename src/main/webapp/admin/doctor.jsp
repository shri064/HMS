<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hms.entity.Specialist"%>
<%@ page import="com.hms.dao.SpecialistDAO"%>
<%@ page import="com.hms.db.DBConnection"%>
<%@ page import="java.util.List"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Doctor</title>

<%@ include file="../component/allcss.jsp"%>

<style>
.my-card {
	box-shadow: 0px 0px 10px 1px maroon;
}
</style>
</head>
<body>

	<%@ include file="navbar.jsp"%>

	<div class="container-fluid p-3">
		<div class="row">
			<div class="col-md-5 offset-4">
				<div class="card my-card">
					<div class="card-body">
						<p class="fs-3 text-center text-danger">Add Doctor</p>

						<!-- Success Message -->
						<c:if test="${not empty successMsg}">
							<p class="text-center text-success fs-3">${successMsg}</p>
							<c:remove var="successMsg" scope="session" />
						</c:if>

						<!-- Error Message -->
						<c:if test="${not empty errorMsg}">
							<p class="text-center text-danger fs-3">${errorMsg}</p>
							<c:remove var="errorMsg" scope="session" />
						</c:if>

						<!-- Doctor Form -->
						<form action="../addDoctor" method="post">
							<div class="mb-3">
								<label class="form-label">Full Name</label> <input
									name="fullName" type="text" class="form-control"
									placeholder="Enter full name">
							</div>

							<div class="mb-3">
								<label class="form-label">Date of Birth</label> <input
									name="dateOfBirth" type="date" class="form-control">
							</div>

							<div class="mb-3">
								<label class="form-label">Qualification</label> <input
									name="qualification" type="text" class="form-control"
									placeholder="Enter qualification">
							</div>

							<div class="mb-3">
								<label class="form-label">Specialist</label> <select
									name="specialist" class="form-control">
									<option disabled selected>---Select---</option>
									<%
									SpecialistDAO spDAO = new SpecialistDAO(DBConnection.getConn());
									List<Specialist> spList = spDAO.getAllSpecialist();
									for (Specialist sp : spList) {
									%>
									<option><%=sp.getSpecialistName()%></option>
									<%
									}
									%>
								</select>
							</div>

							<div class="mb-3">
								<label class="form-label">Email address</label> <input
									name="email" type="email" class="form-control"
									placeholder="Enter email">
							</div>

							<div class="mb-3">
								<label class="form-label">Phone</label> <input name="phone"
									type="text" class="form-control"
									placeholder="Enter mobile number">
							</div>

							<div class="mb-3">
								<label class="form-label">Password</label> <input
									name="password" type="password" class="form-control"
									placeholder="Enter password">
							</div>

							<button type="submit" class="btn btn-danger text-white col-md-12">Register</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
