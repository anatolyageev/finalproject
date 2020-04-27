<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/22/2020
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="newTestCommand"/>
        <input type="hidden" name="subjectId" value="${subjectId}"/>

        <div class="form-group">
            <label for="inputNameEn"><fmt:message key="crate_subject.subject_name_en"/></label>
            <input type="text" class="form-control" id="inputNameEn" placeholder="Enter name for new test" name="nameEn" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="inputNameRu"><fmt:message key="crate_subject.subject_name_ru"/></label>
            <input type="text" class="form-control" id="inputNameRu" placeholder="<fmt:message key="crate_subject.create_test_form.place_holder_ru"/>" name="nameRu" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field.</div>
        </div>
        <div class="form-group">
            <label for="difficulty-level"> difficulty-level (between 1 and 100):</label>
            <input type="number" class="form-control" id="difficulty-level" placeholder="1" name="difficulty-level" min="1" max="100" required>
            <div class="valid-feedback">Valid.</div>
            <div class="invalid-feedback">Please fill out this field from 1-100.</div>
        </div>
        <div class="form-group">
            <label for="min-complete"> Minutes to complete (between 1 and 60):</label>
            <input type="number" class="form-control" id="min-complete" placeholder="1" name="min-complete" min="1" max="60" required>
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
