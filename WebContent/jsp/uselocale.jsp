<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${param.lang == 'en'}">
	<c:set var="locale" value="locale_en_US" scope="session" />
</c:if>
<c:if test="${param.lang == 'ru'}">
	<c:set var="locale" value="locale_ru_RU" scope="session" />
</c:if>

<c:if
	test="${sessionScope.locale == 'locale_en_US' or empty sessionScope.locale}">
	<fmt:setBundle basename="locale_en_US" />
</c:if>
<c:if test="${sessionScope.locale == 'locale_ru_RU'}">
	<fmt:setBundle basename="locale_ru_RU" />
</c:if>