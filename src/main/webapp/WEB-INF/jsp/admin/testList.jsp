<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/22/2020
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>
<div class="container">
    <div class="row">
        <div class="col-md-8 order-md-2 ">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Test</th>
                    <th>Difficulty Level</th>
                    <th>Number of question</th>
                    <th>minutesToComplite</th>
                </tr>
                </thead>

                <c:forEach items="${test}" var="test" varStatus="loop">
                    <tr>
                        <td>
                            <form action="controller" method=post>
                                <input type="hidden" name="command" value="questionCommand"/>
                                <input type="hidden" name="testId" value="${test.id}"/>
                                <input type=submit class="btn btn-link" value="${test.testName}">
                            </form>
                        </td>
                        <td>${test.difficultyLevel}</td>
                        <td>${test.questionQuantity}</td>
                        <td>${test.minutesToComplite}</td>
                        <td>
                            <form action="controller" method=post>
                                <input type="hidden" name="command" value="editTestCommand"/>
                                <input type="hidden" name="subjectId" value="${subjectId}"/>
                                <input type="hidden" name="testId" value="${test.id}"/>
                                <button type="submit" class="btn btn-info" ><fmt:message key="common_button.edit"/></button>
                            </form>
                        </td>
                        <td>
                            <form action="controller" method=post>
                                <input type="hidden" name="command" value="deleteTestCommand"/>
                                <input type="hidden" name="subjectId" value="${subjectId}"/>
                                <input type="hidden" name="testId" value="${test.id}"/>
                                <button type="submit" class="btn btn-danger" ><fmt:message key="common_button.delete"/></button>
                            </form>
                        </td>

                    </tr>
                </c:forEach>

            </table>

            <div>

                <a type="button" class="btn btn-primary"
                   href="${pageContext.request.contextPath}/controller?command=createTestCommand&subjectId=${subjectId}">
                    <fmt:message
                            key="crate_subject.create_test"/> </a>
            </div>
        </div>
    </div>
</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>

