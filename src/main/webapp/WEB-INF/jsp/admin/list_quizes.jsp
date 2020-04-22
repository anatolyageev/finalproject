<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 3/22/2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>
<div class="container">
    <div class="row">
        <%--        <div class="col-md-4 order-md-1 mb-5">--%>
        <div class="col-md-4 order-md-1 mb-5">
            <table class="table table-borderless">
                <%--            <div>--%>
                <%--                <div class="btn-group-vertical" role="group" mb-5>--%>
                <c:forEach items="${sessionScope.subjectList}" var="subject" varStatus="loop">
                    <tr>
                        <td>
                            <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=testsListCommand&subjectId=${subject.id}">${subject.subjectName}</a>
                        </td>
                        <td>
                            <a type="button" class="btn btn-info" href="${pageContext.request.contextPath}/controller?command=editSubjectCommand&subjectId=${subject.id}">Edit</a>
                        </td>
                        <td>
                            <a type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/controller?command=deleteSubjectCommand&subjectId=${subject.id}">Del</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>


            <div>

                <a type="button" class="btn btn-primary"
                   href="${pageContext.request.contextPath}/controller?command=createSubjectCommand"> <fmt:message
                        key="crate_subject.create_subject"/> </a>


            </div>
            <%--            </ul>--%>
        </div>


    <div class="col-md-8 order-md-2 ">
        <fmt:message key="crate_subject.text1"/>
        <ul>
            <li><fmt:message key="crate_subject.text2"/></li>
            <li><fmt:message key="crate_subject.text3"/></li>
            <li><fmt:message key="crate_subject.text4"/></li>
        </ul>
    </div>
    </div>
</div>


<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
<script>

    function addMinutes(minutes) {
        var d = new Date();
        return d.setMinutes(d.getMinutes() + minutes);
    }

    function doSomething(minutes) {
        var d = new Date()
        d.setMinutes(d.getMinutes() + minutes);
        document.cookie = "testEndTime=" + d.toUTCString();
    }
</script>
</body>
</html>
