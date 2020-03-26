<%@ include file="/WEB-INF/jspf/head.jspf"%>

<%-- set the locale --%>
<fmt:setLocale value="${param.locale}" scope="session"/>
<c:out value="dddfff"/>
<%-- load the bundle (by locale) --%>
<fmt:setBundle basename="local"/>

<%-- set current locale to session --%>
<c:set var="currentLocale" value="${param.locale}" scope="session"/>

<%-- goto back to the settings--%>
<c:redirect url="/controller?command=viewSettings"/>

