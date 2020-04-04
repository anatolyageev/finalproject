<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/4/2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="/WEB-INF/jspf/head.jspf"%>

<body>
<%@ include file ="/WEB-INF/jspf/hederCorrectrd.jsp"%>
<ul class="links">
    <c:forEach items="${questionList}" var="question" varStatus="loop">
        <li><a href="#">${question.questionText}</a></li>

        <ul>
            <c:forEach items="${question.answers}" var = "answers" varStatus="loop">

                <c:choose>

                    <%--===========================================================================
                    This way we define radio when correct answer is only one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers == 1 }">
                        <li>
                            <input name="${question.id}" type="radio">
                            <label>${answers.answerText}</label>
                        </li> &nbsp;
                    </c:when>
                    <%--===========================================================================
                    This way we define checkbox when correct answer more then one one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers > 1}">
                        <li>
                           <input name="${question.id}" type="checkbox">
                           <label>${answers.answerText}</label>
                        </li> &nbsp;
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>
        <input type="submit" value=<fmt:message key="test.answer.button"/>>
    </c:forEach>
</ul>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
