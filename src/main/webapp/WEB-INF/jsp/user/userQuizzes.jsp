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
    <div class="row">
        <div class="col-md-4 order-md-1 ">
            <a href="${pageContext.request.contextPath}/controller?command=quizzeCommand">All subjects</a>
            <ul class="links">
                <c:forEach items="${requestScope.subjectList}" var="subject" varStatus="loop">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName}
                            # ${subject.id}</a></li>
                </c:forEach>
            </ul>


        </div>

        <div class="col-md-8 order-md-2 ">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Test</th>
                    <th>Difficulty Level</th>
                    <th>Number of question</th>
                </tr>
                </thead>

                <c:forEach items="${requestScope.testList}" var="test" varStatus="loop">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/controller?command=userTestCommand&test_id=${test.id}">${test.testName}</a></td>
                    <td>${test.difficultyLevel}</td>
                    <td>${test.questionQuantity}</td>
                </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
