<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/28/2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="newQuestionCommand"/>
        <input type="hidden" name="testId" value="${sessionScope.testId}"/>

        <c:forEach items="${answerList}" var="answer" varStatus="loop">
            <div class="form-group col-xs-6 mb-2">
                <label for="newQuestionEn"><fmt:message key="crate_question.question_name_en"/></label>
                <textarea class="form-control" rows="2" id="newQuestionEn" placeholder="Enter text for new question"
                          name="id" value="${answer.id}" name="lang" value="en" required></textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>

            <div class="form-group col-xs-6 mb-2">
                <label for="newQuestionRu"><fmt:message key="crate_question.question_name_ru"/></label>
                <textarea class="form-control" rows="2" id="newQuestionRu" placeholder="Enter text for new question"
                          name="${answer.id}_ru" required></textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
        </c:forEach>

        <button type="submit" class="btn btn-primary"><fmt:message key="common_button.save"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
