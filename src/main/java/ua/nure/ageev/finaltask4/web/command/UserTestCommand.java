package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.domain.Test;
import ua.nure.ageev.finaltask4.domain.UserAnswer;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.TestRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.TestService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.TestServiceImpl;
import ua.nure.ageev.finaltask4.web.utils.DataHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTestCommand extends Command {

    private static final long serialVersionUID = -1555444566799573283L;

    private static final Logger LOG = Logger.getLogger(UserTestCommand.class);

    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UserTestCommand starts");

        HttpSession session = request.getSession();
        String local = DataHelper.getLanguage(request);
        LOG.debug("UserTestCommand get locale after if: " + local);
        Long testId = Long.parseLong(request.getParameter("test_id"));
       // Long questionId = Long.parseLong(request.getParameter("question_id"));
        Map<Long, Boolean> answerIdUser = new HashMap<>();
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        TestService testService = new TestServiceImpl(new TestRepositoryImpl());
        List<Question> questionList = questionService.findAllByParent(testId,local);
        List<UserAnswer> userAnswerList = new ArrayList<>();
        Map<Long,Boolean> mapAnswer = new HashMap<>();
        Test currentTest = testService.getOne(testId, local);
        currentTest.setQuestionQuantity(questionList.size());

        LOG.debug("Found in DB: Question list --> " + questionList);

        for (Question q:questionList) {
            Long questionId = q.getId();
            List<Answer> answerList = answerService.findAllByParent(questionId,local);
            q.setNumberCorrectAnswers(getCorrNum(answerList));
            q.setAnswers(answerList);
        }
        fillUserAnswer(questionList, userAnswerList);


        LOG.debug("Found in DB: Question list answers added --> " + questionList);


        session.setAttribute("mapAnswer", mapAnswer);
        session.setAttribute("currentTest", currentTest);
        session.setAttribute("questionList", questionList);
        session.setAttribute("answerIdUser",answerIdUser);
        //request.setAttribute("testList", test);
        LOG.debug("Set the session attribute: questionList --> " + questionList);
        LOG.debug("UserTestCommand finished");
        return Path.PAGE_USER_TEST;
    }

    private void fillUserAnswer(List<Question> questionList, List<UserAnswer> userAnswerList) {
        for (Question q:questionList) {
            UserAnswer userAnswer = new UserAnswer();
            Long questionId = q.getId();
            userAnswer.setQuestionId(questionId);
            userAnswerList.add(userAnswer);
        }
    }

    private Integer getCorrNum(List<Answer> answerList) {
        Integer counter =0;
        for (Answer a:
             answerList) {
            if(a.getCorrectAnswer()){
                counter++;
            }
        }
        return counter;
    }
}
