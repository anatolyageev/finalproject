<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 4/28/2020
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%-- HEADER --%>
<%@ include file="/WEB-INF/jspf/headerSettings.jsp" %>
<%-- HEADER --%>

<div class="container">

    <form action="controller" method="post" name="answer">
        <input type="hidden" name="command" value="updateAnswerCommand"/>
        <input type="hidden" name="testId" value="${sessionScope.testId}"/>

        <c:forEach items="${answerList}" var="answer" varStatus="loop">
        <div class="form-row">
            <div class="form-group col-md-6 mb-2">
                <label for="newQuestionEn"><fmt:message key="crate_question.question_name_en"/></label>
                <textarea class="form-control" rows="2" id="newQuestionEn" placeholder="Enter text for new question"
                          name="${answer.id}_en" required>${answer.answerText}</textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>

            <div class="form-group col-md-6 mb-2">
                <label for="newQuestionRu"><fmt:message key="crate_question.question_name_ru"/></label>
                <textarea class="form-control" rows="2" id="newQuestionRu" placeholder="Enter text for new question"
                          name="${answer.id}_ru" required>${answer.answerText}</textarea>
                <div class="valid-feedback">Valid.</div>
                <div class="invalid-feedback">Please fill out this field.</div>
            </div>
            <div class="form-group col-md-6 mb-4">
                <input class="form-check-input" type="checkbox" name="isCorrect"  value="${answer.id}" id="is-correct-answer">
                <label class="form-check-label" for="is-correct-answer"><fmt:message key="answer.answer_correct_check_box"/></label>
            </div>
        </div>
        </c:forEach>

        <button type="submit" class="btn btn-primary" id="submitButton"><fmt:message key="common_button.save"/></button>
    </form>

</div>

<%-- FOOTER --%>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
<%-- FOOTER --%>
<script>

    // Function to prevent send form without checking at least one answer as correct
    let groupOne = document.forms.answer.querySelectorAll('input');
        //groupTwo = document.forms.mode.querySelectorAll('input');

        document.getElementById('submitButton').addEventListener('click', function (e){
            console.log("What is e " + e);
        if(!isChecked(groupOne)) {
        e.preventDefault();
        alert('<fmt:message key="answer.answer_pop_up_if_not_checked"/>')
    }
    });

    let isChecked = function(elems){
        let checked = false;
        console.log("Elem: "+elems);
        Array.prototype.map.call(elems, function(checkbox) {
            var i = 1;
            console.log("Step: " + i++);
            console.log(elems);
            if(checkbox.checked) {
            checked = true;
        }
    });
        console.log("Checke in the end =>" + checked);
        return checked;
    }
</script>
</body>
</html>
