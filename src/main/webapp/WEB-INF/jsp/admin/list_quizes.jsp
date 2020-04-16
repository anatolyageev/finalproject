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
        <div class="col-md-4 order-md-1 ">
            <a href="${pageContext.request.contextPath}/controller?command=quizzeCommand">All subjects</a>
<div>
<%--            <ul class="links">--%>
                <div class="btn-group-vertical" role="group">
                <c:forEach items="${sessionScope.subjectList}" var="subject" varStatus="loop">
<%--                    <li>--%>
<%--                    <button type="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName}</button>--%>
                        <a type="button" class="btn btn-secondary" href="${pageContext.request.contextPath}/controller?command=quizzeTestCommand&subject_id=${subject.id}">${subject.subjectName}</a>
<%--                    </li>--%>

                </c:forEach>
                </div>
<%--            </ul>--%>
</div>
        </div>

        <div class="col-md-8 order-md-2 ">
            Для редактирования теста выберете предмет.
            Если необходимо добавить новый нажмите кнопку Add




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
