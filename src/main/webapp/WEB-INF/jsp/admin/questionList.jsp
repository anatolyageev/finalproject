<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/25/2020
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<body>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>
<div class="container">
    <div class="row">
        <div class="col-md-10 order-md-1 mr-5">
            <%--            <table class="table table-borderless">--%>
            <c:forEach items="${sessionScope.questionList}" var="question" varStatus="loop">

                <div class="input-group col-xs-6 mb-2">
                    <textarea class="form-control" rows="2" id="comment" readonly>${question.questionText}</textarea>
                    <div class="input-group-append">
                        <button class="btn btn-info" type="button"><fmt:message
                                key="common_button.edit"/></button>


                        <form action="controller" method=post>
                            <input type="hidden" name="command" value="editAnswerCommand"/>
                            <input type="hidden" name="questionId" value="${question.id}"/>
                        <button type="submit" class="btn btn-primary" type="button"  ><fmt:message
                                key="crate_question.edit_answers"/></button>
                        </form>


                        <button class="btn btn-danger" type="button"><fmt:message
                                key="common_button.delete"/></button>
                    </div>
                </div>


                <%--                    <tr>--%>
                <%--                        <td>--%>
                <%--                            <a type="button" class="btn btn-info"--%>
                <%--                               href="${pageContext.request.contextPath}/controller?command=testsListCommand&subjectId=${question.id}">${question.questionText}</a>--%>
                <%--                        </td>--%>
                <%--                        <td>--%>
                <%--                            <a type="button" class="btn btn-info"--%>
                <%--                               href="${pageContext.request.contextPath}/controller?command=editSubjectCommand&subjectId=${question.id}">Edit</a>--%>
                <%--                        </td>--%>
                <%--                        <td>--%>
                <%--                            <a type="button" class="btn btn-danger"--%>
                <%--                               href="${pageContext.request.contextPath}/controller?command=deleteSubjectCommand&subjectId=${question.id}">Del</a>--%>
                <%--                        </td>--%>
                <%--                    </tr>--%>
            </c:forEach>
            <%--            </table>--%>
            <div>
                <a type="button" class="btn btn-primary"
                   href="${pageContext.request.contextPath}/controller?command=createQuestionCommand"> <fmt:message
                        key="crate_question.create_question"/> </a>

            </div>
        </div>
    </div>
</div>
<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
</body>
</html>
