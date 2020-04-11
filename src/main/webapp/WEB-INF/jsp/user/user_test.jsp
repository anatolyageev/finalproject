<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/4/2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="/WEB-INF/jspf/head.jspf"%>

<body>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<ul class="links">
<%--    <c:forEach items="${questionList.get(0)}" var="question" varStatus="loop">--%>
    page
    ${pageId}
    <c:set var="question" value="${questionList.get(pageId)}"/>
        <form id="settings_form" action="controller" method="post" >
            <input type="hidden" name="command" value="userAnswerCommand" />
            <input type="hidden" name="question_id" value="${question.id}" />
        <p>${question.questionText}</p>

        <ul>
            <c:forEach items="${question.answers}" var = "answers" varStatus="loop">

                <c:choose>

                    <%--===========================================================================
                    This way we define radio when correct answer is only one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers == 1 }">
                        <li>

                            <label>${answers.answerText}</label>
                            <input name="answer_id" value="${answers.id}" type="radio" <c:if test="${answerIdUser.containsKey(answers.id)}">
                                   checked
                            </c:if>>
                        </li> &nbsp;
                    </c:when>
                    <%--===========================================================================
                    This way we define checkbox when correct answer more then one one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers > 1}">
                        <li>

                           <label>${answers.answerText}</label>
                            <input name="answer_id" value="${answers.id}" type="checkbox"<c:if test="${answerIdUser.containsKey(answers.id)}">
                                   checked
                            </c:if>>
                        </li> &nbsp;
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>

        <c:set var="key" value="${question.id}"/>


        <input type="submit" <c:if test="${mapAnswer.containsKey(key)}">
               disabled
        </c:if> value=<fmt:message key="test.answer.button"/>>
        </form>
<c:forEach items="${questionList}" var="question" varStatus="loop">

    <a href="${pageContext.request.contextPath}/controller?command=userPageChange&page=${loop.index}">Qustion ${loop.index + 1}</a>


<%--    <form action="controller" method="post" >--%>
<%--        <input type="hidden" name="command" value="userAnswerCommand" />--%>
<%--        <input type="hidden" name="page" value="${question.id}" />--%>
<%--        <input type="submit" value="${loop.index + 1}" />--%>
<%--    </form>--%>


   </c:forEach>

</ul>
<form id="settings_form" action="controller" method="post" >
    <input type="hidden" name="command" value="userTestFinishCommand" />
    <input type="submit" name="Finish" >
</form>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>

<script>

    // document.getElementById('not active').disabled = true;
// document.getElementsByName('not active').disabled = true;
    document.getElementsByClassName('not active').disabled = true;

</script>

</body>
</html>
