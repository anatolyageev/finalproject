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
                <c:forEach items="${sessionScope.subjectList}" var="subject" varStatus="loop">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="col-md-8 order-md-2 ">
            <div class="dropdown">
                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                    Sort by:
                </button>
                <div class="dropdown-menu">
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=quizzeTestSortCommand&sort=name">Sort
                        by name</a>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=quizzeTestSortCommand&sort=level">Sort
                        by level</a>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=quizzeTestSortCommand&sort=number">Sort
                        by number</a>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/controller?command=quizzeTestSortCommand&sort=minutes">Sort
                        by minutes</a>
                </div>
            </div>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Test</th>
                    <th>Difficulty Level</th>
                    <th>Number of question</th>
                    <th>minutesToComplite</th>
                </tr>
                </thead>

                <c:forEach items="${sessionScope.testList}" var="test" varStatus="loop">
                    <tr>
                        <td>
                            <form action="controller" method=post
                                  onsubmit="return doSomething(${test.minutesToComplite})">
                                <input type="hidden" name="command" value="userTestCommand"/>
                                <input type="hidden" name="test_id" value="${test.id}"/>
                                <input type=submit value="${test.testName}">
                            </form>
                        </td>
                        <td>${test.difficultyLevel}</td>
                        <td>${test.questionQuantity}</td>
                        <td>${test.minutesToComplite}</td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
<script>

    function addMinutes(minutes) {
        var d = new Date();
        return d.setMinutes(d.getMinutes() + minutes);
    }

    function doSomething(minutes) {
        var d = new Date()
        d.setMinutes(d.getMinutes() + minutes);
        document.cookie = "testEndTime=" + d.toUTCString();
    }
</script>
</body>
</html>
