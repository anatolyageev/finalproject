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




<%--<%@ include file="/WEB-INF/jspf/head.jspf"%>--%>

<%--&lt;%&ndash; set the locale &ndash;%&gt;--%>
<%--<fmt:setLocale value="${param.locale}" scope="session"/>--%>

<%--&lt;%&ndash; load the bundle (by locale) &ndash;%&gt;--%>
<%--<fmt:setBundle basename="local"/>--%>

<%--&lt;%&ndash; set current locale to session &ndash;%&gt;--%>
<%--<c:set var="sessionLocale" value="${param.locale}" scope="session"/>--%>


<%--&lt;%&ndash; goto back to the settings&ndash;%&gt;--%>
<%--<jsp:forward page="WEB-INF/jsp/settings.jsp"/>--%>

