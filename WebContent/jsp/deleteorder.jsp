<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="navbar.jsp"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Delete order page</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background: url(images/fon1.jpg);
}
</style>
</head>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="text-center">
					<h3>
						<fmt:message key="title.show.all.orders" />
					</h3>
				</div>

				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<td align="center"><fmt:message key="show.order.number" /></td>
						<td align="center"><fmt:message key="show.order.places" /></td>
						<td align="center"><fmt:message key="show.order.time" /></td>
					</tr>

					<c:forEach items="${list}" varStatus="сounter">
						<tr>
							<td align="center">${list[сounter.count-1].numberOfOrder}</td>
							<td align="center">${list[сounter.count-1].placesOrder}</td>
							<td align="center">${list[сounter.count-1].showTime}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-4 col-md-offset-4">

				<form action="controller" method="POST" class="form-horizontal">
					<input type="hidden" name="command" value="DELETE_ORDER">
					<div class="form-group">
						<label for="order"><fmt:message key="delete.order.number" /></label>
						<input name="order" id="order" type="text" class="form-control"
							placeholder="<fmt:message key="delete.order.number.placeholder" />">
					</div>

					<button type="submit" class="btn btn-primary">
						<fmt:message key="button.delete.order" />
					</button>
					<c:if test="${not empty errorOrderMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.errorOrderMessage" /></font>
								
							</h4>
						</div>
					</c:if>
					<c:if test="${not empty exeptionMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.errorOrderMessage" /></font>
								
							</h4>
						</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>
</body>
</html>