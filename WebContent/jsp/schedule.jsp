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
<title>Show schedule page</title>
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
						<fmt:message key="title.show.all.schedules" />
					</h3>
				</div>

				<table class="table table-striped table-condensed table-bordered">
					<tr>
						<td align="center">№</td>
						<td align="center"><fmt:message key="show.schedule.name" /></td>
						<td align="center"><fmt:message key="show.schedule.time" /></td>
						<td align="center"><fmt:message key="show.schedule.places" /></td>
					</tr>

					<c:forEach items="${list}" varStatus="сounter">
						<tr>
							<td align="center">${сounter.count}</td>
							<td align="center">${list[сounter.count-1].name}</td>
							<td align="center">${list[сounter.count-1].time}</td>
							<td align="center">${list[сounter.count-1].places}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div class="col-md-4 col-md-offset-4">

				<form action="controller" method="POST" class="form-horizontal">
					<input type="hidden" name="command" value="RESERVATION">
					<div class="form-group">
						<label for="time"><fmt:message key="register.time" /></label> <input
							name="time" id="time" type="text" class="form-control"
							placeholder="<fmt:message key="register.time.placeholder" />">
					</div>
					<div class="form-group">
						<label for="place"><fmt:message key="register.place" /></label> <input
							name="place" id="place" type="text" class="form-control"
							placeholder="<fmt:message key="register.place.placeholder" />">
					</div>
					<button type="submit" class="btn btn-primary">
						<fmt:message key="button.register" />
					</button>
					<c:if test="${not empty exeptionMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.errorOrderMessage" /></font>
								
							</h4>
						</div>
					</c:if>
					<c:if test="${not empty errorTimeMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.errorTimeMessage" /></font>
							</h4>
						</div>
					</c:if>
					<c:if test="${not empty errorPlaceMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.errorPlaceMessage" /></font>
							</h4>
						</div>
					</c:if>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>