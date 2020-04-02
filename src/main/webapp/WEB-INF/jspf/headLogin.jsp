<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:message key="title.quizzes" var="title"/>
<head>

    <title>
        ${title}
    </title>

    <%--===========================================================================
    Bind CSS document.
    ===========================================================================--%>
    <link rel="stylesheet" href="styles/loginRegisterPage.css">
    <%--===========================================================================
    If you define http-equiv attribute, set the content type and the charset the same
    as you set them in a page directive.
    ===========================================================================--%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>