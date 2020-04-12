<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/4/2020
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ include file ="/WEB-INF/jspf/head.jspf"%>

<div>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<ul class="links">
<%--    <c:forEach items="${questionList.get(0)}" var="question" varStatus="loop">--%>
    <div class="container">
        <div class="row mt-5">

    <c:set var="question" value="${questionList.get(pageId)}"/>
        <form id="settings_form" action="controller" method="post" >
            <input type="hidden" name="command" value="userAnswerCommand" />
            <input type="hidden" name="question_id" value="${question.id}" />

        <div>${question.questionText}</div>
        <div class="mt-5">
        <ul>
            <c:forEach items="${question.answers}" var = "answers" varStatus="loop">

                <c:choose>

                    <%--===========================================================================
                    This way we define radio when correct answer is only one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers == 1 }">
                        <li>

                            <label>${answers.answerText}</label>
                            <input name="answer_id" value="${answers.id}" type="radio" <c:if test="${answerIdUser.containsKey(answers.id)}">
                                   checked
                            </c:if>>
                        </li> &nbsp;
                    </c:when>
                    <%--===========================================================================
                    This way we define checkbox when correct answer more then one one.
                    ===========================================================================--%>
                    <c:when test="${question.numberCorrectAnswers > 1}">
                        <li>

                           <label>${answers.answerText}</label>
                            <input name="answer_id" value="${answers.id}" type="checkbox"<c:if test="${answerIdUser.containsKey(answers.id)}">
                                   checked
                            </c:if>>
                        </li> &nbsp;
                    </c:when>
                </c:choose>
            </c:forEach>
        </ul>

        <c:set var="key" value="${question.id}"/>

            <div class="mt-3">
        <input type="submit"
        <c:if test="${mapAnswer.containsKey(key)}">
               disabled
        </c:if>

               value=<fmt:message key="test.answer.button"/>>
            </div>
        </form>

        <div class="mt-3">
<c:forEach items="${questionList}" var="question" varStatus="loop">

    <a href="${pageContext.request.contextPath}/controller?command=userPageChange&page=${loop.index}">Qustion ${loop.index + 1}</a>


<%--    <form action="controller" method="post" >--%>
<%--        <input type="hidden" name="command" value="userAnswerCommand" />--%>
<%--        <input type="hidden" name="page" value="${question.id}" />--%>
<%--        <input type="submit" value="${loop.index + 1}" />--%>
<%--    </form>--%>


   </c:forEach>
        </div>
</ul>
    <div class="mt-5 ml-5">
<form action="controller" method="post" >
    <input type="hidden" name="command" value="userTestFinishCommand" />
    <input type="submit" name="Finish" >
</form>
    </div>
</div>
</div>
</div>

<%
    String mins = request.getParameter( "mins" );
    if( mins == null ) mins = "30";

    String secs = request.getParameter( "secs" );
    if( secs == null ) secs = "1";
%>
<script>
    <!--
    var mins = <%=mins%>; // write mins to javascript
    var secs = <%=secs%>; // write secs to javascript
    function timer()
    {
// tic tac
        if( --secs == -1 )
        {
            secs = 59;
            --mins;
        }

// leading zero? formatting
        if( secs < 10 ) secs = "0" + secs;
        if( mins < 10 ) mins = "0" + parseInt( mins, 10 );

// display
        document.forma.mins.value = mins;
        document.forma.secs.value = secs;

// continue?
        if( secs == 0 && mins == 0 ) // time over
        {
            document.forma.ok.disabled = true;
            document.formb.results.style.display = "block";
        }
        else // call timer() recursively every 1000 ms == 1 sec
        {
            window.setTimeout( "timer()", 1000 );
        }
    }
    //-->
</script></head>
<body>
<form action="<%=request.getRequestURL()%>" name="forma">
    Time remaining: <input type="text" name="mins" size="1" style="border:0px solid black;text-align:right">:<input type="text" name="secs" size="1" style="border:0px solid black">
    <hr>
    Question: What is green and turns red if you hit the button?<br>
    Answer: <input type="text" name="answer">
    <input type="submit" name="ok" value="OK">
</form>
<hr>
<form action="#" name="formb">
    <input type="submit" name="results" value="show results" style="display:none;">
</form>
<script>
    <!--
    timer(); // call timer() after page is loaded
    //-->
</script>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
<%--<script language="JavaScript" src="../../../js/countdown.js"></script>--%>
<script>

    // document.getElementById('not active').disabled = true;
// document.getElementsByName('not active').disabled = true;
    document.getElementsByClassName('not active').disabled = true;

</script>

</body>
</html>
