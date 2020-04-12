package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.ageev.finaltask4.services.QuestionService;
import ua.nure.ageev.finaltask4.services.impl.QuestionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuizzeQuestionCommand extends Command {

    private static final long serialVersionUID = -1555444565199573283L;

    private static final Logger LOG = Logger.getLogger(QuizzeQuestionCommand.class);
    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("QuizzeQuestionCommand starts");
        HttpSession session = request.getSession();
        String getRequestURI = request.getRequestURI();
        LOG.debug("QuizzeQuestionCommand get getRequestURI: " +getRequestURI );
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("QuizzeQuestionCommand get locale: " + local);
        if(local == null){
            local ="en";
        }
        LOG.debug("QuizzeQuestionCommand get locale after if: " + local);

        Long testId = Long.parseLong(request.getParameter("test_id"));
        QuestionService questionService = new QuestionServiceImpl(new QuestionRepositoryImpl());
        List<Question> questionList = questionService.findAllByParent(testId,local);
        LOG.debug("Found in DB: Question list --> " + questionList);

        //TODO sorting
        //  List<Subject> subjectList = request.getAttribute("subjectList");
        // put user order beans list to request
        session.setAttribute("questionList", questionList);
        //request.setAttribute("testList", test);
        LOG.debug("Set the request attribute: subjectList --> " + questionList);
        LOG.debug("QuizzeQuestionCommand finished");
        return Path.PAGE_USER_PAGE;
    }
}
