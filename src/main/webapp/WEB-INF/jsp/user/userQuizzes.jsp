<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/11/2020
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>
<div class="container">
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
</div>
<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
