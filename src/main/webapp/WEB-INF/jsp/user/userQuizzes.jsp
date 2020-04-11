<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/11/2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<a href="${pageContext.request.contextPath}/controller?command=subjectCommand">Show subjects</a>
<ul class="links">
    <c:forEach items="${requestScope.subjectList}" var="subject" varStatus="loop">
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName}
                # ${subject.id}</a></li>
    </c:forEach>
</ul>

<ul class="links">
    <c:forEach items="${requestScope.testList}" var="test" varStatus="loop">
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=userTestCommand&test_id=${test.id}">${test.testName}</a>
        </li>
    </c:forEach>
</ul>

</body>
</html>
