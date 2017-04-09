<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="navbar.jsp"%>

<!DOCTYPE html>
<html lang="en">
<script type="text/javascript" src="js/validation.js"></script>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cinema page</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	background: url(images/fon.jpg);
}
</style>
</head>
<body>
	<div class="container" style="color: black;">
		<div class="row">
			<div class="col-md-12">
				<div class="text-center">
					<h3>
						<fmt:message key="messages.enter" />
					</h3>

					<form name="ShowAllSchedulesForm" method="POST" action="controller">
						<input type="hidden" name="command" value="SHOW_SCHEDULE" />
						<button type="submit" class="btn btn-primary">
							<fmt:message key="button.show.schedule" />
						</button>
							<c:if test="${not empty exeptionMessage}">
						<div class="text-center">
							<h4>
								<font color="blue"><fmt:message key="message.exeptionMessage" /></font>
							</h4>
						</div>
					</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>