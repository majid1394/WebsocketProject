<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
	<title>List Customers</title>

	<!-- reference our style sheet -->

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

<div id="wrapper">
	<div id="header">
		<h2>CRM - Customer Relationship Manager</h2>
	</div>
</div>

<div id="container">

	<div id="content">

		<!-- put new button: Add Customer -->

		<input type="button" value="Add Customer"
			   onclick="window.location.href='showFormForAdd'; return false;"
			   class="add-button"
		/>

		<!--  add our html table here -->

		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Account Number</th>
				<th>Account Type</th>
				<th>Action</th>
			</tr>

			<!-- loop over and print our customers -->
			<c:forEach var="tempCustomer" items="${customers}">
				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/customer/showFormForUpdate">
					<c:param name="customerId" value="${tempCustomer.id}" />
					<c:param name="action" value="update"/>
				</c:url>

				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
					<c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>
				<tr>
					<td> ${tempCustomer.firstName} </td>
					<td> ${tempCustomer.lastName} </td>
					<td> ${tempCustomer.email}</td>

				<c:forEach var="account" items="${customers.get(0).accountList}">
					<%--<c:if test = "tempCustomer.accountList.get(0).size() > 0">--%>
						<td> ${account.accountNumbers} </td>
						<td> ${account.accountType} </td>
				</c:forEach>

					<%--</c:if>--%>

					<td>
						<!-- display the update link -->
						<a href="${updateLink}">Update</a>
						|
						<a href="${deleteLink}"
						   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
					</td>

				</tr>

			</c:forEach>

		</table>
		<form:form action="${pageContext.request.contextPath}/logout"
				   method="POST">

			<input type="submit" value="Logout" />

		</form:form>

	</div>

</div>


</body>

</html>









