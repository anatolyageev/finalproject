<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/25/2020
  Time: 17:38
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="updateTestCommand"/>
        <input type="hidden" name="subjectId" value="${subjectId}"/>
        <input type="hidden" name="testId" value="${testEn.id}"/>

        <div class="form-group">
            <label for="inputNameEn"><fmt:message key="edit_test.test_name_en"/></label>
            <input type="text" class="form-control" id="inputNameEn" value="${testEn.testName}" name="nameEn" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="inputNameRu"><fmt:message key="edit_test.test_name_ru"/></label>
            <input type="text" class="form-control" id="inputNameRu" value="${testRu.testName}" name="nameRu" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="difficulty-level"> difficulty-level (between 1 and 100):</label>
            <input type="number" class="form-control" id="difficulty-level" value="${testEn.difficultyLevel}" name="difficulty-level" min="1" max="100" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field from 1-100.</div>
        </div>
        <div class="form-group">
            <label for="min-complete"> Minutes to complete (between 1 and 60):</label>
            <input type="number" class="form-control" id="min-complete" value="${testEn.minutesToComplite}" name="min-complete" min="1" max="60" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field from 1-60.</div>
        </div>

        <button type="submit" class="btn btn-primary"><fmt:message key="common_button.save"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
