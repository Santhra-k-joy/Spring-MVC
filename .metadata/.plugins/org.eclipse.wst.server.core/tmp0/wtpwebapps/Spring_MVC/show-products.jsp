<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product list</title>
<link
	href="https://bootswatch.com/4/united/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div class="alert alert-primary">

		<div class="container">
			<h1>Spring MVC Demo</h1>
		</div>
	</div>
	<div class="container">
		<p>Product list</p>
		
		<p>
		<a href="./">Home</a>
		</p>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Picture</th>
					<th>Description</th>
					<th>Quantity per unit</th>
					<th>Brand</th>
					<th>Category</th>
					<th>Unit price (INR)</th>
					<th>Discount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${products}" var="p">
					<tr>
						<td>${p.id}</td>
						<td><img src="${p.picture}" height="60"></td>
						<td>${p.description}</td>
						<td>${p.quantityPerUnit}</td>
						<td>${p.brand}</td>
						<td>${p.category}</td>
						<td>${p.unitPrice}</td>
						<td>${p.discount}%</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>
</body>
</html>