<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/27/2020
  Time: 23:21
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

        <div class="form-group col-xs-6 mb-2">
            <label for="newQuestionEn"><fmt:message key="crate_question.question_name_en"/></label>
            <textarea class="form-control" rows="2" id="newQuestionEn" placeholder="Enter text for new question" name="newQuestionEn" required></textarea>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <div class="form-group col-xs-6 mb-2">
            <label for="newQuestionRu"><fmt:message key="crate_question.question_name_ru"/></label>
            <textarea class="form-control" rows="2" id="newQuestionRu" placeholder="Enter text for new question" name="newQuestionRu" required></textarea>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="common_button.next"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
