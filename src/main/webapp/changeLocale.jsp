<%@ include file="/WEB-INF/jspf/head.jspf"%>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>

<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="local"/>

<%-- set current locale to session --%>
<c:set var="sessionLocale" value="${param.locale}" scope="session"/>


<%-- goto back to the settings--%>
<jsp:forward page="WEB-INF/jsp/settings.jsp"/>

