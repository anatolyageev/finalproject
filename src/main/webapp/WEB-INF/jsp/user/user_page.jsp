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

<c:forEach items="${requestScope.subjectList}" var="subject" varStatus="loop">

    <tr>
        <td>${subject.id}</td>
        <td>${subject.subjectName}</td>
    </tr>

    <ul class="links">
        <li><a href="#">${subject.subjectName}</a></li>
    </ul>


</c:forEach>





<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
