package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Answer;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuizzeAnswerCommand extends Command {

    private static final long serialVersionUID = -1555444566799573283L;

    private static final Logger LOG = Logger.getLogger(QuizzeAnswerCommand.class);

    /**
     * Execution method for QuizzeAnswerCommand.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("QuizzeQuestionCommand starts");

        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("currentLocale");
        LOG.debug("QuizzeQuestionCommand get locale: " + local);
        if(local == null){
            local ="en";
        }
        LOG.debug("QuizzeQuestionCommand get locale after if: " + local);

        Long questionId = Long.parseLong(request.getParameter("question_id"));
        AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
        List<Answer> answerList = answerService.findAllByParent(questionId,local);
        LOG.debug("Found in DB: Answer list --> " + answerList);

        session.setAttribute("answerList", answerList);
        LOG.debug("Set the session attribute: answerList --> " + answerList);
        LOG.debug("QuizzeQuestionCommand finished");
        return Path.PAGE_USER_PAGE;
    }
}
