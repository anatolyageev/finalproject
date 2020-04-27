<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/20/2020
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="newSubjectCommand"/>
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="inputShortName"><fmt:message key="crate_subject.subject_short_name"/></label>
                <input type="text" class="form-control" id="inputShortName" placeholder="Java" name="nameShort"
                       required="true">
            </div>
        </div>
        <div class="form-group">
            <label for="inputNameEn"><fmt:message key="crate_subject.subject_name_en"/></label>
            <input type="text" class="form-control" id="inputNameEn" name="nameEn" required="true">
        </div>
        <div class="form-group">
            <label for="inputNameRu"><fmt:message key="crate_subject.subject_name_ru"/></label>
            <input type="text" class="form-control" id="inputNameRu" name="nameRu" required="true">
        </div>
        <button type="submit" class="btn btn-primary"><fmt:message key="common_button.save"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
