<html>
<head>

    <%@ include file="/WEB-INF/jspf/head.jspf" %>

    <title>Title</title>
</head>
<body>

<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<%-- HEADER --%>
<%--===========================================================================
Defines the web form.
===========================================================================--%>
<%--<form id="login_form" method="post" action="/quiz/controller?command=login">--%>
<form id="login_form" action="controller" method="post">

    <%--===========================================================================
    Hidden field. In the query it will act as command=login.
    The purpose of this to define the command name, which have to be executed
    after you submit current form.
    ===========================================================================--%>
    <input type="hidden" name="command" value="login"/>

    <fmt:message key="login.login_form.login" var="login"/> ${login}: <input type="text" name="login" />
        <br/>
    <fmt:message key="login.login_form.password"/><input type="password" name="password" /><br/>
    <input type="submit" value="<fmt:message key="login.login_form.login"/>"/>

</form>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>