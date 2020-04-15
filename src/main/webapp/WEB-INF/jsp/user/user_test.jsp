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
            <p id="demo"></p>
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

    <a href="${pageContext.request.contextPath}/controller?command=userPageChange&page=${loop.index}"><fmt:message key="user_test.Qustion"/> ${loop.index + 1}</a>

   </c:forEach>
        </div>
</ul>
    <div class="mt-5 ml-5">
<form id="submitTest" action="controller" method="post" >
    <input type="hidden" name="command" value="userTestFinishCommand" />
    <input type="submit" name="Finish" >
</form>
    </div>
</div>
</div>
</div>


<%@ include file="/WEB-INF/jspf/footer.jspf"%>

<%--JS for timer--%>
<script>
d = new Date();
d = getCookie("testEndTime");
console.log(d);
    function addMinutes(date, minutes) {
        return new Date(date.getTime() + minutes*60000);
    }

    // Set the date we're counting down to
    var countDownDate = new Date(d).getTime();

    // Update the count down every 1 second
    var x = setInterval(function() {

        // Get today's date and time
        var now = new Date().getTime();

        // Find the distance between now and the count down date
        var distance = countDownDate - now;

        // Time calculations for days, hours, minutes and seconds
        var days = Math.floor(distance / (1000 * 60 * 60 * 24));
        var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        // Output the result in an element with id="demo"
        // document.getElementById("demo").innerHTML = days + "d " + hours + "h "  + minutes + "m " + seconds + "s ";
        document.getElementById("demo").innerHTML = minutes + ":" + seconds;

        // If the count down is over, write some text
        if (distance < 0) {
            clearInterval(x);
            document.getElementById("submitTest").submit();
            document.getElementById("demo").innerHTML = "EXPIRED";
        }
    }, 1000);

    function getCookie(name) {
        var cookie = " " + document.cookie;
        var search = " " + name + "=";
        var setStr = null;
        var offset = 0;
        var end = 0;
        if (cookie.length > 0) {
            offset = cookie.indexOf(search);
            if (offset != -1) {
                offset += search.length;
                end = cookie.indexOf(";", offset)
                if (end == -1) {
                    end = cookie.length;
                }
                setStr = unescape(cookie.substring(offset, end));
            }
        }
        return(setStr);
    }

</script>

<%--JS for disabling answered qustion--%>
<script>

    document.getElementsByClassName('not active').disabled = true;

</script>

</body>
</html>
