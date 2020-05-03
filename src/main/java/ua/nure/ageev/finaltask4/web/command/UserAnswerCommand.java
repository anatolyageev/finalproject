package ua.nure.ageev.finaltask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.ageev.finaltask4.Path;
import ua.nure.ageev.finaltask4.domain.Question;
import ua.nure.ageev.finaltask4.exception.AppException;
import ua.nure.ageev.finaltask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.ageev.finaltask4.services.AnswerService;
import ua.nure.ageev.finaltask4.services.impl.AnswerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAnswerCommand extends  Command {

    private static final long serialVersionUID = -1555444566799343283L;

    private static final Logger LOG = Logger.getLogger(UserAnswerCommand.class);


    /**
     * Execution method for command.
     *
     * @param request
     * @param response
     * @return Address to go once the command is executed.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        LOG.debug("UserAnswerCommand starts");

        HttpSession session = request.getSession();
        String local = (String) session.getAttribute("currentLocale");

        LOG.debug("UserAnswerCommand get locale: " + local);
        if(local == null){
            local ="en";
        }
        LOG.debug("UserAnswerCommand get locale after if: " + local);

        Map<Long,Boolean> mapAnswer = (HashMap) session.getAttribute("mapAnswer");
        Map<Long,Boolean> answerIdUser = (HashMap)session.getAttribute("answerIdUser");
        LOG.debug("UserAnswerCommand get mapAnswer : " + mapAnswer);
        List<Question> questionList = (ArrayList)session.getAttribute("questionList");
        LOG.debug("UserAnswerCommand get questionList : " + questionList);

        if(request.getParameter("question_id")!=null ) {
            Long questionId = Long.parseLong(request.getParameter("question_id"));
            LOG.debug("UserAnswerCommand get questionId : " + questionId);
            AnswerService answerService = new AnswerServiceImpl(new AnswerRepositoryImpl());
            String[] answers = request.getParameterValues("answer_id");
            LOG.debug("UserAnswerCommand get answers : " + answers);
            for (int i = 0; i < answers.length; i++) {
                answerIdUser.put(Long.parseLong(answers[i]),true);
                if (answerService.getOne(Long.parseLong(answers[i]), local).getCorrectAnswer()) {
                    mapAnswer.put(questionId, true);

                } else {
                    mapAnswer.put(questionId, false);
                }
            }
        }
        LOG.debug("UserAnswerCommand get mapAnswer : " + mapAnswer);
        return Path.PAGE_USER_TEST;
    }
}
