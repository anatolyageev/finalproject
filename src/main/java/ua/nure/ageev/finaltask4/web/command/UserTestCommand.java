package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("UserTestCommand get locale: " + local);
        if(local == null){
            local ="en";
        }
        LOG.debug("UserTestCommand get locale after if: " + local);
        Long testId = Long.parseLong(request.getParameter("test_id"));
       // Long questionId = Long.parseLong(request.getParameter("question_id"));

        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        List<Question> questionList = questionService.findAllByParent(testId,local);
        LOG.debug("Found in DB: Question list --> " + questionList);

        for (Question q:questionList) {
            Long questionId = q.getId();
            List<Answer> answerList = answerService.findAllByParent(questionId,local);
            q.setNumberCorrectAnswers(getCorrNum(answerList));
            q.setAnswers(answerList);
        }

        LOG.debug("Found in DB: Question list answers added --> " + questionList);

        //TODO sorting
        //  List<Subject> subjectList = request.getAttribute("subjectList");
        // put user order beans list to request

        request.setAttribute("questionList", questionList);
        //request.setAttribute("testList", test);
        LOG.debug("Set the session attribute: questionList --> " + questionList);
        LOG.debug("UserTestCommand finished");
        return Path.PAGE_USER_TEST;
    }

    private Integer getCorrNum(List<Answer> answerList) {
        Integer counter =0;
        for (Answer a:
             answerList) {
            if(a.isCorrectAnswer()){
                counter++;
            }
        }
        return counter;
    }
}
