<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/25/2020
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="/WEB-INF/jspf/head.jspf"%>

<div>
    <%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
    <ul class="links">
        <%--    <c:forEach items="${questionList.get(0)}" var="question" varStatus="loop">--%>
        <div class="container">
            <div class="row mt-5">

                <c:set var="question" value="${questionList.get(pageId)}"/>
                <form id="settings_form" action="controller" method="post" >
                    <input type="hidden" name="command" value="userAnswerCommand" />
                    <input type="hidden" name="question_id" value="${question.id}" />
                    <p id="demo"></p>
                    <div>${question.questionText}</div>
                    <div class="mt-5">
                        <ul>
                            <c:forEach items="${question.answers}" var = "answers" varStatus="loop">
                                        <li>

                                            <label>${answers.answerText}</label>
                                            <input name="answer_id" value="${answers.id}" type="radio" <c:if test="${answerIdUser.containsKey(answers.id)}">
                                                   checked
                                            </c:if>>
                                        </li> &nbsp;

                            </c:forEach>
                        </ul>

                        <c:set var="key" value="${question.id}"/>

                        <div class="mt-3">
                            <input type="submit"
                            <c:if test="${mapAnswer.containsKey(key)}">
                                   disabled
                            </c:if>

                                   value=<fmt:message key="test.answer.button"/>>
                        </div>
                </form>

                <div class="mt-3">
                    <c:forEach items="${questionList}" var="question" varStatus="loop">

                        <a href="${pageContext.request.contextPath}/controller?command=userPageChange&page=${loop.index}"><fmt:message key="user_test.Qustion"/> ${loop.index + 1}</a>

                    </c:forEach>
                </div>
    </ul>
    <div class="mt-5 ml-5">
        <form id="submitTest" action="controller" method="post" >
            <input type="hidden" name="command" value="userTestFinishCommand" />
            <input type="submit" name="Finish" >
        </form>
    </div>
</div>
</div>
</div>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
