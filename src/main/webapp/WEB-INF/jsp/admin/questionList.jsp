<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/25/2020
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="/WEB-INF/jspf/head.jspf"%>
<body>
<%--<div>--%>
    <%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%--    <ul class="links">--%>
        <%--    <c:forEach items="${questionList.get(0)}" var="question" varStatus="loop">--%>
        <div class="container">
<%--            <div class="row mt-5">--%>

                <c:set var="questionEn" value="${questionListEn.get(pageId)}"/>
                <c:set var="questionRu" value="${questionListRu.get(pageId)}"/>
                <form class="questions-answers" action="controller" method="post" >
                    <input type="hidden" name="command" value="userAnswerCommand" />
                    <input type="hidden" name="question_id" value="${question.id}" />

                    <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="questionTextEn"><fmt:message key="edit_test.test_name_en"/></label>
                        <textarea class="form-control" id="questionTextEn" value="${questionEn.questionText}" name="questionEn" rows="2" required>${questionEn.questionText}</textarea>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>

                    <div class="form-group col-md-6">
                        <label for="questionTextRu"><fmt:message key="edit_test.test_name_ru"/></label>
                        <textarea class="form-control" id="questionTextRu" value="${questionRu.questionText}" name="questionRu" rows="2" required>${questionRu.questionText}</textarea>
                        <div class="valid-feedback">Valid.</div>
                        <div class="invalid-feedback">Please fill out this field.</div>
                    </div>
                    </div>

                    <div class="mt-5">
                        <ul>
                            <c:forEach items="${questionEn.answers}" var = "answersEn" varStatus="loop">
                                        <li>

                                            <label>${answersEn.answerText}</label>
                                            <input name="answer_id" value="${answerEn.id}" type="radio" <c:if test="${answerIdUser.containsKey(answers.id)}">
                                                   checked
                                            </c:if>>
                                        </li> &nbsp;

                            </c:forEach>
                        </ul>

                        <c:set var="key" value="${questionEn.id}"/>

                        <div class="mt-3">
                            <input type="submit"
                            <c:if test="${mapAnswer.containsKey(key)}">
                                   disabled
                            </c:if>

                                   value=<fmt:message key="test.answer.button"/>>
                        </div>
                </form>

                <div class="mt-3">
                    <c:forEach items="${questionListEn}" var="question" varStatus="loop">

                        <a href="${pageContext.request.contextPath}/controller?command=adminPageChange&page=${loop.index}"><fmt:message key="user_test.Qustion"/> ${loop.index + 1}</a>

                    </c:forEach>
                </div>
<%--    </ul>--%>
    <div class="mt-5 ml-5">
        <form id="submitTest" action="controller" method="post" >
            <input type="hidden" name="command" value="userTestFinishCommand" />
            <input type="submit" name="Finish" >
        </form>
    </div>
</div>
<%--</div>--%>
<%--</div>--%>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
