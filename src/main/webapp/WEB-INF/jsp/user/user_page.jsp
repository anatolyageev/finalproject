<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 3/22/2020
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<%-- HEADER --%>
<h2>this is user page</h2>
<a href="${pageContext.request.contextPath}/controller?command=subjectCommand">Show subjects</a>
<ul class="links">
<c:forEach items="${requestScope.subjectList}" var="subject" varStatus="loop">
<%--    <tr>--%>
<%--        <td>${subject.id}</td>--%>
<%--        <td>${subject.subjectName}</td>--%>
<%--    </tr>--%>
        <li><a href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName} # ${subject.id}</a></li>
</c:forEach>
</ul>

<ul class="links">
    <c:forEach items="${requestScope.testList}" var="test" varStatus="loop">
        <%--    <tr>--%>
        <%--        <td>${subject.id}</td>--%>
        <%--        <td>${subject.subjectName}</td>--%>
        <%--    </tr>--%>
        <li><a href="#">${test.testName}</a></li>
    </c:forEach>
</ul>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
