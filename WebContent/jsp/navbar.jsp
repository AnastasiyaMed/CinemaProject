<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="uselocale.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--  <link href="../css/bootstrap.min.css" rel="stylesheet">-->
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"><fmt:message key="title.about" /> <b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="http://silverscreen.by" target="_blank">Silverscreen.by</a></li>
							<li><a href="http://pogoda.by" target="_blank">Pogoda.by</a></li>
							<li><a href="http://www.nbrb.by" target="_blank">Nbrb.by</a></li>
						</ul></li>
				</ul>
				<form action="controller" method="POST"
					class="navbar-form navbar-left">
					<input type="hidden" name="command" value="SHOW_ORDER">
					<button type="submit" class="btn btn-default">
						<fmt:message key="nav.order.number" />
					</button>
				</form>

				<form action="controller" method="POST"
					class="navbar-form navbar-right">
					<c:if
						test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}">
						<button type="submit" name="lang" value="ru"
							class="btn btn-default">
							<fmt:message key="nav.locale" />
						</button>
					</c:if>
					<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
						<button type="submit" name="lang" value="en"
							class="btn btn-default">
							<fmt:message key="nav.locale" />
						</button>
					</c:if>
				</form>
				<form action="controller" method="POST"
					class="navbar-form navbar-right">
					<input type="hidden" name="command" value="GO_TO_HEAD">
					<button type="submit" class="btn btn-default">
						<fmt:message key="nav.goto.menu" />
					</button>
				</form>
			</div>
		</div>
	</nav>
</body>
</html>