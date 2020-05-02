<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 5/2/2020
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="updateQuestionCommand"/>
        <input type="hidden" name="questionId" value="${questionId}"/>


        <div class="form-group">
            <label for="inputNameEn"><fmt:message key="crate_question.question_name_en"/></label>
            <input type="text" class="form-control" id="inputNameEn" value="${questionEn.questionText}" name="nameEn" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="inputNameRu"><fmt:message key="crate_question.question_name_ru"/></label>
            <input type="text" class="form-control" id="inputNameRu" value="${questionRu.questionText}" name="nameRu" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="common_button.save"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>

